package com.generic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;

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
	
	public static void main(String[] args) throws IOException {
		///////////////???????
		ReportAnalyzer.analyze("D:/workspace_svn/DBIAutomation_new/AutomationReport/MiniRegression-Report-12-18-2016-Time-180638/Suite1_TC_log1_DinerOrderPlacment_1.html", 55);
		
	}

}
