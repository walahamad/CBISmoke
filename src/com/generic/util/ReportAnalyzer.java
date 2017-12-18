package com.generic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.generic.setup.EnvironmentFiles;

public class ReportAnalyzer {
	public static void analyze(String path, float period_step ) throws IOException
	{
		System.out.println("Analyzing report: " + path +" Period is: "+ period_step);
		String content;
		content = new String(Files.readAllBytes(Paths.get(path)));
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
		
		String Pline=bufReader.readLine();
		String Cline=null;
		
		FileUtils.writeStringToFile(new File(path),"<font face='monospace'>0: "+Pline);
		
		while( (Cline=bufReader.readLine()) != null )
		{	
			
				
				long  diff = 0 ;
				
				try {
					diff = (format.parse(Cline.split(" ")[1]).getTime())- (format.parse(Pline.split(" ")[1]).getTime());
				} catch (Exception t) {
					// TODO Auto-generated catch block
					System.out.println("Line is not parsable");
					diff = 0;
				}
				
			if (diff>period_step)
			{
				FileUtils.writeStringToFile(new File(path), "<SPAN STYLE='background-color: #ffffcc'>"+diff/1000+": "+Cline+"</SPAN>",true) ;
			}
			else
			{
				FileUtils.writeStringToFile(new File(path), diff/1000+": "+Cline, true) ;
			}
			Pline = new String(Cline);
		}
		
	}
	
	public static void splitLogs()
	{
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath(),EnvironmentFiles.getLogFileName());
		//Path PathObj = Paths.get("F:\\WS\\SAS_FW\\logs\\Application.log");
		FileWriter fw = null;
		BufferedWriter bw = null;
		String[] content = null;
		
		try {
			FileUtils.deleteDirectory(new File(PathObj.getParent().toString()+"\\reportLogs\\"));
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e2) {
			e2.printStackTrace();
		}
		File reportLogs = new File(PathObj.getParent().toString()+"\\reportLogs\\");
		reportLogs.mkdir();
		
		
		try {
			content = new String(Files.readAllBytes(PathObj)).split("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (String line : content) {
			try {

				if (!getThreadID(line).equals("")) {
					File logfile = new File(PathObj.getParent()+"\\reportLogs\\"+ getThreadID(line) + ".html");
					// if file doesnt exists, then create it
					if (!logfile.exists()) {
						logfile.createNewFile();
						fw = new FileWriter(logfile.getAbsoluteFile(), true);
						bw = new BufferedWriter(fw);
						bw.write("<font face='monospace'>\n"+line+"\n");
					}else
					{
						fw = new FileWriter(logfile.getAbsoluteFile(), true);
						bw = new BufferedWriter(fw);
						bw.write(line+"\n");
					}
				} // if
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	private static String getThreadID(String line) {
		String[] threadID = line.split(":");
		//thread id should be numeric
		if (threadID.length>4 && StringUtils.isNumeric(threadID[3].trim()))
			return threadID[3].trim();
		return "";
	}
	
	public static void splitCases() {
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath(),"reportLogs");
		//Path PathObj = Paths.get("F:\\WS\\SAS_FW_testNg_justDriver_static\\logs\\reportLogs");
		
		File index = new File(PathObj.toString());
		String[]entries = index.list();
		String caseSplitter = "com.generic.setup.SelTestCase.setUp";  
		int numberOfCases = 0;
		
		for(String s: entries){
			File currentFile = new File(index.getPath(),s);
			System.out.println("File"+ currentFile.toString());
		    String content = null;
			try {
				content = new String(Files.readAllBytes(currentFile.toPath()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			//check if there are more than one case in file
			numberOfCases = content.split(caseSplitter).length/2 ;
			String[] lines = content.split("\r");
			if (numberOfCases > 1)
			{
				for (int readedCases = 0; readedCases < numberOfCases ; readedCases++)
				{
					String splittedFile = ""; 
					int stopper = 1; 
					for( String line :  lines)
				    {
						//Keep reading and insert into file string case until the case number  
				    	if (line.contains(caseSplitter) && line.toLowerCase().contains("starting") )
				    	{	stopper++;		    			
				    	}//stopper counter 
				    	if (stopper==1 || stopper == (readedCases+2))
				    	{
				    		splittedFile+=(line+"\r");
				    	}
				    }
					try {
						Files.write(Paths.get(currentFile.toString().replace(".", "_"+readedCases + ".")), splittedFile.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				//removing the base file to prevent duplication 
				currentFile.delete();
				
			}//if number of cases 
		}
	}//split cases
	
	public static void copyScreenShots()
	{
		Path PathObj = Paths.get(EnvironmentFiles.getLogFilePath());
		//Path PathObj = Paths.get("F:\\WS\\SAS_FW_testNg_justDriver_static\\logs");
		
		File index = new File(PathObj.toString());
		String[]entries = index.list();
		for(String s: entries){
			File currentFile = new File(index.getPath(),s);
			if (currentFile.toString().endsWith("jpg") || currentFile.toString().endsWith("png"))
			{
				try {
					FileUtils.copyFileToDirectory(currentFile, Paths.get(PathObj.toString(), "reportLogs").toFile());
					currentFile.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ReportAnalyzer.splitLogs();
		
	}

}
