package com.generic.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class TestUtilities extends SelTestCase {

	public static void prepareLogs() throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.CLEAR_LOGS_MSG, ""));
		String logs_dir = EnvironmentFiles.getLogFilePath();
		String log_file = EnvironmentFiles.getLogFileName();
		String log_abs_path = logs_dir + "/" + log_file;

		PrintWriter writer = new PrintWriter(log_abs_path);
		writer.print("");
		writer.close();
		logs.debug(MessageFormat.format(LoggingMsg.CLEAR_LOGS_MSG, " done"));
	}

	public static void reportSetup(String Env) throws Exception {

		try {
			if (runReportSetup) {
				logs.debug(LoggingMsg.EXECUTE_REPORT_SETUP);

				try {
					mainReportDir = EnvironmentFiles.getReportsFolderPath();

					logs.debug(MessageFormat.format(LoggingMsg.REPORT_DIR, mainReportDir));

					File dir1 = new File(mainReportDir);
					boolean exists = dir1.exists();
					if (!exists) {
						logs.debug(MessageFormat.format(LoggingMsg.MAIN_DIR_EXISTANCE_MSG, "does not", exists));
						dir1.mkdir(); // creating main directory if it doesn't exist
					} else {
						logs.debug(MessageFormat.format(LoggingMsg.MAIN_DIR_EXISTANCE_MSG, "does", exists));
					}
					createRunReportDirectory();
				} catch (Throwable t) {
					t.printStackTrace();
					logs.debug(LoggingMsg.FAILED_REPORT_FOLDERS_CREATION_MSG);
				}

				ReportUtil.startTesting(reportDirectory + "//index.html", ReportUtil.now(SelTestCase.time_date_format),
						Env);

				Path PathObj = Paths.get(EnvironmentFiles.getTemplateDir());

				File index = new File(PathObj.toString());
				String[] entries = index.list();
				for (String s : entries) {
					File currentFile = new File(index.getPath(), s);
					if (currentFile.toString().endsWith("jpg") || currentFile.toString().endsWith("png")) {
						try {
							FileUtils.copyFileToDirectory(currentFile, Paths.get(reportDirectory).toFile());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				runReportSetup = false;
			}

		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}

	}

	public static void createRunReportDirectory() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(SelTestCase.reportFolderDateStampFormat);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(SelTestCase.reportFolderTimeStampFormat);
		reportDirectory = mainReportDir + "/" + "AutoRep_" + ft.format(dNow) + sdf.format(cal.getTime());
		new File(reportDirectory).mkdir();

	}

	public static void configInitialization() throws Exception {
		getCurrentFunctionName(true);

		logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, "Execute initialize function"));
		// config property file
		setCONFIG(new Properties());
		FileInputStream fn = new FileInputStream(EnvironmentFiles.getConfigFilePath());
		getCONFIG().load(fn);
		
		// getCONFIG().setProperty("testSiteName",
		// "https://"+getCONFIG().getProperty("testEnvironment")+"/"+getCONFIG().getProperty("testSiteName"));
		// getCONFIG().setProperty("logout",
		// "https://"+getCONFIG().getProperty("testEnvironment")+"."+getCONFIG().getProperty("logout"));

		logs.debug(MessageFormat.format(LoggingMsg.REPORT_TCID_MSG, testCaseRepotId));

		setDatatable(new Xls_Reader(EnvironmentFiles.getDataSheetPath()));

		// set the max wait time
		setWaitTime(Integer.parseInt(getCONFIG().getProperty("waitTime")));

		getCurrentFunctionName(false);
	}

	public static void writeFinalXMLRegression(ArrayList<String> regressionsPathes, String[] targetBrowsers)
			throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		getCurrentFunctionName(true);
		// Template file to copy the suite tag from
		File srcTemplateFile = new File(
				System.getProperty("user.dir") + "/src/com/generic/test_runners/TemplateRegression.xml");

		// The name of final output file
		File destFile = new File(System.getProperty("user.dir") + "/src/com/generic/test_runners/FinalRegression.xml");
		destFile.createNewFile();

		// Read the source template
		DocumentBuilderFactory srcTemplateFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder srcTemplateBuilder = srcTemplateFactory.newDocumentBuilder();
		org.w3c.dom.Document srcTemplateDoc = srcTemplateBuilder.parse(srcTemplateFile);

		// locate the root element (suite) tag
		srcTemplateDoc.getDocumentElement().normalize();
		org.w3c.dom.Node srcDocRootNode = srcTemplateDoc.getElementsByTagName("suite").item(0);

		// Copy the template to the file FinalRegression.xml
		Transformer finalTransformer = TransformerFactory.newInstance().newTransformer();
		Result finalOutput = new StreamResult(destFile);
		Source finalInput = new DOMSource(srcDocRootNode);
		finalTransformer.transform(finalInput, finalOutput);

		// loop over the XML regression files that will be included in the run
		for (String path : regressionsPathes) {
			File srcXmlFile = new File(path);

			// Read current XML Runner
			DocumentBuilderFactory srcDbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder srcBuilder = srcDbFactory.newDocumentBuilder();
			org.w3c.dom.Document srcDoc = srcBuilder.parse(srcXmlFile);

			// Read the FinalRegression.xml file
			DocumentBuilderFactory destDbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder destBuilder = destDbFactory.newDocumentBuilder();
			org.w3c.dom.Document destDoc = destBuilder.parse(destFile);

			// locate the <test> tag from the source regression xml
			srcDoc.getDocumentElement().normalize();
			org.w3c.dom.Element srcTestNode = (org.w3c.dom.Element) srcDoc.getElementsByTagName("test").item(0);

			// fetch the <suite> tag in the FinalRegression.xml file to copy <test> tag to
			org.w3c.dom.Node destRootNode = destDoc.getElementsByTagName("suite").item(0);

			// loop over included browsers in the regression to append them to <test> tags.
			for (String browser : targetBrowsers) {
				org.w3c.dom.Element destTestNode = (Element) destDoc.adoptNode(srcTestNode.cloneNode(true));

				// retrieving the name attribute on <test> tag and adding the target browser to
				String oldNodeAttributeValue = srcTestNode.getAttribute("name");
				String[] attributeSplit = oldNodeAttributeValue.split(" ");
				attributeSplit[attributeSplit.length - 1] = browser;

				String newNodeAttributeValue = "";
				for (String attributePart : attributeSplit) {
					newNodeAttributeValue = newNodeAttributeValue + attributePart + " ";
				}

				// appending the new value of "name" attribute to include the browser name
				destTestNode.setAttribute("name", newNodeAttributeValue);

				// retrieving the "parameter" attribute value and replace it with the targeted
				// browser
				org.w3c.dom.Element destTestNodeParameter = (Element) destTestNode.getElementsByTagName("parameter")
						.item(0);
				destTestNodeParameter.setAttribute("value", browser);

				// Adding the final <test> tag to the <suite> tag in Finalregression.xml file
				destRootNode.appendChild(destTestNode);
			}

			// Save the changes to FinalRegression.xml file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(destFile);
			Source input = new DOMSource(destRootNode);
			transformer.transform(input, output);

		}
		getCurrentFunctionName(false);
	}

	/***
	 * locate files recursively (Currently Unused)
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static String locateXMLRunnerFile(File dir, String fileName) {
		getCurrentFunctionName(true);
		// retrieves array of Directories under dir parameter
		File[] subDirs = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});

		// loop over subDirs to get fileName
		for (File folder : subDirs) {
			String filePath = searchSpecificFile(folder, fileName);
			if (filePath.length()==0) {
				getCurrentFunctionName(false);
				return filePath;
			}
			locateXMLRunnerFile(folder, fileName);
		}
		getCurrentFunctionName(false);
		return fileName;
	}

	public static String searchSpecificFile(File dir, String fileName) {
		getCurrentFunctionName(true);
		File[] files = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.contains(fileName);
			}
		});
		if (files.length > 0) {
			String filePath = files[0].getAbsolutePath();
			getCurrentFunctionName(false);
			return filePath;
		} else {
			getCurrentFunctionName(false);
			return null;
		}
	}

}
