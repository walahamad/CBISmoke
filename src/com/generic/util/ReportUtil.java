package com.generic.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.generic.setup.SelTestCase;


/**
 * The Class ReportUtil.
 */
public final class ReportUtil extends SelTestCase {

    /**
     * The private constructor instance.
     */
    private ReportUtil() {
    }

    /** The script number. */
    public static int scriptNumber = 1;

    /** The index result filename. */
    public static String indexResultFilename;

    /** The current dir. */
    public static String currentDir;

    /** The current suite name. */
    public static String currentSuiteName;

    /** The tcid. */
    public static int tcid;

    /** The pass number. */
    public static double passNumber;

    /** The fail number. */
    public static double failNumber;

    /** The new test. */
    public static boolean newTest = true;

    /** The description. */
    public static ArrayList<String> description = new ArrayList<String>();

    /** The keyword. */
   // public static ArrayList<String> keyword = new ArrayList<String>();

    /** The teststatus. */
    public static String teststatus = null;

    /** The screen shot path. */
    public static ArrayList<String> screenShotPath = new ArrayList<String>();

    /**
     * MY_BYTE constant is being used in wait methods.
     */
    public static final int MY_BYTE = 1000;

    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns
    // false.
    /**
     * Delete dir.
     *
     * @param dir
     *        the dir
     * @return true, if successful
     */
    public static boolean deleteDir(final File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = new File(dir, children[i]).delete();
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        // return dir.delete();
        return true;
    }

    /**
     * Start testing.
     *
     * @param filename
     *        the filename
     * @param testStartTime
     *        the test start time
     * @param env
     *        the env
     * @param rel
     *        the rel
     * @param browser
     *        This is the browser type that executed the test.
     */
    public static void startTesting(final String filename,
        final String testStartTime,
        final String env,
        final String browser) {
        indexResultFilename = filename;
        currentDir =
            indexResultFilename.substring(0,
                indexResultFilename.lastIndexOf("//"));
        logDir = currentDir;
        ReportUtil.deleteDir(new File(currentDir));
        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            // Create file

            fstream = new FileWriter(filename);
            out = new BufferedWriter(fstream);

            String rUNDATE = ReportUtil.now("dd.MMMMM.yyyy").toString();

            String eNVIRONMENT = env;

            // Added so the report will show the browser type.
            String rBrowserType = browser;

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

            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }
    }

    /**
     * Start suite.
     *
     * @param suiteName
     *        the suite name
     */
    public static void startSuite(final String suiteName) {

        FileWriter fstream = null;
        BufferedWriter out = null;
        currentSuiteName = suiteName.replaceAll(" ", "_");
        tcid = 1;
        try {

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

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
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }
    }

    /**
     * End suite.
     */
    public static void endSuite() {
        FileWriter fstream = null;
        BufferedWriter out = null;

        try {
            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);
            out.write("</table>\n");
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {

            fstream = null;
            out = null;
        }

    }
    
    public static void copyLogsFile(File dest)
    		throws IOException {
    	String logs_dir = System.getProperty("user.dir") + "/"
                + SelTestCase.getCONFIG().getProperty("logs");
		String log_file = "Application.log";
		String log_abs_path = logs_dir + "/" + log_file;
    	File source = new File(log_abs_path);
    	Files.copy(source.toPath(), dest.toPath());
    }

    /**
     * Adds the test case.
     *
     * @param testCaseName
     *        the test case name
     * @param testCaseStartTime
     *        the test case start time
     * @param testCaseEndTime
     *        the test case end time
     * @param status
     *        the status
     */
    public static void addTestCase(final String testCaseName,
        final String testCaseDesc,
        final String testCaseStartTime,
        final String testCaseEndTime,
        final String status) {
    	SelTestCase.getCurrentFunctionName(true);
    	logs.debug("Adding a "+ status +" case to report");
        newTest = true;
        FileWriter fstream = null;
        BufferedWriter out = null;
        
        
        File log_file = new File(currentDir + "//" + currentSuiteName + "_TC_log"
                + tcid + "_" + testCaseName.replaceAll(" ", "_")
                + ".html");
        try {
			copyLogsFile(log_file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
            newTest = true;
            if (status.startsWith("Fail")) {

                File f =
                    new File(currentDir + "//" + currentSuiteName + "_TC"
                        + tcid + "_" + testCaseName.replaceAll(" ", "_")
                        + ".html");
                f.createNewFile();
                fstream =
                    new FileWriter(currentDir + "//" + currentSuiteName + "_TC"
                        + tcid + "_" + testCaseName.replaceAll(" ", "_")
                        + ".html");
                out = new BufferedWriter(fstream);
                out.write("<html>");
                out.write("<head>");
                out.write("<title>");
                out.write(testCaseName + " Detailed Reports");
                out.write("</title>");
                out.write("</head>");
                out.write("<body>");

                out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> Detailed Report :</h4>");
                out.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
                out.write("<tr> ");
                out.write("<td align=center width=10%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Step/Row#</b></td>");
                out.write("<td align=center width=70% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Error Description</b></td>");
                out.write("<td align=center width=20% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></td>");
                out.write("</tr>");

                int i = 0;
                out.write("<tr> ");

                out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>TS"
                        + (i + 1) + "</b></td>");
                out.write("<td width=75% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                        + teststatus + "</b></td>\n");

                if (screenShotPath.get(i) != null) {
                    out.write("<td align=center width=15%><FONT COLOR=#153E7E FACE=Arial SIZE=2><b><a href="
                            + screenShotPath.get(i)
                            + " target=_blank>Screen Shot</a></b></td>");
                } else {
                    out.write("<td align=center width=15%><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>&nbsp;</b></td>");
                }
                out.write("</tr>");
                out.close();
            }

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

            out.write("<tr>\n");
            out.write("<td width=5% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + scriptNumber + "</b></td>\n");
            if (status.equalsIgnoreCase("Pass") || status.equalsIgnoreCase("Ignore") ) {
                out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                    + testCaseName + "</b></td>\n");
            } else if (status.startsWith("Fail")){

                out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href="
                    + currentSuiteName
                    + "_TC"
                    + tcid
                    + "_"
                    + testCaseName.replaceAll(" ", "_")
                    + ".html>"
                    + testCaseName + "</a></b></td>\n");
            }

            out.write("<td width=35% align= center><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
                    + testCaseDesc + "</b></td>\n");
            if (status.startsWith("Pass")) {
                out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
                    + status + "</b></td>\n");
            } else if (status.startsWith("Fail")) {
                out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                    + status + "</b></td>\n");
            }else if (status.startsWith("Ignore")) {
                out.write("<td width=10% align= center  bgcolor=#c0c0c0><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                    + status + "</b></td>\n");
            }
            
            out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + testCaseStartTime + "</b></td>\n");
            out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
                + testCaseEndTime + "</b></td>\n");


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
				out.write("<td width=10% align= center bgcolor=#ffbfbf ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
	            		+String.format("%02d",diffInHours)+":"+ String.format("%02d",diffInMinutes)+":"+String.format("%02d",diffInSeconds)
	            		+ "</b></td>\n");
			}
			else
			{
				
			out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
            		+String.format("%02d",diffInHours)+":"+ String.format("%02d",diffInMinutes)+":"+String.format("%02d",diffInSeconds)
            		+ "</b></td>\n");
            }
			
            out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href="
                    + currentSuiteName
                    + "_TC_log"
                    + tcid
                    + "_"
                    + testCaseName.replaceAll(" ", "_")
                    + ".html>"
                    + testCaseName + "</a></b></td>\n");
            
            out.write("</tr>\n");
            reportAnalyzer.analyze(currentDir + "//" + currentSuiteName + "_TC_log"
            		+ tcid + "_" + testCaseName.replaceAll(" ", "_")
            		+ ".html", Float.parseFloat(SelTestCase.getCONFIG().getProperty("report_analysis_period")));

            tcid++;
            scriptNumber++;
            
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        description = new ArrayList<String>();
        screenShotPath = new ArrayList<String>();
        newTest = false;
        
        SelTestCase.getCurrentFunctionName(false);
    }

    /**
     * Adds the addError.
     *
     * @param stat
     *        the stat
     * @param path
     *        the path
     */
    public static void addError(
        final String stat,
        final String path) {

        teststatus = stat;
        screenShotPath.add(path);

    }

    /**
     * Update end time.
     *
     * @param endTime
     *        the end time
     */
    public static void updateEndTime(final String endTime, String testStat) {
        getCurrentFunctionName(true);
    	StringBuffer buf = new StringBuffer();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(indexResultFilename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // Read File Line By Line

            while ((strLine = br.readLine()) != null) {

                if (strLine.indexOf("end_time") != -1) {
                    //strLine = strLine.replace("END_TIME", endTime);
                	strLine=strLine.replaceAll("'end_time'><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>.*</b></td></tr><tr id = 'Done_end'>", "'end_time'><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+endTime+"</b></td></tr><tr id = 'Done_end'>");
                }
                if (strLine.indexOf("TEST_DATA") != -1) {
                    if (testStat == "Passed") {
                        strLine =
                            strLine.replace("TEST_DATA",
                                "<FONT COLOR=GREEN FACE= Arial  SIZE=2.75>"
                                    + testStat + "</FONT>");
                    } else if (testStat == "Failed") {
                        strLine =
                            strLine.replace("TEST_DATA",
                                "<FONT COLOR=RED FACE= Arial  SIZE=2.75>"
                                    + testStat + "</FONT>");
                    }

                }
                buf.append(strLine);
            }

            in.close();
            FileOutputStream fos = new FileOutputStream(indexResultFilename);
            DataOutputStream output = new DataOutputStream(fos);
            output.writeBytes(buf.toString());
            fos.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        getCurrentFunctionName(false);

    }

    /**
     * Now.
     *
     * @param dateFormat
     *        the date format
     * @return the string
     */
    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());

    }

    /**
     * Take screen shot.
     *
     * @param filePath
     *        the file path
     */
    public static void takeScreenShot(WebDriver driver, String filePath) {
        File scrFile;
        if (SelTestCase.getCONFIG().getProperty("browser").contains("android")) {
            Augmenter augmenter = new Augmenter();
             TakesScreenshot ts = (TakesScreenshot) augmenter.augment(driver);
             scrFile = ts.getScreenshotAs(OutputType.FILE);
        } else {
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        }

        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
