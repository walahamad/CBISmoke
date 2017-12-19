package com.generic.tests.personalDetails;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.generic.page.PersonalDetails;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;

@RunWith(Parameterized.class)
public class PersonalDertailsValidation extends SelTestCase {

	// used sheet in test
		public static final String testDataSheet = SheetVariables.personalDetailsSheet;

		private String caseId;
		private String runTest;
		private String desc;
		private String userName;
		private String password;
		private String url;
		private String title;
		private String firstName;
		private String lastName;
		private String globalAlerts;
		private String firstNameErrors;
		private String lastNameErrors;
		private boolean doVerifyCurrent;
		private boolean doClickCancelBtn;
		private boolean doClickUpdateBtn;
		
		
		@BeforeClass
		public static void initialSetUp() throws Exception {
			testCaseRepotId = SheetVariables.personalDetailsTestCaseId;
		}
		
		public PersonalDertailsValidation(String caseId, String runTest, String desc, String userName, String password,String url, String title,
				String firstName, String lastName, String doVerifyCurrent, String doClickCancelBtn, String doClickUpdateBtn, String globalAlerts, String firstNameErrors, String lastNameErrors) {
			this.caseId = caseId;
			this.runTest = runTest;
			this.desc = desc;
			this.userName = userName;
			this.password = password;
			this.url = url;
			this.title = title;
			this.firstName = firstName;
			this.lastName = lastName;
			this.globalAlerts = globalAlerts;
			this.firstNameErrors = firstNameErrors;
			this.lastNameErrors = lastNameErrors;
			this.doVerifyCurrent = Boolean.valueOf(doVerifyCurrent);
			this.doClickCancelBtn = Boolean.valueOf(doClickCancelBtn);
			this.doClickUpdateBtn = Boolean.valueOf(doClickUpdateBtn);
			
		}
		
		@Parameters(name = "{index}_:{2}")
		public static Collection<Object[]> loadTestData() throws Exception {
			Object[][] data = TestUtilities.getData(testDataSheet);
			return Arrays.asList(data);
		}

		@Test
		public void verifyPersonalDetails() throws Exception {
			setTestCaseDescription(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
					this.getClass().getCanonicalName(), desc));
			
			try {
				
				SignIn.logIn(userName, password);
				Thread.sleep(2000);
				getDriver().get(url);
				Thread.sleep(3000);
				
				if(doVerifyCurrent) {
					String incorrectTitleErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getTitleValue(), title);
					String incorrectFirstNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getFirstNameValue(), firstName);
					String incorrectlastNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getLastNameValue(), lastName);
					Assert.assertEquals(incorrectTitleErrorMsg, title, PersonalDetails.getTitleValue());
					Assert.assertEquals(incorrectFirstNameErrorMsg, firstName, PersonalDetails.getFirstNameValue());
					Assert.assertEquals(incorrectlastNameErrorMsg, lastName, PersonalDetails.getLastNameValue());
				} else {
					PersonalDetails.fillInNewValuesAndClickUpdateOrCancel(title, firstName, lastName, doClickUpdateBtn, doClickCancelBtn);
					Thread.sleep(2000);
					if (doClickUpdateBtn) {
						String globalAlertMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getGlobalAlertsMsg(), globalAlerts);
						Assert.assertEquals(globalAlertMsg, globalAlerts, PersonalDetails.getGlobalAlertsMsg());
						if (!firstNameErrors.isEmpty()) {
							String firstNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getFirstNameErrorMsg(), firstNameErrors);
							Assert.assertEquals(firstNameErrorMsg, firstNameErrors, PersonalDetails.getFirstNameErrorMsg());
						}
						if (!lastNameErrors.isEmpty()) {
							String lastNameErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, PersonalDetails.getLastNameErrorMsg(), lastNameErrors);
							Assert.assertEquals(lastNameErrorMsg, lastNameErrors, PersonalDetails.getLastNameErrorMsg());
						}
					}
				}
				
				Common.testPass();
			} catch (Throwable t) {
				setTestCaseDescription(getTestCaseDescription());
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
				t.printStackTrace();
				String temp = getTestCaseReportName();
				Common.testFail(t, temp);
				Common.takeScreenShot();
				Assert.assertTrue(t.getMessage(), false);
			}

		}

	}

