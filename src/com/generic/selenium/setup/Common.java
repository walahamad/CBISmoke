package com.generic.selenium.setup;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.selenium.report.ReportUtil;


public class Common extends SelTestCase {

    public static String expected = null;
    public static String actual = null;
    
    /**Reads URL from config.properties file
     * @throws Exception
     *
     *
     */
    public static void launchApplication() throws Exception {
    	getCurrentFunctionName(true);
    	
        logs.debug("Test environment is: " + SelTestCase.getCONFIG().getProperty("testEnvironment"));

        if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
        	logs.debug("signing out from all users");
        	logs.debug(getCONFIG().getProperty("logout"));
        	getDriver().get(getCONFIG().getProperty("logout"));
        	logs.debug("Removing all control cookies");
        	getDriver().manage().deleteAllCookies();
        }
		 getDriver().get(getCONFIG().getProperty("testSiteName"));
		 getDriver().manage().window().maximize();

        getCurrentFunctionName(false);
    }

    /**Explicit wait
     *
     *
     * @param waitTime
     */
    public static void wait(int waitTime) {

        try {
            logs.debug("wait for " + waitTime);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**Set test case status that will appear in the Automation Report
     *
     */
    public static void testPass() {
        setTestStatus("Pass");

    }

    /** Set test case status that will appear in the Automation Report
     *
     *
     */
    public static void testFail(Throwable t, String screenShotName) {
        setTestStatus("Fail: " + t.getMessage());
        setScreenShotName(screenShotName + ".jpg");
        ReportUtil.addError(getTestStatus(), getScreenShotName());
        logs.debug("Current URL: "+SelTestCase.getDriver().getCurrentUrl());
    }

    public static void testFailTemp(List<Exception> t, String screenShotName) {
        String temp = "";
        for ( int i = 0 ; i < t.size(); i++) {

            temp = temp + "Error " + i+1 + "----" + t.get(i).getMessage();
        }
        setTestStatus("Fail: " + temp);
        setScreenShotName(screenShotName + ".jpg");
        ReportUtil.addError(getTestStatus(), getScreenShotName());
    }

    /**Take the screen of the browser that will appear in Automation Report
     * @throws Exception
     *
     */
    public static void takeScreenShot() {
        if (GlobalVariables.flag) {
            captureScreen(subDir + "/" + getScreenShotName());
            GlobalVariables.flag = false;
            ReportUtil.takeScreenShot(getDriver(), subDir + "/" + getScreenShotName());
        }
    }

    /** Closes the opened browsers by selenium.
     *
     */
    public static void closeApplication() {
    	if (getCONFIG().getProperty("debug").equalsIgnoreCase("no"))
    	{
        ActionDriver.closeBrowser();
    	}
    }

    /**It compares the expected text with actual(read from application).
     * If they are not equal then it throws an error and fail the test case.
     *
     * @param expected
     * @param locator
     * @throws Exception
     */
    public static void verifyText(String expected, By locator) throws Exception {
        logs.debug("In verify Text function");
        String actual = "";
        int i = 1;
        WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
        try {
            logs.debug("Expected Text :" + expected);
            while (i <= getWaitTime()) {
                actual = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
                  logs.debug("ACTUAL TEXT IS:" + actual);
                   if (actual.contains(expected)) {
                       break;
                   }
                   logs.debug("Wait : " + i + " Sec");
                   Thread.sleep(1000);
                   i = i + 1;
            }
        } catch (Throwable t) {
              throw new Exception(locator + " is missing " + t);
        }

        if(i > getWaitTime()) {
            logs.debug("Error - >" + "Actual : " + actual + "Expected : " + expected);
            throw new Exception("Actual : " + actual + "Expected : " + expected);
        }
    }

    public static void killDriverServerIfRunning() throws Exception {
        String line;
        String pidInfo ="";
        Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            pidInfo+=line;
        }
        input.close();
        if (getCONFIG().getProperty("browser").equalsIgnoreCase("chrome")) {
            if(pidInfo.contains("chromedriver.exe"))
            {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                logs.debug("Killing chromeDriver.exe process");
            } else {
                logs.debug("chromeDriver.exe process is not running. Starting Scripts execution on chrome");
            }
        }

    }

    public static void captureScreen(String screenShotName) {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            ImageIO.write(image, "png", new File(screenShotName));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
	
}
