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
	
	/***
	 * Writes the result of the executed test case to index.html file 
	 * @param testCaseName
	 * @param testCaseDesc
	 * @param testCaseStartTime
	 * @param testCaseEndTime
	 * @param status
	 * @param out
	 * @throws IOException
	 * @throws NumberFormatException
	 */
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
		    out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>" + status.substring(0,4) + "</b></td>\n");
		} else if (status.startsWith("Ignore")) {
			out.write("<td width=10% align= center  bgcolor=Yellow><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>" + status.substring(0,6) + "</b></td>\n");
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
	
	/***
	 * Calculates the duration of execution for the test case
	 * @param testCaseStartTime
	 * @param testCaseEndTime
	 * @return
	 */
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
	
	/***
	 * Update the details of copied template for the failed test case.
	 * @param testCaseFilePath
	 * @param title
	 * @param testScriptNumber
	 * @param failureDetails
	 * @param screenshotName
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

                if (strLine.contains("~TCName~")) {
                	strLine=strLine.replaceAll("~TCName~", title);
                	
                } if (strLine.contains("~TSNum~")) {
                	strLine=strLine.replaceAll("~TSNum~", testScriptNumber);
                	
                } if (strLine.contains("~Fail Desc~")) {
                	strLine=strLine.replaceAll("~Fail Desc~", failureDetails);
                	
                } if (strLine.contains("~ScreenshotPath~")) {
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
    
    /***
     * Copies the failed test case template into the target reports folder
     * @param testCaseName
     * @param fstream
     * @throws IOException
     */
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
	
	/***
	 * Copies the template of test cases execution index into the target reports folder and update
	 * the related details of the execution.
	 * @param testStartTime
	 * @param out
	 * @param rUNDATE
	 * @param eNVIRONMENT
	 * @param rBrowserType
	 * @param testSuiteName
	 * @throws IOException
	 */
	public static void writeReportTestDetails(final String testStartTime, BufferedWriter out, String rUNDATE,
			String eNVIRONMENT, String rBrowserType, String testSuiteName) throws IOException {
		try {
			SelTestCase.getCurrentFunctionName(true);
			SelTestCase.logs.debug("Copying the template into the target failed test case");
			BufferedReader br = new BufferedReader(new FileReader(EnvironmentFiles.getExecutinResultsIndexFileTemplate()));
			
			// copying template into target
			String str;
		    while ((str = br.readLine()) != null) {
		    	if (str.contains("~RunDate~")) {
		    		str=str.replaceAll("~RunDate~", rUNDATE);
                	
                } if (str.contains("~Env~")) {
                	str=str.replaceAll("~Env~", eNVIRONMENT);
                	
                } if (str.contains("~BrowserType~")) {
                	str=str.replaceAll("~BrowserType~", rBrowserType);
                	
                } if (str.contains("~StartTime~")) {
                	str=str.replaceAll("~StartTime~", testStartTime);
                	
                } if (str.contains("~SuiteName~")) {
                	str=str.replaceAll("~SuiteName~", testSuiteName);
                }
                
		        out.append(str);
		    }
		    br.close();
		} catch(Throwable t) {
			SelTestCase.logs.debug("Error in writing index file ");
			t.printStackTrace();
		} finally {
			out.close();
		}
	}
}
