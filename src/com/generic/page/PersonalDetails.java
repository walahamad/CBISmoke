package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PersonalDetailsSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PersonalDetails extends SelTestCase {
	private static List<String> subStrArr = new ArrayList<String>();
    private static List<String> valuesArr = new ArrayList<String>();
    
    public static class keys {
		public static final String caseId = "caseId";
	}
    
    public static String getTitleValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.title));
		subStrArr.add(PersonalDetailsSelectors.title);
		valuesArr.add("");
		String titleVal = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		titleVal = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return titleVal;
    }
    
    public static String getFirstNameValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.firstName));
		subStrArr.add(PersonalDetailsSelectors.firstName);
		valuesArr.add("getCurrentValue");
		String firstName = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		firstName = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return firstName;
    }
    
    public static String getLastNameValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.lastName));
		subStrArr.add(PersonalDetailsSelectors.lastName);
		valuesArr.add("getCurrentValue");
		String lastName = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		lastName = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return lastName;
    }
    
    public static void clickCancelBtn() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PersonalDetailsSelectors.cancelBtn));
		subStrArr.add(PersonalDetailsSelectors.cancelBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickUpdateBtn() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PersonalDetailsSelectors.updateBtn));
		subStrArr.add(PersonalDetailsSelectors.updateBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void selectTitle(String title) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,PersonalDetailsSelectors.title, title));
		subStrArr.add(PersonalDetailsSelectors.title);
		valuesArr.add(title);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void typeFirstName(String firstName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, PersonalDetailsSelectors.firstName, firstName));
		subStrArr.add(PersonalDetailsSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void typeLastName(String lastName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, PersonalDetailsSelectors.lastName, lastName));
		subStrArr.add(PersonalDetailsSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void fillInNewValuesAndClickUpdateOrCancel(String title, String firstName, String lastName, boolean doUpdate, boolean doCancel) throws Exception {
    	getCurrentFunctionName(true);
    	PersonalDetails.selectTitle(title);
    	PersonalDetails.typeFirstName(firstName);
    	PersonalDetails.typeLastName(lastName);
    	if (doUpdate) {
    		PersonalDetails.clickUpdateBtn();
    	}
    	if (doCancel) {
    		PersonalDetails.clickCancelBtn();
    	}
    	getCurrentFunctionName(false);
    }  
    
    public static String getGlobalAlertsMsg() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.globalAlerts));
		subStrArr.add(PersonalDetailsSelectors.globalAlerts);
		valuesArr.add("");
		String globalAlertMsg = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		globalAlertMsg = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return globalAlertMsg;
    }
    
    public static String getFirstNameErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.firstNameErrors));
		subStrArr.add(PersonalDetailsSelectors.firstNameErrors);
		valuesArr.add("noClick");
		String firstNameErrorMsg = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		firstNameErrorMsg = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return firstNameErrorMsg;
    }
    
    public static String getLastNameErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PersonalDetailsSelectors.lastNameErrors));
		subStrArr.add(PersonalDetailsSelectors.lastNameErrors);
		valuesArr.add("noClick");
		String lastNameErrorMsg = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		lastNameErrorMsg = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return lastNameErrorMsg;
    }
    
    
}
