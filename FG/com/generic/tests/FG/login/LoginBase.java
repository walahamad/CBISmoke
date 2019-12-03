package com.generic.tests.FG.login;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.selector.SignInSelectors;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class LoginBase extends SelTestCase {

	private static int testCaseID;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.loginSheet;
	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "Login", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Login")
	public void LoginRegressionTest(String caseId, String runTest, String desc, String proprties, String email,
			String fieldsValidation) {

		Testlogs.set(new SASLogger("Login " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("Login Case");
		Testlogs.get().debug("Case Browser: " + testObject.getParameter("browserName"));
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));

		String userMail = "";
		String userPassword = "";
		LinkedHashMap<String, String> userdetails = null;
		// Get the user details (User email and password).
		if (!email.equals("")) {
			userdetails = (LinkedHashMap<String, String>) users.get(email);
			userMail = getSubMailAccount(userdetails.get(Registration.keys.email));
			Testlogs.get().debug("Mail will be used is: " + userMail);
			userPassword = userdetails.get(Registration.keys.password);
		}

		Testlogs.get().debug("Login mail is: " + userMail);
		Testlogs.get().debug("Login password is: " + userPassword);

		try {
			
			if ((proprties.equals("Success login") || proprties.equals("myAccountLink")) && email.equals("")) {
				Testlogs.get().debug("Run the registration test case before sign in.");
				//Prepare registration data.
				userMail = RandomUtilities.getRandomEmail();
				userPassword = "P@ssword11";
				SignIn.registerNewUser(userMail, userPassword);
			}

			if (proprties.equals("Success login")) {
				Testlogs.get().debug("Validate Success login");
				SignIn.fillLoginFormAndClickSubmit(userMail, (String) userPassword);
				sassert().assertTrue(SignIn.checkUserAccount(), LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);
			}
			if (proprties.equals("emptyData")) {
				SignIn.fillLoginFormAndClickSubmit("", "");
				String emailMessage = SignIn.getMailErrorMsg();
				String passwordMessage = SignIn.getPasswrdErrorMsg();

				String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						emailMessage + "<br>" + passwordMessage, fieldsValidation);

				sassert().assertTrue(fieldsValidation.contains(emailMessage),"Mail Validation error: "+failureMessage);
				sassert().assertTrue(fieldsValidation.contains(passwordMessage),"Password Validation error"+ failureMessage);
				sassert().assertTrue(!SignIn.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("invalidUserEmail")) {
				Testlogs.get().debug("Validate invalid User Email login");
				SignIn.fillLoginFormAndClickSubmit(userMail.replace("@", ""), userPassword);
				String alertMessage = SignIn.getMailErrorMsg().toLowerCase();
				sassert().assertTrue(alertMessage.contains(fieldsValidation.toLowerCase()),
						"Error message is not as expected" + fieldsValidation + " : " + alertMessage);
				sassert().assertTrue(!SignIn.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("wrongUserPassword")) {

				Testlogs.get().debug("Validate wrong User Password Message");
				SignIn.fillLoginFormAndClickSubmit(userMail, userPassword + "123");
				String loginformMessage = SignIn.getErrologinMessage();
				String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, loginformMessage,
						fieldsValidation);
				sassert().assertTrue(loginformMessage.contains(fieldsValidation), failureMessage);
				sassert().assertTrue(!SignIn.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("myAccountLink")) {

				Testlogs.get().debug("Validate existence of my account link");
				SignIn.fillLoginFormAndClickSubmit(userMail, userPassword);
				sassert().assertTrue(SignIn.checkUserAccount(), LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);
				sassert().assertTrue(SignIn.checkExistenceOfAccountLink(), LoggingMsg.MY_ACCOUNT_LINK_NOT_EXIST);
				sassert().assertTrue(SignIn.checkMyAccountPage(), LoggingMsg.NOT_MY_ACCOUNT_PAGE);
			}

			if (proprties.equals("Forgot password -Valid Email")) {
				SignIn.clickForgotPasswordBtn();
				SignIn.typeForgottenPwdEmail(userMail);
				SignIn.clickForgotPasswordSubmitBtn();
				Thread.sleep(1500);
				String alertMessage = SignIn.getAlertPositiveForgottenPasswordd();
				String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, alertMessage,
						fieldsValidation);
				sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
			}
			if (proprties.equals("Forgot password -Invalid Email")) {
				if(!getBrowserName().contains("mobile"))
				{
					SignIn.clickForgotPasswordBtn();
					SignIn.typeForgottenPwdEmail(userMail.replace("@", ""));
					SignIn.clickForgotPasswordSubmitBtn();
					Thread.sleep(1500);
					String alertMessage = SignIn.getForgottenPwdEmailError();
					String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, alertMessage,
							fieldsValidation);
					sassert().assertTrue(alertMessage.contains(fieldsValidation), failureMessage);
				}
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
