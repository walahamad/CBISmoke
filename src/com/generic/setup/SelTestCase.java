package com.generic.setup;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;
import com.generic.util.Xls_Reader;

public class SelTestCase {
	
	public static String time_date_format = "hh:mm:ss aaa";
	public static String reportFolderDateStampFormat = "MM-dd-yyyy";
	public static String reportFolderTimeStampFormat = "HHmmss";
	public static String testCaseRunDateStamp = "dd.MMMMM.yyyy";

    public static boolean runReportSetup = true;
    public static String mainDir = null;
    public static String subDir = null;
    
    public static Logger logs = Logger.getLogger("logs");
    public static int counter ;
    private static Properties CONFIG = null;
    private static WebDriver driver = null;
    private static int waitTime;
    private static Xls_Reader datatable = null;
    private static String testCaseId = null;
    private static String startTime = null;
    private static String testStatus = null;
    private static String screenShotName = null;
    private static String browserName = null;
    private static int testCaseRowNum;
    private static Throwable Error = null;
    
    public static String logDir= null;
    
    public static String testCaseRepotId = null;
    public static String testCaseDescription = null;
    
    public static int caseIndex;
    
    public static boolean initializeConfigurations = true;

    public static String getBrowserName() {
        return browserName;
    }

    public static void setBrowserName(String browserName) {
        SelTestCase.browserName = browserName;
    }

    public static String getScreenShotName() {
        return screenShotName;
    }


    public static void setScreenShotName(String screenShotName) {
        SelTestCase.screenShotName = screenShotName;
    }


    public static String getTestStatus() {
        return testStatus;
    }


    public static void setTestStatus(String testStatus) {
        SelTestCase.testStatus = testStatus;
    }

    public static String getStartTime() {
        return startTime;
    }


    public static void setStartTime(String startTime) {
        SelTestCase.startTime = startTime;
    }

    public static Xls_Reader getDatatable() {
        return datatable;
    }


    public static void setDatatable(Xls_Reader datatable) {
        SelTestCase.datatable = datatable;
    }
    
    public static String getTestCaseDescription() {
        return testCaseDescription;
    }


    public static void setTestCaseDescription(String testDescription) {
        testCaseDescription = testDescription;
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public static void setWaitTime(int time) {
        waitTime = time;
    }

    public static Properties getCONFIG() {
        return CONFIG;
    }

    public static void setCONFIG(Properties cONFIG) {
        CONFIG = cONFIG;
    }


    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        SelTestCase.driver = driver;
    }

    public static int getTestCaseRowNum() {
        return testCaseRowNum;
    }


    public static void setTestCaseRowNum(int testCaseRowNum) {
        SelTestCase.testCaseRowNum = testCaseRowNum;
    }
    
    public static String getTestCaseId() {
        return testCaseId;
    }

    public static void setTestCaseId(String testId) {
        testCaseId = testId;
    }

    public static void getCurrentFunctionName(Boolean start)
    {
    	if (start){
        	logs.debug(MessageFormat.format(LoggingMsg.STARTING_THREAD, Thread.currentThread().getStackTrace()[2]));
        	}
        	else
        	{
        		logs.debug(MessageFormat.format(LoggingMsg.ENDING_THREAD, Thread.currentThread().getStackTrace()[2]));
        	}
    	
	}
    
    
    /**setUp function will be invoked by Junit before execution of every test case.
     * It initializes the property files and html report setup
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception  {
    	getCurrentFunctionName(true);
        try {                   
            setTestCaseId(testCaseRepotId);
            setStartTime(ReportUtil.now(time_date_format));
            logs.debug(MessageFormat.format(LoggingMsg.COUNTER_VALUE, counter));
            counter = counter + 1;
            
            //Initialize the property file
            TestUtilities.configInitialization();
            TestUtilities.reportSetup(); // Initialize html report setup
            TestUtilities.prepareLogs();

        } catch (Exception e) {
            e.printStackTrace();
            Assume.assumeTrue(false);
        }

        logs.debug(MessageFormat.format(LoggingMsg.EXECUTE_TEST_CASE, getTestCaseId()));
        setTestCaseDescription(getDatatable().getCellData("Test Cases","Description", getTestCaseRowNum()));
        Common.initializeBrowser();

        try {
        	Common.launchApplication();

        } catch(Throwable t) {
            Error = t;
            logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t));
            throw new Exception(t);
        }
        getCurrentFunctionName(false);

    }


    /**tearDown function will be invoked by Junit after execution of every test case.
     * It adds the test case status into the html report and close the browser.
     * It reads email id from config file. If they are mentioned then framework will zip the html reports
     * and send it into corresponding email ids.
     * @throws Exception
     */
    @After
    public void tearDown() {
    	getCurrentFunctionName(true);
    	logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, getDriver().getCurrentUrl()));

        if(! Strings.isNullOrEmpty(getTestStatus())) {
        	ReportUtil.addTestCase(getTestCaseId() + "_" + counter, getTestCaseDescription(),
                    			   getStartTime(),ReportUtil.now(time_date_format),getTestStatus());
        } else if (Strings.isNullOrEmpty(getTestStatus())) {
            String temp = getTestCaseId() + "_" + counter;
            Common.testFail(Error, temp);
            Common.takeScreenShot();
            ReportUtil.addTestCase(getTestCaseId() + "_" + counter, getTestCaseDescription(),
                    			   getStartTime(),ReportUtil.now(time_date_format),getTestStatus().substring(0, 4));
        }
        Common.closeApplication();
        ReportUtil.updateEndTime(ReportUtil.now(time_date_format), getTestStatus());
        try {
        	if (getCONFIG().getProperty("browser").equalsIgnoreCase("chrome"))
        	{
        		logs.debug(MessageFormat.format(LoggingMsg.TERMINATING_DRIVERS, "chrome"));
            	Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        	}
        	logs.debug(MessageFormat.format(LoggingMsg.TEST_CASE_STATUS, getTestCaseId(), getTestStatus()));
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        getCurrentFunctionName(false);
    }
}
