package com.generic.page;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import com.generic.selector.RegistrationSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;
import com.generic.util.SelectorUtil.commands.actions;

import java.net.URI;

public class Registration extends SelTestCase {
	public static class keys {
		public static final String password = "password";
		public static final String email = "mail";
	}

	public static class shippingAddress {

		public static class keys {

			public static final String isSavedShipping = "saved-shipping";

			public static final String countery = "countery";
			public static final String title = "title";
			public static final String lastName = "lastName";
			public static final String firstName = "firstName";
			public static final String adddressLine = "adddressLine";
			public static final String city = "city";
			public static final String zipcode = "postal";
			public static final String phone = "phone";
		}
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
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registerBtn.get());
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

			if (!"".equals(addressDetails.get(shippingAddress.keys.city)))
				typeCity(addressDetails.get(shippingAddress.keys.city));

			if (!"".equals(addressDetails.get(shippingAddress.keys.city)))
				typeState(addressDetails.get(shippingAddress.keys.city));

			if (!"".equals(addressDetails.get(shippingAddress.keys.zipcode)))
				typeZipcode(addressDetails.get(shippingAddress.keys.zipcode));

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
			} else if (isGR() || isFG()) {
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
			} else if (isGR() || isFG() || isRY()) {
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
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First Name Error"));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstNameError.get());
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
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last Name Error"));
			SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastNameError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.emailAddressErrorMobile.get(),
						MessageFormat.format(actions.index, "0"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.emailAddressError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confEmailAddressErrorMobile.get(),
						MessageFormat.format(actions.index, "1"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confEmailAddressError.get(), "");
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.passwordRulesErrorMobile.get(),
						MessageFormat.format(actions.index, "2"));

			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.passwordRulesError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmPasswordErrorMobile.get(),
						MessageFormat.format(actions.index, "3"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.confirmPasswordError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstNameErrorMobile.get(),
						MessageFormat.format(actions.index, "0"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.firstNameError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastNameErrorMobile.get(),
						MessageFormat.format(actions.index, "1"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last name Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.lastNameError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Street address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.streetAddreesErrorMobile.get(),
						MessageFormat.format(actions.index, "2"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Street address Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.streetAddreesError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "City Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.cityErrorMobile.get(),
						MessageFormat.format(actions.index, "3"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "City Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.cityError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.stateErrorMobile.get());
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.stateError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.ZIPCodeErrorMobile.get(),
						MessageFormat.format(actions.index, "4"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.ZIPCodeError.get());
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

			if (isMobile()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phoneErrorMobile.get(),
						MessageFormat.format(actions.index, "5"));
			} else {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "State Error"));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.phoneError.get());
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

			else if (isGR() || isFG()) {
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

			// GH
			if (isGH()) {

				if (!isMobile()) {
					logs.debug(
							MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for desktop..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButton.get());

				} else {
					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Tab for mobile... GH"));
					SelectorUtil.getAllElements(RegistrationSelectors.mobileRegistrationTabGH.get()).get(1).click();

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,
							"Clicking Register Button for mobile...  GH"));
					SelectorUtil
							.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationButtonGH.get());
				}

				// RY
			} else if (isRY()) {
				logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button RY..."));
				SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButtonRY.get());

			}

			// FG, GR
			else if (isGR() || isFG()) {
				if (!isMobile()) {
					logs.debug(
							MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for desktop..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.registrationButton.get());
				} else {

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Tab for mobile..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationTab.get());

					logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Clicking Register Button for mobile..."));
					SelectorUtil.initializeSelectorsAndDoActions(RegistrationSelectors.mobileRegistrationButton.get());
				}
			}

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done - CBI
	@SuppressWarnings("unchecked")
	public static String registerFreshUser(String email, String password) throws Exception {

		// click on register new user button
		goToRegistrationForm();

		// prepare random address details
		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get("A3");

		// Prepare registration data
		String firstName = RandomUtilities.getRandomName();
		String lastName = RandomUtilities.getRandomName();
		String companyName = RandomUtilities.getRandomName();

		// register new user and validate the results
		fillRegistrationFirstStep(email, email, password, password);

		Thread.sleep(1500);
		fillRegistrationSecondStep(firstName, lastName, companyName, addressDetails);

		// Success message needs to be updated on excel to (Welcome to your account at )
		String registrationSuccessMsg = getRegistrationSuccessMessage();
		return registrationSuccessMsg;
	}

	// Done - CBI
	@SuppressWarnings("unchecked")
	public static String registerFreshUser(String email, String password, String fname, String lname) throws Exception {

		// click on register new user button
		Registration.goToRegistrationForm();

		// prepare random address details
		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get("A3");

		// Prepare registration data
		String firstName = fname;
		String lastName = lname;
		String companyName = RandomUtilities.getRandomName();

		// register new user and validate the results
		Registration.fillRegistrationFirstStep(email, email, password, password);

		Thread.sleep(1500);
		Registration.fillRegistrationSecondStep(firstName, lastName, companyName, addressDetails);

		// Success message needs to be updated on excel to (Welcome to your account at )
		String registrationSuccessMsg = Registration.getRegistrationSuccessMessage();
		return registrationSuccessMsg;
	}

}
