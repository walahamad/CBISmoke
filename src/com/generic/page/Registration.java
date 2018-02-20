package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.generic.selector.RegistrationSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

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

	//Done 
	public static void typeFirstName(String firstName) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
		subStrArr.add(RegistrationSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	//Done
	public static void typeLastName(String lastName) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
		subStrArr.add(RegistrationSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	//Done
	public static void typeEmailAddress(String address) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "emailAddress ", address));
		subStrArr.add(RegistrationSelectors.emailAddress);
		valuesArr.add(address);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	//done
	public static void typePassword(String password) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password ", password));
		subStrArr.add(RegistrationSelectors.password);
		valuesArr.add(password);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	//done
	public static void typeConfirmPassword(String confPassword) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "confirmPassword", confPassword));
		subStrArr.add(RegistrationSelectors.confirmPassword);
		valuesArr.add(confPassword);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	//done
	public static void clickRegisterButton() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Register btn"));
		subStrArr.add(RegistrationSelectors.registerBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	//done
	public static void fillAndClickRegister(String fName, String lName, String email, String pass, String confPass) throws Exception {
		getCurrentFunctionName(true);

		if (!"".equals(fName))
			typeFirstName(fName);

		if (!"".equals(lName))
			typeLastName(lName);

		if (!"".equals(email))
			typeEmailAddress(email);

		if (!"".equals(pass))
			typePassword(pass);

		if (!"".equals(confPass))
			typeConfirmPassword(confPass);

		clickRegisterButton();
		getCurrentFunctionName(false);

	}

	//done
	public static String getFirstNameError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "First Name Error"));
		subStrArr.add(RegistrationSelectors.firstNameError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}
	
	//done
	public static String getLastNameError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Last Name Error"));
		subStrArr.add(RegistrationSelectors.lastNameError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}

	//done
	public static String getEmailAddressError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Email Address Error"));
		subStrArr.add(RegistrationSelectors.emailAddressError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	//done
	public static String getPasswordError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Password Error"));
		subStrArr.add(RegistrationSelectors.passwordError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	//Done
	public static String getConfirmPasswordError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Confirm Password Error"));
		subStrArr.add(RegistrationSelectors.confirmPasswordError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static void verifyRegistrationFormErrors() throws Exception {
		getCurrentFunctionName(true);
		getFirstNameError();
		getLastNameError();
		getEmailAddressError();
		getPasswordError();
		getConfirmPasswordError();
		getCurrentFunctionName(false);
	}

	//Done
	public static String getRegistrationSuccessMessage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT, "Account created success message"));
		subStrArr.add(RegistrationSelectors.registrationSuccess);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();

	}
}
