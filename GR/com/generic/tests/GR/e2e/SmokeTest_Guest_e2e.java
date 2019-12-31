package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.tests.GR.e2e.Cart_e2e;
import com.generic.tests.GR.e2e.Checkout_e2e;
import com.generic.tests.GR.e2e.HomePage_e2e;
import com.generic.tests.GR.e2e.PDP_e2e;
import com.generic.tests.GR.e2e.Search_PLP_e2e;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class SmokeTest_Guest_e2e extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.smokeTesting;
	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	public static boolean external = false; // change this value will pass through logging

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("checkout_setup"));
		testObject = test;
	}

	@DataProvider(name = "Orders", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Orders")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String productsNumber,
			String shippingMethod, String payment, String shippingAddress, String billingAddress, String email)
			throws Exception {

		Testlogs.set(new SASLogger("checkout_" + getBrowserName()));
		setTestCaseReportName("Checkout Case");
		String CaseDescription = MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc.replace("\n", "<br>--"));
		initReportTime();

		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get(shippingAddress);
		LinkedHashMap<String, String> paymentDetails = (LinkedHashMap<String, String>) paymentCards.get(payment);
		LinkedHashMap<String, String> userdetails = (LinkedHashMap<String, String>) users.get(email);

		int productsCount = Integer.parseInt(productsNumber);

		try {

			Common.refreshBrowser();
			HomePage_e2e.Validate();
			Search_PLP_e2e.Validate();
			PDP_e2e.Validate();
			Cart_e2e.Validate();
			Checkout_e2e.ValidateGuest(productsCount, addressDetails, paymentDetails, userdetails);

			sassert().assertAll();
			logCaseDetailds(CaseDescription);
			Common.testPass();

		} catch (Throwable t) {
			logCaseDetailds(CaseDescription + "<br><b><font color='red'>Failure Reason: </font></b>"
					+ t.getMessage().replace("\n", "").trim());
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test
}// class
