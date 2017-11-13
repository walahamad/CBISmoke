package com.generic.setup;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;


public class Common extends SelTestCase {

    public static String expected = null;
    public static String actual = null;
    
    /** It initialize the webdriver object with Chrome
	 *
	 * @throws Exception
	 */
	public static void initializeBrowser() throws Exception {
		getCurrentFunctionName(true);
	    try {
	    	logs.debug("the browser is: " + SelTestCase.getCONFIG().getProperty("browser").toString());
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        
	        if(SelTestCase.getCONFIG().getProperty("browser").equalsIgnoreCase("chrome")){
	        	
	            capabilities = DesiredCapabilities.chrome();
	            capabilities.setCapability("platform", "WINDOWS");
	            capabilities.setBrowserName("chrome");
	            
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("disable-extensions");
	            options.addArguments("--start-maximized");
	            
	            
	            if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")){
	            	//options.addArguments("user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome/User Data/");
	            	options.addArguments("user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome SxS/User Data/");
	            	options.addArguments("detach=true");
	            }
	            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	
	            
	            
	            System.setProperty("webdriver.chrome.driver", "C:/softwares/servers/chromedriver.exe");
	            SelTestCase.setDriver(new ChromeDriver(capabilities));
	
	            logs.debug(SelTestCase.getDriver().toString());
	            
	            
	        } else {
	            logs.debug("Invalid browser. Check the config file");
	            throw new Exception("Invalid browser. Check the config file");
	        }
	      
	    } catch(Throwable t) {
	        t.printStackTrace();
	        SelTestCase.setTestStatus("Fail: " + t.getMessage());
	        SelTestCase.setStartTime(ReportUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));
	        ReportUtil.addError(SelTestCase.getTestStatus(), null);
	        throw new Exception(t);
	    }
	    getCurrentFunctionName(false);
	}

	/**Reads URL from config.properties file
     * @throws Exception
     *
     *
     */
    public static void launchApplication() throws Exception {
    	getCurrentFunctionName(true);
    	
        logs.debug("Test environment is: " + SelTestCase.getCONFIG().getProperty("testEnvironment"));

        if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
        	//TODO: please enable it later with correct url 
        	logs.debug("Please enable this block for future");
//        	logs.debug("signing out from all users");
//        	logs.debug(getCONFIG().getProperty("logout"));
//        	getDriver().get(getCONFIG().getProperty("logout"));
//        	logs.debug("Removing all control cookies");
//        	getDriver().manage().deleteAllCookies();
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

	public static LinkedHashMap<String, Object> readAddresses() {
		/* output example
		 * [
			  {
			    A1={
			      firstName=Accept,
			      lastName=Tester,
			      adddressLine=49FeatherstoneStreet,
			      city=LONDON,
			      postal=EC1Y8SY,
			      countery=UNITEDKINGDOM
			    },
			    A2={
			      firstName=Accept,
			      lastName=Tester,
			      adddressLine=ArdenhamCourt,
			      city=LONDON,
			      postal=HP193EQ,
			      countery=UNITEDKINGDOM
			    }
			  }
			]
		 */
		
		LinkedHashMap<String, Object> addresses = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.addresses, 1);
		
		//data map
		int header = 0;
		int addresscode = 0;
		int firstName = 1;
		int lastName = 2;
		int addressLine = 3;
		int city = 4;
		int postal = 5;
		int countery = 6;
		
		for (int row = 1; row < data.length; row++)
		{
			LinkedHashMap<String, Object> address = new LinkedHashMap<>();
			address.put((String) data[header][firstName], data[row][firstName]);
			address.put((String) data[header][lastName], data[row][lastName]);
			address.put((String) data[header][addressLine], data[row][addressLine]);
			address.put((String) data[header][city], data[row][city]);
			address.put((String) data[header][postal], data[row][postal]);
			address.put((String) data[header][countery], data[row][countery]);
			
			addresses.put((String) data[row][addresscode], address);
		}
		return addresses;
	}//readAddresses

	public static LinkedHashMap<String, Object> readProducts() {
		/* Output example
		 * [
			  {
			    P1={
			      url=/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-Satchel/p/300613490,
			      color=black,
			      size=SizeUni,£34.792 1,
			      qty=1
			    },
			    P2={
			      url=/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-Bag/p/300441924,
			      color=claycourt,
			      size=SizeUni, £24.26 4,
			      qty=1
			    }
			  }
			]
		 */
		LinkedHashMap<String, Object> products = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.products, 1);
		
		//data map
		int header = 0;
		int name = 0;
		int url = 1;
		int color = 2;
		int size = 3;
		int qty = 4;
		
		for (int row = 1; row < data.length; row++)
		{
			LinkedHashMap<String, Object> product = new LinkedHashMap<>();
			product.put((String) data[header][url], data[row][url]);
			product.put((String) data[header][color], data[row][color]);
			product.put((String) data[header][size], data[row][size]);
			product.put((String) data[header][qty], data[row][qty]);
			
			products.put((String) data[row][name], product);
		}
		return products;
	}//readProducts
	
}
