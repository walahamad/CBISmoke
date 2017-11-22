package com.generic.page;


import java.util.ArrayList;
import java.util.List;

import com.generic.selector.signInSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class signIn extends SelTestCase {
    private static final String DOC_URL = getCONFIG().getProperty("testSiteName");
    private static List<String> subStrArr = new ArrayList<String>();
    private static List<String> valuesArr = new ArrayList<String>();


public static void logIn(String userName, String Password) throws Exception {
	getCurrentFunctionName(true);
	typeUsername(userName);
	typePassword(Password);
	clickLogin();
	getCurrentFunctionName(false);
}


private static void clickLogin() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(signInSelectors.loginBtn);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
	getCurrentFunctionName(false);
	
}


private static void typePassword(String password) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(signInSelectors.password);
	valuesArr.add(password);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
	getCurrentFunctionName(false);
}


private static void typeUsername(String userName) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(signInSelectors.userName);
	valuesArr.add(userName);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
	getCurrentFunctionName(false);
}

    
}
