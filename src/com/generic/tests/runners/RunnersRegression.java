package com.generic.tests.runners;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.util.Strings;
import org.testng.xml.XmlTest;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.SASLogger;

public class RunnersRegression extends SelTestCase{
	private static  ArrayList<String> runners =null ;
	private static  ArrayList<String> browsers =null ;
	private static  ArrayList<String> regressionsPathes = new ArrayList<String>();
	private static int testCaseID;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.RunnersRegressionSheet;
	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ; 
	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		try {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		runners = Common.readRunners();
		Testlogs.get().debug("Started in Regression");
		browsers = Common.readBrowsers();
		for (String regressionName : runners) {
			regressionsPathes.add(findFile(new File(System.getProperty("user.dir") + "\\src\\com\\generic\\tests"), regressionName));
		}
		
		writeFinalRegression(regressionsPathes);
		
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public static void writeFinalRegression(ArrayList<String> regressionsPathes) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
		File srcTemplateFile = new File(System.getProperty("user.dir") + "/src/com/generic/test_runners/TemplateRegression.xml");
		File destFile = new File(System.getProperty("user.dir") + "/src/com/generic/test_runners/FinalRegression.xml");
		destFile.createNewFile();
//		FileUtils.copyFile(srcFile, destFile);
		
		DocumentBuilderFactory srcTemplateFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder srcTemplateBuilder = srcTemplateFactory.newDocumentBuilder();
		org.w3c.dom.Document srcTemplateDoc = srcTemplateBuilder.parse(srcTemplateFile);
		
		srcTemplateDoc.getDocumentElement().normalize();
		org.w3c.dom.Node srcDocRootNode = srcTemplateDoc.getElementsByTagName("suite").item(0);
				
		Transformer finalTransformer = TransformerFactory.newInstance().newTransformer();
		Result finalOutput = new StreamResult(destFile);
		Source finalInput = new DOMSource(srcDocRootNode);
		finalTransformer.transform(finalInput, finalOutput);
		
		for (String path : regressionsPathes) {
			File srcXmlFile = new File(path);
			
			DocumentBuilderFactory srcDbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder srcBuilder = srcDbFactory.newDocumentBuilder();
			org.w3c.dom.Document srcDoc = srcBuilder.parse(srcXmlFile);
			
			DocumentBuilderFactory destDbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder destBuilder = destDbFactory.newDocumentBuilder();
			org.w3c.dom.Document destDoc = destBuilder.parse(destFile);
			
			srcDoc.getDocumentElement().normalize();
			org.w3c.dom.Element srcTestNode = (org.w3c.dom.Element) srcDoc.getElementsByTagName("test").item(0);
			
			org.w3c.dom.Node destRootNode = destDoc.getElementsByTagName("suite").item(0);
			
			for (String browser : browsers) {
				org.w3c.dom.Element destTestNode = (Element) destDoc.adoptNode(srcTestNode.cloneNode(true));
				
				String oldNodeAttributeValue = srcTestNode.getAttribute("name");
				String [] attributeSplit = oldNodeAttributeValue.split(" ");
				attributeSplit[attributeSplit.length-1] = browser;
				
				String newNodeAttributeValue =  "";
				for (String attributePart : attributeSplit) {
					newNodeAttributeValue = newNodeAttributeValue + attributePart + " ";
				}
				destTestNode.setAttribute("name", newNodeAttributeValue); 
				org.w3c.dom.Element destTestNodeParameter = (Element) destTestNode.getElementsByTagName("parameter").item(0);
				destTestNodeParameter.setAttribute("value", browser);
				destRootNode.appendChild(destTestNode);
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(destFile);
			Source input = new DOMSource(destRootNode);
			transformer.transform(input, output);
			
		}
	}
	
	public static void populateBrowsers (ArrayList<String> browsers, org.w3c.dom.Node originalNode, org.w3c.dom.Node rootNode) {
		
	}
	
	public static String findFile(File dir,String fileName) {
		//retrieves array of Directories under dir parameter
		File [] subDirs = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		
		//loop over subDirs to get fileName
		for (File folder : subDirs) {
			String filePath = searchFile(folder, fileName);
			if(Strings.isNotNullAndNotEmpty(filePath)) {
				return filePath;
			}
			findFile(folder, fileName);
		}
		return fileName;
	}

	private static String searchFile(File dir, String fileName) {
		Testlogs.get().debug("Running searchFile for getting (" + fileName + ") in: " + dir.getAbsolutePath());
		File [] files = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				Testlogs.get().debug("Inside accept dir = " + dir +" || name = "+ name);
				return name.contains(fileName);
			}
		});
		if (files.length > 0) {
		for(File file : files) {
			Testlogs.get().debug("File is : " + file.getName());
		}
		String filePath = files[0].getAbsolutePath();
		return filePath;
		} else {
			return null;
		}
	} 
	
}
