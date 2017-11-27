package com.generic.page;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.RegistrationSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class Registration extends SelTestCase {
    private static List<String> subStrArr = new ArrayList<String>();
    private static List<String> valuesArr = new ArrayList<String>();

    
    public static class keys{
    	public static final String title = "title";
    	public static final String name = "name";
    	public static final String userName = "userName";
    	public static final String firstName = "firstName";
    	public static final String lastName = "lastName";
    	public static final String password = "password";
    	public static final String email = "email";
    	
    }
    
    public static void selectTitle(String title) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,"title ", title));
		subStrArr.add(RegistrationSelectors.title);
		valuesArr.add(title);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void typeFirstName(String firstName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,"firstname ", firstName));
		subStrArr.add(RegistrationSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void typeLastName(String lastName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,"lastname ", lastName));
		subStrArr.add(RegistrationSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void typeEmailAddress(String address) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,"emailAddress ", address));
		subStrArr.add(RegistrationSelectors.emailAddress);
		valuesArr.add(address);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void typePassword(String password) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,"password ", password));
		subStrArr.add(RegistrationSelectors.password);
		valuesArr.add(password);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	public static void typeConfirmPassword(String confPassword) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,"confirmPassword", confPassword));
		subStrArr.add(RegistrationSelectors.confirmPassword);
		valuesArr.add(confPassword);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void checkConsentGiven1(boolean isConsentGiven1Checked) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL,"isConsentGiven1Checked", isConsentGiven1Checked));
		subStrArr.add(RegistrationSelectors.consentGiven);
		valuesArr.add(String.valueOf(isConsentGiven1Checked));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void clickRegistration() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL,"Register btn"));
		subStrArr.add(RegistrationSelectors.register);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		
	}
    
	public static void fillAndClickRegister(String title, String fName, String lName,String email, String pass, String confPass, boolean isConsentGiven1Checked) throws Exception {
		getCurrentFunctionName(true);
		if (!"".equals(title))
			selectTitle(title);

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

		checkConsentGiven1(isConsentGiven1Checked);

		clickRegistration();
		getCurrentFunctionName(false);
		
	}
}
