package com.generic.selenium.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.selenium.report.ReportUtil;

/**
 * This class is responsible for GUI interaction. It contains functions like type, click , select etc
 *
 */
public class ActionDriver extends SelTestCase{


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


    /**This function will fetch the element from the application.
     *
     * @param locator
     * @return
     * @throws Exception
     */
    public static WebElement getElement(By locator) throws Exception {
        try{
//        	if(SelTestCase.getCONFIG().getProperty("browser").equalsIgnoreCase("Chrome")){
//        	Thread.sleep(3000);
//        	}
        	logs.debug("get elemet by locator " + locator.toString());
            //explicit wait
            return waitForElementPresent(locator);
        } catch(Throwable t){
            throw new Exception(t);
        }
    }


   




    /** It waits for element to load
     *
     * @param locator
     * @return
     * @throws Exception
     * @throws TimeoutException
     */
    public static WebElement waitForElementPresent(final By locator) throws Exception, TimeoutException {
        WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
   

    public static boolean waitForElementNotPresent(final By locator, int waitTime) throws Exception, TimeoutException {
      try {
          WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), waitTime);
          wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
          return true;
      } catch(Exception e) {
          return false;
      }


    }

    /** It select the option from a drop down by using text
     *
     * @param locator
     * @param testData
     * @throws Exception
     */
    public static void selectByText(By locator, String testData) throws Exception {
        WebElement element;
        try {
            element = getElement(locator);
            Select s2 = new Select(element);
            s2.selectByVisibleText(testData);

        } catch (Throwable t) {
            throw new Exception(t);
        }
    }

    /**It select the option from a drop down by value
     *
     * @param locator
     * @param testData
     * @throws Exception
     */
    public static void selectByValue(By locator, String testData) throws Exception {
        WebElement element;
        try {
            element = getElement(locator);
            Select s2 = new Select(element);
            s2.selectByValue(testData);

        } catch (Throwable t) {
            throw new Exception(t);
        }
    }

    /** Returns true if element is present else false
     *
     */
    public static boolean isElementPresent(By locator) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Throwable t) {
            //t.printStackTrace();
            return false;
        }
    }

    /** It closes the browser
     *
     */
    public static void closeBrowser() {
        logs.debug("Terminating session ");

        if (getDriver() != null) {
            try {
            	SelTestCase.getDriver().quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SelTestCase.setDriver(null);
        }       
    }

    /**It will type the test data into an input box
     *
     * @param locator
     * @param testData
     * @throws Exception
     */
    public static void type(By locator, String testData) throws Exception {
    	logs.debug("Writing: "+ testData +" to: " + locator.toString());
        WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
        WebElement element = null;
        element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.clear();
        element.sendKeys(testData);
    }

    public static void forceType(By locator, String testData) throws Exception {
    	logs.debug("Force Writing: "+ testData +" to: " + locator.toString());
        WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
        WebElement element = null;
        element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ActionDriver.click (locator);
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a")+testData);
    }

    /** It clicks on web element
     *
     * @param locator
     * @throws Exception
     */
    public static void click(By locator) throws Exception {
        try {
        	logs.debug("Click element " + locator.toString());
        	getElement(locator).click();
        }catch (Throwable t) {
            //t.printStackTrace();
            throw new Exception(locator +" is missing " + t);
        }
    }

 

    /** It clicks on web element
     *
     * @param locator
     * @throws Exception
     */
    public static void submitForm(By locator) throws Exception {

        try {
            getElement(locator).submit();
        }catch (Throwable t) {
            throw new Exception(locator +" is missing " + t);
        }
    }

    public static void mouseHover(By locator) throws Exception {
        Actions action = new Actions(getDriver());
        action.moveToElement(ActionDriver.getElement(locator)).
        build().perform();
    }

    public static void doubleClick(By locator) throws Exception {
        Actions action = new Actions(getDriver());
        action.doubleClick(ActionDriver.getElement(locator));
        action.perform();
    }

    public static void setImplicitWaitOnDriver(int maxWaitTime) {
        SelTestCase.getDriver().manage().timeouts().implicitlyWait(maxWaitTime, TimeUnit.SECONDS);
    }
}
