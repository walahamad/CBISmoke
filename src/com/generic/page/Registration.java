package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import com.generic.selector.RegistrationSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;
import java.net.URI;

public class Registration extends SelTestCase {
	public static class keys {
		public static final String caseId = "caseId";
		public static final String title = "title";
		public static final String name = "name";
		public static final String userName = "userName";
		public static final String firstName = "firstName";
		public static final String lastName = "lastName";
		public static final String password = "password";
		public static final String email = "mail";

	}

	// Done CBI Smoke
	public static void typeFirstName(String firstName) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstName.get(), firstName);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void typeLastName(String lastName) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastName.get(), lastName);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void typeEmailAddress(String address) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "emailAddress ", address));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.emailAddress.get(), address);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void typePassword(String password) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password ", password));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.password.get(), password);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI Smoke
	public static void typeConfirmPassword(String confPassword) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "confirmPassword", confPassword));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmPassword.get(), confPassword);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI Smoke
	public static void typeCompany(String comapnyName) throws Exception {
		try {
			getCurrentFunctionName(true);
			if (!isRY()) {
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "companyName", comapnyName));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.companyName.get(), comapnyName);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI Smoke
	public static void clickRegisterButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Register btn"));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registerBtn, "");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void fillRegistrationFirstStep(String email, String confEmail, String confPassword, String password)
			throws Exception {
		try {
			getCurrentFunctionName(true);
			if (!"".equals(email))
				typeEmailAddress(email);

			if (!"".equals(confEmail))
				typeconfEmailAddress(confEmail);
			
			if (!"".equals(password))
				typePassword(password);

			if (!"".equals(confPassword))
				typeConfirmPassword(confPassword);

			Thread.sleep(1000);

			clickRegisterButton();

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void fillRegistrationSecondStep(String fName, String lName, String companyName,
			LinkedHashMap<String, String> addressDetails) throws Exception {
		try {
			getCurrentFunctionName(true);

			if (!"".equals(fName))
				typeFirstName(fName);

			if (!"".equals(lName))
				typeLastName(lName);

			if (!"".equals(companyName))
				typeCompany(companyName);

			typeAddressLine1(RandomUtilities.getRandomName());

			if (!"".equals(addressDetails.get(AddressBook.shippingAddress.keys.city)))
				typeCity(addressDetails.get(AddressBook.shippingAddress.keys.city));

			if (!"".equals(addressDetails.get(AddressBook.shippingAddress.keys.city)))
				typeState(addressDetails.get(AddressBook.shippingAddress.keys.city));

			if (!"".equals(addressDetails.get(AddressBook.shippingAddress.keys.zipcode)))
				typeZipcode(addressDetails.get(AddressBook.shippingAddress.keys.zipcode));

			typePhone(RandomUtilities.getRandomPhone());

			clickSaveButton();

			getCurrentFunctionName(false);
		}

		catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI
	public static void clickSaveButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Register btn"));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.saveButton.get(), "");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}


	// Done CBI Smoke
	private static void typePhone(String phone) throws Exception {
		try {
			getCurrentFunctionName(true);
			if (isGH() || isRY()) {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phoneGH.get(), phone);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phone.get(), phone);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	private static void typeconfEmailAddress(String email) throws Exception {
		try {
			getCurrentFunctionName(true);
			if (isGH()) {
				if (!isMobile())
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmEmailAddress.get(),
							email);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmEmailAddress.get(), email);

			}

			logs.debug("Data is" + email);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	private static void typeAddressLine1(String address) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.AddressLine1.get(), address);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	private static void typeCity(String city) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.city.get(), city);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	private static void typeState(String state) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.state.get(), state);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	private static void typeZipcode(String zipcode) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.Zipcode.get(), zipcode);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI
	public static String getFirstNameError() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First Name Error"));
			subStrArr.add(RegistrationSelectors.firstNameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getLastNameError() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last Name Error"));
			subStrArr.add(RegistrationSelectors.lastNameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI
	public static String getEmailAddressErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.emailAddressErrorMobile, "index,0");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.emailAddressError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getConfEmailAddressErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confEmailAddressErrorMobile,
						"index,1");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confEmailAddressError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getPasswordError() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.passwordRulesErrorMobile, "index,2");

			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.passwordRulesError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getConfirmPasswordError() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmPasswordErrorMobile,
						"index,3");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmPasswordError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getFirstNameErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstNameErrorMobile, "index,0");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstNameError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getLastNameErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastNameErrorMobile, "index,1");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastNameError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getStreerAddressErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Street address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.streetAddreesErrorMobile, "index,2");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Street address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.streetAddreesError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getCityErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "City Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.cityErrorMobile, "index,3");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "City Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.cityError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getStateErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.stateErrorMobile, "");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.stateError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getZIPCodeErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.ZIPCodeErrorMobile, "index,4");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.ZIPCodeError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done CBI
	public static String getPhoneErrorInvalid() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phoneErrorMobile, "index,5");
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phoneError, "");
			}

			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI Smoke
	public static String getRegistrationSuccessMessage() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "welcome Message check"));
			
			if (isGH()) {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.welcomeMessageGH.get(), "");
			}
			
			else if (isRY()) {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.welcomeMessageRY.get(), "");
			} 
			
			else {
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.welcomeMessage.get(), "");
			}
			
			getCurrentFunctionName(false);

			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI Smoke
	public static void goToRegistrationForm() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,
					"Navigating to registration page..." + getCONFIG().getProperty("RegistrationPage")));
			getDriver().get(new URI(getDriver().getCurrentUrl()).resolve(getCONFIG().getProperty("RegistrationPage"))
					.toString());

			//GH 
			if (isGH()) {

				if (!getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for desktop..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButton);
				
				} else {
					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Tab for mobile... GH"));
					getDriver().findElements(By.cssSelector(RegistrationSelectors.mobileRegistrationTabGH)).get(1).click();

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,"Clicking Register Button for mobile...  GH"));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationButtonGH);
				}

			//RY	
			} else if (isRY()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button RY..."));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButtonRY.get());			
				
			}
			
			//FG, GR
			else {
				if (!getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
					logs.debug(
							MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for desktop..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButton);
				} else {

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Tab for mobile..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationTab);

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for mobile..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationButton);
				}
			}

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	public static String freshUserValidate(String email, String password) throws Exception {

		//click on register new user button
		Registration.goToRegistrationForm();

		//prepare random address details
		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get("A3");

		//Prepare registration data
		String firstName = RandomUtilities.getRandomName();
		String lastName = RandomUtilities.getRandomName();
		String companyName = RandomUtilities.getRandomName();

		//register new user and validate the results
		Registration.fillRegistrationFirstStep(email,email,password,password);

		Thread.sleep(1500);
		Registration.fillRegistrationSecondStep(firstName,lastName,companyName,addressDetails);

		//Success message needs to be updated on excel to (Welcome to your account at )
		String registrationSuccessMsg = Registration.getRegistrationSuccessMessage();
		return registrationSuccessMsg;
	}
}
