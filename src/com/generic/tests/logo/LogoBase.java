package com.generic.tests.logo;

import java.text.MessageFormat;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.HomePage;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class LogoBase extends SelTestCase {

	public static final String logoSelector = "//*[@id=\"logo1\"]/a";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.VisualTestingHPRegressionsheet;

	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ; 

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	
	@DataProvider(name = "Logo", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}


	@Test (dataProvider = "Logo")
	public void LogoRegressionTest(String caseId, String runTest, String desc, String proprties, String baseline) throws Exception {
		Testlogs.set(new SASLogger("Logo"+getBrowserName()));
		//Important to add this for logging/reporting 
		setTestCaseReportName(SheetVariables.LogoValidationTestId);
		//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		try {
			String url = PagesURLs.getHomePage();
			getDriver().get(url);
			Thread.sleep(1500);
			boolean isLogoDisplayed = getDriver().findElement(By.xpath(logoSelector)).isDisplayed();
			if (!isLogoDisplayed)
		    {
		         System.out.println("Image not displayed.");
				sassert().assertTrue(isLogoDisplayed,"Logo not displayed.");
		    }else{
		         System.out.println("Image  displayed.");
				sassert().assertTrue(isLogoDisplayed,"Logo  displayed.");
		    }
			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test
}
