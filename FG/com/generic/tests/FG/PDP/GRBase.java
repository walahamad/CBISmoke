package com.generic.tests.FG.PDP;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.GiftRegistry;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.selector.GiftRegistrySelectors;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.tests.FG.login.LoginBase;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class GRBase extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.GRSheet;

	private static XmlTest testObject;
	LoginBase loginBase = new LoginBase();

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	GiftRegistry giftRegistry = new GiftRegistry();

	public String userMail;
	public String userPassword;
	public LinkedHashMap<String, String> addressDetails;
	public String firstName;
	public String lastName;
	public String companyName;
	public String addressLine1;
	public String phone;

	// Test cases.
	public static final String createRegistry = "create registry";
	public static final String manageRegistry = "manage registry";

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		LoginBase.initialSetUp(test);
	}

	@DataProvider(name = "GR_SC", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "GR_SC")
	public void GRTest(String caseId, String runTest, String desc, String proprties, String email, String registryType, String eventDateMonth, String eventDateDay, String eventDateYear, String emptyMessage)
			throws Exception {
		Testlogs.set(new SASLogger("GR_SC " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.GRCaseId);
		Testlogs.get().debug("Case Browser: " + testObject.getParameter("browserName"));
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		try {
			accessValidAccount(email, caseId, runTest, desc);
			GiftRegistry.setRegistryInformtion(registryType, eventDateMonth, eventDateDay, eventDateYear, emptyMessage);
			if (!email.equals("")) {
				LinkedHashMap<String, String> userdetails = (LinkedHashMap<String, String>) users.get(email);
				userMail = getSubMailAccount(userdetails.get(Registration.keys.email));
			}

			if (proprties.contains(createRegistry)) {
				giftRegistry.validate(userMail);
			} else {
				// Clear the initial value for gift registry name.
				GiftRegistry.setRegistryName("");
			}

			if (proprties.contains(manageRegistry)) {
				// Add item to GR then add the item to cart from GR.
				giftRegistry.manageRegistryValidate(userMail);
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

	public void accessValidAccount(String email,String caseId,String runTest,String desc) throws Exception {
		if (email.equals("")) {
			giftRegistry.accessValidAccount(email, caseId, runTest, desc);
		} else {
			loginBase.LoginRegressionTest(caseId, runTest, desc, "Success login", email, "");
		}
	}
}
