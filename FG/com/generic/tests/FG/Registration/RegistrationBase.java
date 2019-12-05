package com.generic.tests.FG.Registration;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.runners.Parameterized.Parameters;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Registration;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class RegistrationBase extends SelTestCase {

	// possible scenarios
	public static final String freshUser = "fresh";
	public static final String existingUser = "existing";
	public static final String emptyData = "empty";
	public static final String invalidUserID = "UserID";
	public static final String passwordMismatch = "Mismatch";
	public static final String invalidPassword = "UserPassword";
	
	//messagesValidations
	public static final String successMessage = "success";
	public static final String invalidEmail = "invalidEmail";
	public static final String titleError = "titleError";
	public static final String firstNameError = "firstNameError";
	public static final String lastNameError = "lastNameError";
	public static final String passwordError = "passwordError";
	public static final String confPasswordError = "confirmPasswordError";
	public static final String passwordRulesError = "passwordRuleError";
	public static final String passwordMisatchError = "passwordMismatchError";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.registrationRegressionSheet;

	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ;
	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "Registration", parallel = true)
	//concurrency maintenance on sheet reading 
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "<br>--"));
		return data;
	}
	
	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Registration")
	public void registrationRegressionTest(String caseId, String runTest, String desc, String proprties,
			String password, String fieldsValidation) throws Exception {
		
		Testlogs.set(new SASLogger("registration "+getBrowserName()));
		//Important to add this for logging/reporting 
		setTestCaseReportName("Registration Case");
		//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
		logCaseDetailds(MessageFormat.format(LoggingMsg.REGISTRATIONDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		
		String thankUMsg = (fieldsValidation.split("ThankyouValidation:").length >2) ? fieldsValidation.split("ThankyouValidation:")[0].split("\n")[0]:"";
		String emailValidation = (fieldsValidation.split("EmailValidation:").length >2) ? fieldsValidation.split("EmailValidation:")[0].split("\n")[0]:"";
		String emailConfValidation = (fieldsValidation.split("EmailConfValidation:").length >2) ? fieldsValidation.split("EmailConfValidation:")[0].split("\n")[0]:"";
		String firstNameValidation =(fieldsValidation.split("firstNameValidation:").length >2) ?  fieldsValidation.split("firstNameValidation:")[0].split("\n")[0]:"";
		String lastNameValidation = (fieldsValidation.split("lastNameValidation:").length >2) ? fieldsValidation.split("lastNameValidation:")[0].split("\n")[0]:"";
		String passwordValidation =(fieldsValidation.split("PasswordValidation:").length >2) ?  fieldsValidation.split("PasswordValidation:")[0].split("\n")[0]:"";
		String passwordConfValidation = (fieldsValidation.split("PasswordConfValidation:").length >2) ? fieldsValidation.split("PasswordConfValidation:")[0].split("\n")[0]:"";
		String streetAddress1Validation = (fieldsValidation.split("StreetAddress1Validation:").length >2) ? fieldsValidation.split("StreetAddress1Validation:")[0].split("\n")[0]:"";
		String cityValidation = (fieldsValidation.split("CityValidation:").length >2) ? fieldsValidation.split("CityValidation:")[0].split("\n")[0]:"";
		String stateValidation = (fieldsValidation.split("StateValidation:").length >2) ? fieldsValidation.split("StateValidation:")[0].split("\n")[0]:"";
		String ZIPCodeValidation = (fieldsValidation.split("ZIPCodeValidation:").length >2) ? fieldsValidation.split("ZIPCodeValidation:")[0].split("\n")[0]:"";
		String PhoneValidation = (fieldsValidation.split("PhoneValidation:").length >2) ? fieldsValidation.split("PhoneValidation:")[0].split("\n")[0]:"";
		String schoolValidation = (fieldsValidation.split("schoolValidation:").length >2) ? fieldsValidation.split("schoolValidation:")[0].split("\n")[0]:"";
		String termsValidation = (fieldsValidation.split("AcceptTermsValidation:").length >2) ? fieldsValidation.split("AcceptTermsValidation:")[0].split("\n")[0]:"";
		
		
		
		
		//Prepare registration data 
		String email = RandomUtilities.getRandomEmail();

		try {
			// Positive registration case
			if (proprties.contains(freshUser)) {
				String registrationSuccessMsg = Registration.freshUserValidate(email, password);
				sassert().assertTrue(registrationSuccessMsg.toLowerCase().contains(thankUMsg), "Regestration Success, validation failed Expected to have in message: " + thankUMsg +" but Actual message is: " + registrationSuccessMsg);
			}
			
			//click on register new user button
			Registration.goToRegistrationForm();

			// Negative registration case
			if (proprties.contains(emptyData)) {
				Registration.clickRegisterButton();
				
				//>>>>>>>>>>>>>>>>Error messages to be updated in the excel sheet<<<<<<<<<<<<<<<<<<<<<<<<<<<
				/*
				 * firstNameValidation:Please enter First Name. 
				 * lastNameValidation:Please enter Last Name. 
				 * EmailValidation:Please enter Email Address.
				 * EmailConfValidation:Please Re-Enter Email Address. 
				 * PasswordValidation:Please Enter Password. PasswordConfValidation:Please Re-Enter Password.
				 * StreetAddress1Validation:Please enter Street Address 1. 
				 * CityValidation:Please enter City. 
				 * StateValidation:Please select a State/Province.
				 * ZIPCodeValidation:Please enter ZIP/Postal Code. 
				 * PhoneValidation:Please enter a Daytime phone number, including area code (US Only).
				 */
				
				String validationMsg = "";
				
				//Validating Errors of the first step								
				validationMsg = Registration.getEmailAddressErrorInvalid();
				sassert().assertTrue(validationMsg.contains(emailValidation),
						"Mail validation failed Expected: " + emailValidation + " Actual: " + validationMsg);
				
				validationMsg = Registration.getConfEmailAddressErrorInvalid();
				sassert().assertTrue(validationMsg.contains(emailConfValidation),
						"Mail confirmation validation failed Expected: " + emailConfValidation + " Actual: " + validationMsg);
				
				
				validationMsg = Registration.getPasswordError();
				sassert().assertTrue(validationMsg.contains(passwordValidation),
						"Password validation failed Expected: " + passwordValidation + " Actual: " + validationMsg);
				
				validationMsg = Registration.getConfirmPasswordError();
				sassert().assertTrue(validationMsg.contains(passwordConfValidation),
						"Password confirmation validation failed Expected: " + passwordConfValidation + " Actual: "
								+ validationMsg);
				
				//Sleeping for 1 Second
				Thread.sleep(1000);
				
				//>>>>>>>>>>>>>>Password field needs to be filled in the excel sheet with P@ssword1<<<<<<<<<<<<<<<<<
				
				//Filling 1st step with valid Data to check 2nd step
				Registration.fillRegistrationFirstStep(email,email,password,password);
				
				//Sleeping for 1 Second
				Thread.sleep(1000);
				
				//Click save button with empty fields to trigger error messages
				Registration.clickSaveButton();
				
				//Validating Errors of the second step
				validationMsg = Registration.getFirstNameErrorInvalid();
				sassert().assertTrue(validationMsg.contains(firstNameValidation),
						"First name validation failed Expected: " + firstNameValidation + " Actual: " + firstNameValidation);
				
				validationMsg = Registration.getLastNameErrorInvalid();
				sassert().assertTrue(validationMsg.contains(lastNameValidation),
						"Last name validation failed Expected: " + lastNameValidation + " Actual: " + lastNameValidation);
				

				validationMsg = Registration.getStreerAddressErrorInvalid();
				sassert().assertTrue(validationMsg.contains(streetAddress1Validation),
						"Street address 1 validation failed Expected: " + streetAddress1Validation + " Actual: " + streetAddress1Validation);
				
				validationMsg = Registration.getCityErrorInvalid();
				sassert().assertTrue(validationMsg.contains(cityValidation),
						"City validation failed Expected: " + cityValidation + " Actual: " + cityValidation);
				
				validationMsg = Registration.getStateErrorInvalid();
				sassert().assertTrue(validationMsg.contains(stateValidation),
						"State validation failed Expected: " + stateValidation + " Actual: " + stateValidation);
				
				validationMsg = Registration.getZIPCodeErrorInvalid();
				sassert().assertTrue(validationMsg.contains(ZIPCodeValidation),
						"ZIP Code validation failed Expected: " + ZIPCodeValidation + " Actual: " + ZIPCodeValidation);
				
				validationMsg = Registration.getPhoneErrorInvalid();
				sassert().assertTrue(validationMsg.contains(PhoneValidation),
						"Phone validation failed Expected: " + PhoneValidation + " Actual: " + PhoneValidation);		
					
			}
						
			Thread.sleep(2000);						
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
