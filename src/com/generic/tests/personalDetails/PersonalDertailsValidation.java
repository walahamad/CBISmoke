package com.generic.tests.personalDetails;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.page.PersonalDetails;

public class PersonalDertailsValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.personalDetailsSheet;
	private boolean doVerifyCurrent;
	private boolean doClickCancelBtn;
	private boolean doClickUpdateBtn;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}

	@DataProvider(name = "personalDetails", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		if (testObject.getParameter("browserName").equals("firefox"))
			Thread.sleep(500);
		if (testObject.getParameter("browserName").equals("chrome"))
			Thread.sleep(700);

		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "personalDetails")
	public void verifyPersonalDetails(String caseId, String runTest, String desc, String userName, String password,
			String url, String title, String firstName, String lastName, String doVerifyCurrent1,
			String doClickCancelBtn1, String doClickUpdateBtn1, String globalAlerts, String firstNameErrors,
			String lastNameErrors) throws Exception {

		doVerifyCurrent = Boolean.valueOf(doVerifyCurrent1);
		doClickCancelBtn = Boolean.valueOf(doClickCancelBtn1);
		doClickUpdateBtn = Boolean.valueOf(doClickUpdateBtn1);

		Testlogs.set(new SASLogger("personalDetails_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("personalDetails Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		// this.email = email.replace("tester",
		// "tester_"+getBrowserName().replace(" ", "_"));

		try {

			SignIn.logIn(userName, password);
			Thread.sleep(2000);
			getDriver().get(url);
			Thread.sleep(3000);

			if (doVerifyCurrent) {
				String incorrectTitleErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						PersonalDetails.getTitleValue(), title);
				String incorrectFirstNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						PersonalDetails.getFirstNameValue(), firstName);
				String incorrectlastNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						PersonalDetails.getLastNameValue(), lastName);
				Assert.assertEquals(incorrectTitleErrorMsg, title, PersonalDetails.getTitleValue());
				Assert.assertEquals(incorrectFirstNameErrorMsg, firstName, PersonalDetails.getFirstNameValue());
				Assert.assertEquals(incorrectlastNameErrorMsg, lastName, PersonalDetails.getLastNameValue());
			} else {
				PersonalDetails.fillInNewValuesAndClickUpdateOrCancel(title, firstName, lastName, doClickUpdateBtn,
						doClickCancelBtn);
				Thread.sleep(2000);
				if (doClickUpdateBtn) {
					String globalAlertMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							PersonalDetails.getGlobalAlertsMsg(), globalAlerts);
					Assert.assertEquals(globalAlertMsg, globalAlerts, PersonalDetails.getGlobalAlertsMsg());
					if (!firstNameErrors.isEmpty()) {
						String firstNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								PersonalDetails.getFirstNameErrorMsg(), firstNameErrors);
						Assert.assertEquals(firstNameErrorMsg, firstNameErrors, PersonalDetails.getFirstNameErrorMsg());
					}
					if (!lastNameErrors.isEmpty()) {
						String lastNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								PersonalDetails.getLastNameErrorMsg(), lastNameErrors);
						Assert.assertEquals(lastNameErrorMsg, lastNameErrors, PersonalDetails.getLastNameErrorMsg());
					}
				}
			}

			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		}

	}

}
