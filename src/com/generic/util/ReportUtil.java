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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.generic.setup.EnvironmentFiles;
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
    
    /** The html detailed report for failed test case filename. */
    public static String failedTestCaseFileName;

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


    /**
     * Delete dir.
     * Deletes all files and subdirectories under dir.
     * Returns true if all deletions were successful.
     * If a deletion fails, the method stops attempting to delete and returns
     * false.
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
    public static void startTesting(final String filename, final String testStartTime, final String env, final String browser) {
    	currentSuiteName = getCONFIG().getProperty("testSuiteName").replaceAll(" ", "_");
        indexResultFilename = filename;
        currentDir = indexResultFilename.substring(0,indexResultFilename.lastIndexOf("//"));
        logDir = currentDir;
        ReportUtil.deleteDir(new File(currentDir));
        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            // Create file
            fstream = new FileWriter(filename);
            out = new BufferedWriter(fstream);

            String rUNDATE = ReportUtil.now(SelTestCase.testCaseRunDateStamp).toString();

            String eNVIRONMENT = env;

            // Added so the report will show the browser type.
            String rBrowserType = browser;

            ReportResultsWriter.writeReportTestDetails(testStartTime, out, rUNDATE, eNVIRONMENT, rBrowserType, getCONFIG().getProperty("testSuiteName"));
            
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
            out.write("</body>\n");
            out.write("</html>");
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
    	String logs_dir = EnvironmentFiles.getLogFilePath();
		String log_file = EnvironmentFiles.getLogFileName();
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
    public static void addTestCase(final String testCaseName, final String testCaseDesc, final String testCaseStartTime,
    							   final String testCaseEndTime, final String status) {
    	SelTestCase.getCurrentFunctionName(true);
    	logs.debug("Adding a "+ status +" case to report");
        newTest = true;
        FileWriter fstream = null;
        BufferedWriter out = null;
        
        
        File log_file = new File(currentDir + "//" + currentSuiteName + "_TC_log"
                + tcid + "_" + testCaseName.replaceAll(" ", "_") + ".html");
        try {
			copyLogsFile(log_file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
            newTest = true;
            if (status.startsWith("Fail")) {
            	failedTestCaseFileName = currentDir + "//" + currentSuiteName + "_TC" + tcid + "_" + testCaseName.replaceAll(" ", "_") + ".html"; 
                File f = new File(failedTestCaseFileName);
                f.createNewFile();
                fstream = new FileWriter(failedTestCaseFileName);
                ReportResultsWriter.generateFailedTestCasePage(testCaseName, fstream);
            }

            fstream = new FileWriter(indexResultFilename, true);
            out = new BufferedWriter(fstream);

            ReportResultsWriter.addExecutedTestCaseToIndex(testCaseName, testCaseDesc, testCaseStartTime, testCaseEndTime, status, out);

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
    public static void addError(final String stat,final String path) {
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
                	strLine=strLine.replaceAll("'end_time'><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>.*</b></td></tr><tr id='Done_end'>", 
                			"'end_time'><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>" +endTime+ "</b></td></tr><tr id='Done_end'>");
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
