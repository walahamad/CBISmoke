package com.generic.tests.GR.login;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Registration;
import com.generic.page.Login;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.RandomUtilities;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class LoginBase extends SelTestCase {

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
		String CaseDescription = MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc.replace("\n", "<br>--"));
		initReportTime();

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

		if (!proprties.equals("Success login")) {
			Testlogs.get().debug("Login mail is: " + userMail);
			Testlogs.get().debug("Login password is: " + userPassword);
		}
		try {

			if ((proprties.equals("Success login") || proprties.equals("myAccountLink")) && email.equals("")) {
				Testlogs.get().debug("Run the registration test case before sign in.");
				// Prepare registration data.
				userMail = RandomUtilities.getRandomEmail();
				userPassword = "P@ssword11";
				Login.registerNewUser(userMail, userPassword, true);
			}

			if (proprties.equals("Success login")) {
				Testlogs.get().debug("Validate Success login");
				Testlogs.get().debug("Login mail is: " + userMail);
				Testlogs.get().debug("Login password is: " + userPassword);
				Login.fillLoginFormAndClickSubmit(userMail, (String) userPassword);
				sassert().assertTrue(Login.checkUserAccount(), LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);
			}

			if (proprties.equals("emptyData")) {
				Login.fillLoginFormAndClickSubmit("", "");
				String emailMessage = Login.getMailErrorMsg();
				String passwordMessage = Login.getPasswrdErrorMsg();

				String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						emailMessage + "<br>" + passwordMessage, fieldsValidation);

				sassert().assertTrue(fieldsValidation.contains(emailMessage),
						"Mail Validation error: " + failureMessage);
				sassert().assertTrue(fieldsValidation.contains(passwordMessage),
						"Password Validation error" + failureMessage);
				sassert().assertTrue(!Login.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("invalidUserEmail")) {
				Testlogs.get().debug("Validate invalid User Email login");
				Login.fillLoginFormAndClickSubmit(userMail.replace("@", ""), userPassword);
				String alertMessage = Login.getMailErrorMsg().toLowerCase();
				sassert().assertTrue(alertMessage.contains(fieldsValidation.toLowerCase()),
						"Error message is not as expected" + fieldsValidation + " : " + alertMessage);
				sassert().assertTrue(!Login.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("wrongUserPassword")) {

				Testlogs.get().debug("Validate wrong User Password Message");
				Login.fillLoginFormAndClickSubmit(userMail, userPassword + "123");
				String loginformMessage = Login.getErrologinMessage();
				String failureMessage = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, loginformMessage,
						fieldsValidation);
				sassert().assertTrue(loginformMessage.contains(fieldsValidation), failureMessage);
				sassert().assertTrue(!Login.checkUserAccount(), LoggingMsg.USER_IS_LOGGED_IN);
			}

			if (proprties.equals("myAccountLink")) {

				Testlogs.get().debug("Validate existence of my account link");
				Login.fillLoginFormAndClickSubmit(userMail, userPassword);
				sassert().assertTrue(Login.checkUserAccount(), LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);
				sassert().assertTrue(Login.checkExistenceOfAccountLink(), LoggingMsg.MY_ACCOUNT_LINK_NOT_EXIST);
				sassert().assertTrue(Login.checkMyAccountPage(), LoggingMsg.NOT_MY_ACCOUNT_PAGE);
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
