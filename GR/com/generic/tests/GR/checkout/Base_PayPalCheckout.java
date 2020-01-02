package com.generic.tests.GR.checkout;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.dataProviderUtils;
import com.generic.util.SASLogger;

public class Base_PayPalCheckout extends SelTestCase {

	// user types
	public static final String registeredUserPayPal = "registered-PayPal";
	public static final String freshdUserPayPal = "fresh-PayPal";

	public static boolean external = false; // change this value will pass through logging

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PayPalcheckoutSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

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
			String payment, String email) throws Exception {

		Testlogs.set(new SASLogger("checkout_" + getBrowserName()));
		setTestCaseReportName("Checkout Case");
		String CaseDescription = MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc.replace("\n", "<br>--"));
		initReportTime();

		LinkedHashMap<String, String> userdetails = (LinkedHashMap<String, String>) users.get(email);
		LinkedHashMap<String, String> paymentDetails = (LinkedHashMap<String, String>) paymentCards.get(payment);
		int productsCount = Integer.parseInt(productsNumber);
		try {


			if (proprties.contains(freshdUserPayPal)) {
				PayPalValidation.validate(freshdUserPayPal, productsCount, userdetails, paymentDetails);
			}

			if (proprties.contains(registeredUserPayPal)) {
				PayPalValidation.validate(registeredUserPayPal, productsCount, userdetails, paymentDetails);
			}

			sassert().assertAll();

			Common.testPass(CaseDescription);
		} catch (Throwable t) {
			if ((getTestStatus() != null) && getTestStatus().equalsIgnoreCase("skip")) {
				throw new SkipException("Skipping this exception");
			} else {
				Common.testFail(t, CaseDescription, testDataSheet + "_" + caseId);
			}

		} // catch
	}// test
}