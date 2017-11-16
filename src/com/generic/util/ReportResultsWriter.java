package com.generic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.generic.setup.EnvironmentFiles;
import com.generic.setup.SelTestCase;

public class ReportResultsWriter {
	public static void addExecutedTestCaseToIndex(final String testCaseName, final String testCaseDesc,
			final String testCaseStartTime, final String testCaseEndTime, final String status, BufferedWriter out)
					throws IOException, NumberFormatException {
		out.write("<tr>\n");
		out.write("<td width=5% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + ReportUtil.scriptNumber + "</b></td>\n");
		if (status.equalsIgnoreCase("Pass") || status.equalsIgnoreCase("Ignore") ) {
		    out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseName + "</b></td>\n");
		} else if (status.startsWith("Fail")) {
		    out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href=" + ReportUtil.currentSuiteName + "_TC" 
		    		  + ReportUtil.tcid + "_" + testCaseName.replaceAll(" ", "_") + ".html>" + testCaseName + "</a></b></td>\n");
		}

		out.write("<td width=35% align= center><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>" + testCaseDesc + "</b></td>\n");
		if (status.startsWith("Pass")) {
		    out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>" + status + "</b></td>\n");
		} else if (status.startsWith("Fail")) {
		    out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + status.substring(0,4) + "</b></td>\n");
		} else if (status.startsWith("Ignore")) {
			out.write("<td width=10% align= center  bgcolor=Yellow><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + status.substring(0,6) + "</b></td>\n");
		}
		
		out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseStartTime + "</b></td>\n");
		out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseEndTime + "</b></td>\n");


		out.write(calculateTestCaseRunTime(testCaseStartTime, testCaseEndTime));
		
		
		out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href="
		        + ReportUtil.currentSuiteName + "_TC_log" + ReportUtil.tcid + "_" + testCaseName.replaceAll(" ", "_")
		        + ".html>" + testCaseName + "</a></b></td>\n");
		
		out.write("</tr>\n");
		reportAnalyzer.analyze(ReportUtil.currentDir + "//" + ReportUtil.currentSuiteName + "_TC_log"
				+ ReportUtil.tcid + "_" + testCaseName.replaceAll(" ", "_")
				+ ".html", Float.parseFloat(SelTestCase.getCONFIG().getProperty("report_analysis_period")));
	}
	
	private static String calculateTestCaseRunTime(final String testCaseStartTime, final String testCaseEndTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SelTestCase.time_date_format);
		Date start_date = null;
		Date end_date = null;
		try {
			start_date = simpleDateFormat.parse(testCaseStartTime);
			end_date = simpleDateFormat.parse(testCaseEndTime);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long duration  = end_date.getTime() - start_date.getTime();
		//assume duration not more than one day 
				long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
				long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration)-(diffInHours*60);
				long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration)-(diffInMinutes*60);
				
				if (diffInMinutes > Integer.parseInt(SelTestCase.getCONFIG().getProperty("timeIndicator"))){
					return "<td width=10% align= center bgcolor=#ffbfbf ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
				    		+String.format("%02d",diffInHours)+":"+ String.format("%02d",diffInMinutes)+":"+String.format("%02d",diffInSeconds)
				    		+ "</b></td>\n";
				}
				else
				{
					
				return "<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
						+String.format("%02d",diffInHours)+":"+ String.format("%02d",diffInMinutes)+":"+String.format("%02d",diffInSeconds)
						+ "</b></td>\n";
				}
	}
	
	/**
     * Update the copied template.
     */
    public static void updateFailedTestCaseDetails(String testCaseFilePath, String title, String testScriptNumber, String failureDetails, String screenshotName) {
    	SelTestCase.getCurrentFunctionName(true);
    	StringBuffer buf = new StringBuffer();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(testCaseFilePath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {

                if (strLine.indexOf("~TCName~") != -1) {
                	strLine=strLine.replaceAll("~TCName~", title);
                	
                } if (strLine.indexOf("~TSNum~") != -1) {
                	strLine=strLine.replaceAll("~TSNum~", testScriptNumber);
                	
                } if (strLine.indexOf("~Fail Desc~") != -1) {
                	strLine=strLine.replaceAll("~Fail Desc~", failureDetails);
                	
                } if (strLine.indexOf("~ScreenshotPath~") != -1) {
                	strLine=strLine.replaceAll("~ScreenshotPath~", screenshotName);
                }
                
                buf.append(strLine);
            }

            in.close();
            FileOutputStream fos = new FileOutputStream(testCaseFilePath);
            DataOutputStream output = new DataOutputStream(fos);
            output.writeBytes(buf.toString());
            fos.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        SelTestCase.getCurrentFunctionName(false);

    }
    
	public static void generateFailedTestCasePage(final String testCaseName, FileWriter fstream)
			throws IOException {
		BufferedWriter out = null;
		try {
			SelTestCase.getCurrentFunctionName(true);
			SelTestCase.logs.debug("Copying the template into the target failed test case");
			out = new BufferedWriter(fstream);
			BufferedReader br = new BufferedReader(new FileReader(EnvironmentFiles.getFailedTestCaseReportTemplate()));
			
			// copying template into target
			String str;
		    while ((str = br.readLine()) != null) {
		    	SelTestCase.logs.debug("Writing the tag : \n" + str);
		        out.append(str);
		    }
		    br.close();
		} catch(Throwable t) {
			SelTestCase.logs.debug("Error in generateFailedTestCase function : ");
			t.printStackTrace();
		} finally {
			out.close();
		}
		int i = 0;
		updateFailedTestCaseDetails(ReportUtil.failedTestCaseFileName, testCaseName, (i +1) + "", ReportUtil.teststatus, ReportUtil.screenShotPath.get(i));
		SelTestCase.getCurrentFunctionName(false);
	}
	
	public static void writeTestCasesHeader(final String suiteName, BufferedWriter out) throws IOException {
		try {
		out.write("<h4> <FONT COLOR=#153E7E FACE= Arial  SIZE=4.5> <u>"
		    + suiteName + " Report :</u></h4>\r\n");
		out.write("<table  border=1 cellspacing=1 cellpadding=1 width=100%>\r\n");
		out.write("<tr>\r\n");
		out.write("<td width=5%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script</b></td>\r\n");
		out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Name</b></td>\r\n");
		out.write("<td width=35% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Description</b></td>\r\n");
		out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\r\n");
		out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></td>\r\n");
		out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>\r\n");
		out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>exec. time</b></td>\r\n");
		out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Logs</b></td>\r\n");

		out.write("</tr>\r\n");
		}
		catch(Throwable t) {
		} finally {
			out.close();
		}
	}
	public static void writeReportTestDetails(final String testStartTime, BufferedWriter out, String rUNDATE,
			String eNVIRONMENT, String rBrowserType) throws IOException {
		try {
		out.newLine();

		out.write("<html>\r\n");
		out.write("<HEAD>\r\n");
		out.write(" <TITLE>Automation Test Results</TITLE>\n");
		out.write("<style>tr:nth-of-type(odd) {   background: #eee; }th {   background: #333;   color: white;   font-weight: bold; }td, th {   padding: 6px;   border: 1px solid #ccc;   text-align: left; }</style>\r\n");
		out.write("</HEAD>\r\n");

		out.write("<body>\r\n");
		out.write("<h4 align=center><FONT COLOR=#153E7E FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\r\n");
		out.write("<h4> <FONT COLOR=#153E7E FACE=Arial SIZE=4.5> <u>Test Details :</u></h4>\r\n");

		out.write("<table  border=1 cellspacing=1 cellpadding=1 >\r\n");
		out.write("<tr>\r\n");
		
		out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\r\n");
		out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"
		    + rUNDATE + "</b></td>\r\n");
		out.write("</tr>\r\n");
		out.write("<tr>\r\n");

		out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\r\n");

		out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>\r\n"
		    + testStartTime + "</b></td>\r\n");
		out.write("</tr>\r\n");
		out.write("<tr>\r\n");
		
		out.newLine();
		out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\r\n");
		out.write("\n<td width=150 align= left id = 'end_time'><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></td>\r\n" + System.lineSeparator());
		out.write("</tr>\r\n");
		out.write("<tr id = 'Done_end'>\r\n");
		out.newLine();

		out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\r\n");
		out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>\r\n"
		    + eNVIRONMENT + "</b></td>\r\n");
		out.write("</tr>\r\n");
		out.write("<tr>\r\n");

		out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Browser Type</b></td>\r\n");
		out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>\r\n"
		    + rBrowserType + "</b></td>\n");
		out.write("</tr>\r\n");

		out.write("</table>\r\n");
		} catch(Throwable t) {
			
		} finally {
			out.close();
		}
	}
}
