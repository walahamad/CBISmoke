package com.generic.setup;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class Common extends SelTestCase {

	public static String expected = null;
	public static String actual = null;

	public static class DataSheetConstants {
	}

	public static WebDriver initializeBrowser(String browser) throws Exception {
		getCurrentFunctionName(true);
		try {
			logs.debug(MessageFormat.format(LoggingMsg.BROWSER_NAME,browser));
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//String browser = "chrome";//SelTestCase.getCONFIG().getProperty("browser");
			WebDriver driver;
			if (browser.equalsIgnoreCase("edge")) {
				// TODO: move this path to path file or configuration file
				System.setProperty("webdriver.edge.driver", "C:/softwares/servers/MicrosoftWebDriver.exe");

				//capabilities = DesiredCapabilities.edge();
				//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//capabilities.setJavascriptEnabled(true);
				return new EdgeDriver(new EdgeOptions());
				
			}else if (browser.equalsIgnoreCase("Firefox")) {
				
				System.setProperty("webdriver.gecko.driver","C:\\softwares\\servers\\geckodriver.exe");
				
				LoggingPreferences pref = new LoggingPreferences();
			    pref.enable(LogType.BROWSER, Level.OFF);
			    pref.enable(LogType.CLIENT, Level.OFF);
			    pref.enable(LogType.DRIVER, Level.OFF);
			    pref.enable(LogType.PERFORMANCE, Level.OFF);
			    pref.enable(LogType.PROFILER, Level.OFF);
			    pref.enable(LogType.SERVER, Level.OFF);
				
			    
				//DesiredCapabilities dc=DesiredCapabilities.firefox();
				//FirefoxProfile profile = new FirefoxProfile();
//				dc.setCapability(FirefoxDriver.PROFILE, profile);
//				dc.setCapability("marionette", true);
//				dc.setCapability(CapabilityType.LOGGING_PREFS, pref);
		        
				FirefoxOptions fo = new FirefoxOptions();
				driver = new FirefoxDriver(fo);
				driver.manage().timeouts().pageLoadTimeout(120,TimeUnit.SECONDS);
				return driver;
				
//				System.setProperty("webdriver.gecko.driver","C:/softwares/servers/geckodriver.exe");
//				
//				FirefoxOptions options = new FirefoxOptions();
//				options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); //Location where Firefox is installed
//		 
//				capabilities = DesiredCapabilities.firefox();
//				capabilities.setCapability("moz:firefoxOptions", options);
//				//set more capabilities as per your requirements
//				FirefoxProfile profile = new FirefoxProfile();
//				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//				capabilities.setCapability("marionette", true);
//		 
//				return new FirefoxDriver(capabilities);
				
			}else if (browser.equalsIgnoreCase("IE")) {
				// TODO: move this path to path file or configuration file
				System.setProperty("webdriver.ie.driver", "C:/softwares/servers/IEDriverServer.exe");
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

				SelTestCase.setDriver(new InternetExplorerDriver(capabilities));

			} else if (browser.equalsIgnoreCase("chrome")) {

				//capabilities = DesiredCapabilities.chrome();
				//capabilities.setCapability("platform", "WINDOWS");
				//capabilities.setBrowserName("chrome");

				ChromeOptions options = new ChromeOptions();

//				if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
//					// options.addArguments("user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome/User
//					// Data/");
//					// TODO: move this path to path file or configuration file as google cashed
//					// profile path
//					options.addArguments("user-data-dir=" + System.getProperty("user.home")
//							+ "/AppData/Local/Google/Chrome SxS/User Data/");
//					options.addArguments("detach=true");
//				}

				//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				// TODO: move this path to path file or configuration
				System.setProperty("webdriver.chrome.driver", "C:/softwares/servers/chromedriver.exe");
				ChromeOptions co = new ChromeOptions();
				driver = new ChromeDriver(co);
				//SelTestCase.setDriver(driver);
				return driver;

			}  else if (browser.equalsIgnoreCase("chrome2")) {

				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("platform", "WINDOWS");
				capabilities.setBrowserName("chrome");

				ChromeOptions options = new ChromeOptions();

//				if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
//					// options.addArguments("user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome/User
//					// Data/");
//					// TODO: move this path to path file or configuration file as google cashed
//					// profile path
//					options.addArguments("user-data-dir=" + System.getProperty("user.home")
//							+ "/AppData/Local/Google/Chrome SxS/User Data/");
//					options.addArguments("detach=true");
//				}

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				// TODO: move this path to path file or configuration
				System.setProperty("webdriver.chrome.driver", "C:/softwares/servers/chromedriver2.exe");
				driver = new ChromeDriver(capabilities);
				//SelTestCase.setDriver(driver);
				return driver;

			}else if (browser.contains("BS")) {
				// Configuration format: BS-iPhone 7 Plus-Safari
				String mDevice = browser.split("-")[1];
				String mBrowser = browser.split("-")[1];

				// TODO: move them to configuration file
				final String USERNAME = "ibrahembatta1";
				final String AUTOMATE_KEY = "bwziBLydxfZjPFyR5jsT";
				final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("browser", mBrowser);
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.safari.allowAllCookies", "true");
				caps.setCapability("realMobile", "true");
				caps.setCapability("device", mDevice);
				caps.setCapability("build", "First build");

				// devices tests
				// caps.setCapability("browserName", "iPhone");
				// caps.setCapability("platform", "MAC");
				// caps.setCapability("device", "iPhone 6");

				// caps.setCapability("browser", "chrome");
				// caps.setCapability("browser_version", "53");
				// caps.setCapability("os", "Windows");
				// caps.setCapability("os_version", "10");

				// caps.setCapability("browser", "IE");
				// caps.setCapability("browser_version", "11.0");
				// caps.setCapability("os", "Windows");
				// caps.setCapability("os_version", "7");
				// caps.setCapability("resolution", "1024x768");

				// browser stack configurations
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.local", "true");
				caps.setCapability("acceptSslCerts", "true");

				SelTestCase.setDriver(new RemoteWebDriver(new URL(URL), caps));

			} else if (browser.equalsIgnoreCase("safari_grid")) {
				SafariOptions options = new SafariOptions();
				// options.setUseCleanSession(true);
				capabilities = DesiredCapabilities.safari();
				capabilities.setCapability(SafariOptions.CAPABILITY, options);
				// capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				// capabilities.setPlatform(Platform.MAC);
				// capabilities.setCapability("ensureCleanSession", true);
				// TODO: change it and setup grid server
				SelTestCase.setDriver(new RemoteWebDriver(new URL("http://10.20.20.54:4444/wd/hub"), capabilities));
			} else if (browser.contains("mobile")) {
				String mobile = browser.split("_")[1];
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("platform", "WINDOWS");
				capabilities.setBrowserName("chrome");

				Map<String, String> mobileEmulation = new HashMap<String, String>();
				mobileEmulation.put("deviceName", mobile);
				Map<String, Object> chromeOptions = new HashMap<String, Object>();
				chromeOptions.put("mobileEmulation", mobileEmulation);

				ChromeOptions options = new ChromeOptions();

				if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
					options.addArguments("user-data-dir=" + System.getProperty("user.home")
							+ "/AppData/Local/Google/Chrome SxS/User Data/");
					options.addArguments("detach=true");
				}

				options.setExperimentalOption("mobileEmulation", mobileEmulation);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver", "C:/softwares/servers/chromedriver.exe");
				return new ChromeDriver(capabilities);

			} else {
				logs.debug(LoggingMsg.INVALID_BROWSER_ERROR_MSG);
				throw new Exception(LoggingMsg.INVALID_BROWSER_ERROR_MSG);
			}

		} catch (Throwable t) {
			t.printStackTrace();
			SelTestCase.setTestStatus("Fail: " + t.getMessage());
			SelTestCase.setStartTime(ReportUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));
			ReportUtil.addError(SelTestCase.getTestStatus(), null);
			throw new Exception(t);
		}
		getCurrentFunctionName(false);
		return null;
	}

	/**
	 * Reads URL from config.properties file
	 * 
	 * @throws Exception
	 *
	 *
	 */
	public static void launchApplication(String Browser) throws Exception {
		getCurrentFunctionName(true);

		logs.debug(MessageFormat.format(LoggingMsg.TEST_ENVIRONMENT_NAME,
				SelTestCase.getCONFIG().getProperty("testEnvironment")));

		if (getCONFIG().getProperty("chached_chrome").equalsIgnoreCase("yes")) {
			// TODO: please enable it later with correct url
			logs.debug(LoggingMsg.ENABLE_BLOCK_FOR_FUTURE);
			 logs.debug("signing out from all users");
			 logs.debug(getCONFIG().getProperty("logout"));
			 getDriver().get(getCONFIG().getProperty("logout"));
			// logs.debug("Removing all control cookies");
			// getDriver().manage().deleteAllCookies();
		}
		getDriver().get(getCONFIG().getProperty("testEnvironment"));
		getDriver().manage().window().maximize();
		if (Browser.contains("IE") || Browser.contains("edge")) {
			Thread.sleep(2000);
			getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
		}
		getCurrentFunctionName(false);
	}

	/**
	 * Explicit wait
	 *
	 *
	 * @param waitTime
	 */
	public static void wait(int waitTime) {

		try {
			logs.debug(MessageFormat.format(LoggingMsg.WAIT_FOR_TIME, waitTime));
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set test case status that will appear in the Automation Report
	 *
	 */
	public static void testPass() {
		logs.debug("Test Status: PASS");
		setTestStatus("Pass");

	}

	public static void testIgnored() {
		logs.debug("Test Status: Ignored");
		setTestStatus("Ignore");

	}

	/**
	 * Set test case status that will appear in the Automation Report
	 *
	 *
	 */
	public static void testFail(Throwable t, String screenShotName) {
		logs.debug("Test Status: Failed");
		setTestStatus("Fail: " + t.getMessage());
		setScreenShotName(screenShotName + "_" + counter + ".jpg");
		ReportUtil.addError(getTestStatus(), getScreenShotName());
		logs.debug(MessageFormat.format(LoggingMsg.CURRENT_URL, SelTestCase.getDriver().getCurrentUrl()));
	}

	public static void testFailTemp(List<Exception> t, String screenShotName) {
		String temp = "";
		for (int i = 0; i < t.size(); i++) {

			temp = temp + "Error " + i + 1 + "----" + t.get(i).getMessage();
		}
		setTestStatus("Fail: " + temp);
		setScreenShotName(screenShotName + ".jpg");
		ReportUtil.addError(getTestStatus(), getScreenShotName());
	}

	/**
	 * Closes the opened browsers by selenium.
	 *
	 */
	public static void closeApplication() {
		if (getCONFIG().getProperty("debug").equalsIgnoreCase("no")) {
			ActionDriver.closeBrowser();
		}
	}

	public static void refreshBrowser() {

		ActionDriver.refreshBrowser();
	}
//	/**
//	 * It compares the expected text with actual(read from application). If they are
//	 * not equal then it throws an error and fail the test case.
//	 *
//	 * @param expected
//	 * @param locator
//	 * @throws Exception
//	 */
//	public static void verifyText(String expected, By locator) throws Exception {
//		logs.debug(MessageFormat.format(LoggingMsg.FUNCTION_NAME, "In verify Text function"));
//		String actual = "";
//		int i = 1;
//		WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
//		try {
//			logs.debug(MessageFormat.format(LoggingMsg.EXPECTED_TEXT, expected));
//			while (i <= getWaitTime()) {
//				actual = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
//				logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT, actual));
//				if (actual.contains(expected)) {
//					break;
//				}
//				logs.debug(MessageFormat.format(LoggingMsg.WAIT_SECONDS, i));
//				Thread.sleep(1000);
//				i = i + 1;
//			}
//		} catch (Throwable t) {
//			throw new Exception(locator + " is missing " + t);
//		}
//
//		if (i > getWaitTime()) {
//			logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, actual, expected));
//			throw new Exception("Actual : " + actual + "Expected : " + expected);
//		}
//	}

	public static void killDriverServerIfRunning() throws Exception {
		String line;
		String pidInfo = "";
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			pidInfo += line;
		}
		input.close();
		if (getCONFIG().getProperty("browser").equalsIgnoreCase("chrome")) {
			if (pidInfo.contains("chromedriver.exe")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				logs.debug(MessageFormat.format(LoggingMsg.KILLING_PROCESS, "chromeDriver.exe"));
			} else {
				logs.debug(
						MessageFormat.format(LoggingMsg.NOT_RUNNING_PROCESS_ERROR_MSG, "chromeDriver.exe", "chrome"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LinkedHashMap<String, Object> readAddresses() {
		/*
		 * output example [ { A1={ firstName=Accept, lastName=Tester, title=MR.,
		 * adddressLine=49FeatherstoneStreet, city=LONDON, postal=EC1Y8SY,
		 * countery=UNITEDKINGDOM }, A2={ firstName=Accept, lastName=Tester, title=MR.,
		 * adddressLine=ArdenhamCourt, city=LONDON, postal=HP193EQ,
		 * countery=UNITEDKINGDOM } } ]
		 */

		LinkedHashMap<String, Object> addresses = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.addresses, 1);

		// data map
		int header = 0;
		int addresscode = 0;
		int firstName = 1;
		int lastName = 2;
		int title = 3;
		int addressLine = 4;
		int city = 5;
		int postal = 6;
		int countery = 7;
		int phone = 8;

		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, Object> address = new LinkedHashMap<>();
			address.put((String) data[header][firstName], data[row][firstName]);
			address.put((String) data[header][lastName], data[row][lastName]);
			address.put((String) data[header][title], data[row][title]);
			address.put((String) data[header][addressLine], data[row][addressLine]);
			address.put((String) data[header][city], data[row][city]);
			address.put((String) data[header][postal], data[row][postal]);
			address.put((String) data[header][countery], data[row][countery]);
			address.put((String) data[header][phone], data[row][phone]);

			addresses.put((String) data[row][addresscode], address);
		}
		logs.debug(Arrays.asList(addresses)+"");
		return addresses;
	}// readAddresses

	public static LinkedHashMap<String, Object> readLocalInventory() {
		/*
		 * Output example [ { P1={
		 * url=/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-
		 * Satchel/p/300613490, color=black, size=SizeUni,£34.792 1, qty=1 }, P2={
		 * url=/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-Bag/p
		 * /300441924, color=claycourt, size=SizeUni, £24.26 4, qty=1 } } ]
		 */
		LinkedHashMap<String, Object> products = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.products, 1);

		// data map
		int header = 0;
		int name = 0;
		int url = 1;
		int color = 2;
		int size = 3;
		int qty = 4;

		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, Object> product = new LinkedHashMap<>();
			product.put((String) data[header][url], data[row][url]);
			product.put((String) data[header][color], data[row][color]);
			product.put((String) data[header][size], data[row][size]);
			product.put((String) data[header][qty], data[row][qty]);

			products.put((String) data[row][name], product);
		}
		logs.debug(Arrays.asList(products)+"");
		return products;
	}// readProducts

	public static LinkedHashMap<String, Object> readPaymentcards() {
		/*
		 [
		  {
		    visa={
		      number=4111111111111111,
		      name=AcceptTester,
		      expireYear=2022,
		      expireMonth=6,
		      CVCC=333
		    },
		    master={
		      number=5555555555554444,
		      name=Accept*Tester,
		      expireYear=2022,
		      expireMonth=6,
		      CVCC=334
		    }
		  }
		]
		 */
		LinkedHashMap<String, Object> cards = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.cards, 1);

		// data map
		int header = 0;
		int card = 0;
		int number = 1;
		int name = 2;
		int expireMonth = 3;
		int expireYear = 4;
		int CVCC = 5;

		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, Object> cardOb = new LinkedHashMap<>();
			cardOb.put((String) data[header][number], data[row][number]);
			cardOb.put((String) data[header][name], data[row][name]);
			cardOb.put((String) data[header][expireMonth], data[row][expireMonth]);
			cardOb.put((String) data[header][expireYear], data[row][expireYear]);
			cardOb.put((String) data[header][CVCC], data[row][CVCC]);

			cards.put((String) data[row][card], cardOb);
		}
		logs.debug(Arrays.asList(cards)+"");
		return cards;
	}// read payments

	public static LinkedHashMap<String, Object> readTestparams(String testSheet, int caseIndex) {
		/*
		 * [
			  {
			    desc=loggedinuserwithsavedmaistropaymentandshippingaddress,
			    proprties=loggedin,
			    products=P2,
			    shippingMethod=STANDARDDELIVERY,
			    payment=maistro,
			    shippingAddress=A1,
			    billingAddress=A2,
			    coupon=,
			    email=ibatta@dbi.com,
			    orderId=,
			    orderTotal=,
			    orderSubtotal=,
			    orderTax=,
			    orderShipping=
			  }
			]
		 */
		LinkedHashMap<String, Object> tests = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(testSheet, 1);

		// data map
		int header = 0;
		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, String> params = new LinkedHashMap<>();
			for (int col = 1; col < data[0].length; col++) {
				params.put((String) data[header][col], (String) data[row][col]);
			} // for col
			tests.put(Integer.toString(row), params);
		} // for rows
		return tests;
	}// read test param

	public static LinkedHashMap<String, Object> readUsers() {
		/*
		[
		  {
		    ibatta@dbi.com={
		      name=U1,
		      title=MR.,
		      username=ibatta,
		      firstName=Accept,
		      lastName=Tester,
		      password=1234567,
		      mail=ibatta@dbi.com
		    }
		  }
		]
		 */
		LinkedHashMap<String, Object> users = new LinkedHashMap<>();
		Object[][] data = TestUtilities.getData(SheetVariables.users, 1);

		// data map
		int header = 0;
		int name = 0;
		int title = 1;
		int userName = 2;
		int firstName = 3;
		int lastName = 4;
		int password = 5;
		int mail = 6;

		for (int row = 1; row < data.length; row++) {
			LinkedHashMap<String, Object> user = new LinkedHashMap<>();
			user.put((String) data[header][name], data[row][name]);
			user.put((String) data[header][title], data[row][title]);
			user.put((String) data[header][userName], data[row][userName]);
			user.put((String) data[header][firstName], data[row][firstName]);
			user.put((String) data[header][lastName], data[row][lastName]);
			user.put((String) data[header][password], data[row][password]);
			user.put((String) data[header][mail], data[row][mail]);

			users.put((String) data[row][mail], user);
		}
		logs.debug(Arrays.asList(users)+"");
		return users;
	}//read users

	public static void takeScreenShot() {
		// TODO Auto-generated method stub
		ReportUtil.takeScreenShot(getDriver());
	}

}// class
