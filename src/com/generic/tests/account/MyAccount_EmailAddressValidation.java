package com.generic.tests.account;

import static org.testng.Assert.assertNotEquals;

import java.text.MessageFormat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.generic.page.Registration;
import com.generic.page.CheckOut;
import com.generic.page.MyAccount_EmailAddress;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class MyAccount_EmailAddressValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.EmailAddressRegression;
	private boolean doVerifyCurrent;
	private boolean doClickCancelBtn;
	private boolean doClickUpdateBtn;
	private String email;
	private String currentEmail;
	private String confirmNewEmail;
	private int caseIndexInDatasheet;
	private boolean revertChanges;


	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private static LinkedHashMap<String, Object> users;

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
		users = Common.readUsers();
	}

	@DataProvider(name = "EmailAddress", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "EmailAddress")
	public void verifyEmailAddress(String caseId, String runTest, String desc, String proprties, String email,
			String url, String currentEmail, String confirmNewEmail,String password, String globalAlerts, String emailErrors,
			String confirmEmailEerrors,String passwordEerrors) throws Exception {

		doVerifyCurrent =  proprties.contains("Verify current user");
		doClickCancelBtn = proprties.contains("click cancel");
		doClickUpdateBtn = proprties.contains("click update");
		revertChanges = proprties.contains("revert changes");
		
		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(email);

		Testlogs.set(new SASLogger("EmailAddress" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("EmailAddress Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		this.email = getSubMailAccount(email);
		this.currentEmail = getSubMailAccount(currentEmail);
		this.confirmNewEmail = getSubMailAccount(confirmNewEmail);
		caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, MyAccount_EmailAddress.keys.caseId, caseId);

		try {

			SignIn.logIn(this.email, (String)userDetails.get(Registration.keys.password));
			getDriver().get(url);
//			if(password.equals("1234567")){
//				password=(String)userDetails.get(Registration.keys.password);
//			}
			if (doVerifyCurrent) {
				String incorrectEmailErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						MyAccount_EmailAddress.getEmailValue(), this.email);
				
				sassert().assertEquals(this.email,MyAccount_EmailAddress.getEmailValue(), incorrectEmailErrorMsg);
			} else {
				MyAccount_EmailAddress.fillInNewValuesAndClickUpdateOrCancel(this.currentEmail
						, this.confirmNewEmail, password, doClickUpdateBtn,
						doClickCancelBtn);
				Thread.sleep(3000);
				if (doClickUpdateBtn) {
					String globalAlertMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
							MyAccount_EmailAddress.getGlobalAlertsMsg(), globalAlerts);
					Assert.assertTrue(globalAlertMsg.contains(globalAlerts), globalAlertMsg);
					if (!emailErrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getEmailErrorMsg();
						String currentEmailErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,currentMsg, emailErrors);
						sassert().assertTrue(currentMsg.contains(emailErrors), currentEmailErrorMsg );
					}
					if (!confirmEmailEerrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getConfirmEmailErrorMsg();
						logs.debug(currentMsg);
						String confirmEmailEerrorsMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								currentMsg, confirmEmailEerrors);
						sassert().assertTrue(currentMsg.contains(confirmEmailEerrors) , confirmEmailEerrorsMsg);
					}
					if (!passwordEerrors.equals("")) {
						String currentMsg = MyAccount_EmailAddress.getPasswordErrorMsg();
						logs.debug(currentMsg);
						String passwordEerrorrMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
								currentMsg, passwordEerrors);
						sassert().assertTrue(currentMsg.contains(passwordEerrors) , passwordEerrorrMsg);
					}
				}
			}
			
			if(revertChanges)
			{
				getDriver().get(url);
				MyAccount_EmailAddress.fillInNewValuesAndClickUpdateOrCancel(this.email,this.email,(String)userDetails.get(Registration.keys.password), doClickUpdateBtn, doClickCancelBtn);
				Thread.sleep(2000);
			}
			
			sassert().assertAll();
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
}// class
