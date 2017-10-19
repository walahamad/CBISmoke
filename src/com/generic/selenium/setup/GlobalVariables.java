package com.generic.selenium.setup;

import java.util.ArrayList;



public class GlobalVariables extends SelTestCase {


    public String validLoggedInUserName;
    public String validLoggedInUserPassword;
    public String invalidLoggedInUserName;
    public String invalidLoggedInUserPassword;
    public static final String EMPTY_USER_NAME_PASSWORD_ERROR ="You must enter a username";
    public static final String EMPTY_USERNAME_ERROR = "You must enter a username";
    public static final String EMPTY_PASSWORD_ERROR = "You must enter a password";
    public static final String IN_VALID_USERNAME_PASSWORD_PAIR_ERROR = "Authentication error: User is disabled<br>Try entering the correct information or contact your DEXcenter Administrator if you believe this is an error.";

    public ArrayList<String> list;

    public static boolean flag = false;

    public GlobalVariables() {
    	getCurrentFunctionName(true);
    	logs.debug("initializing Global Variables...");
        
    	validLoggedInUserName = SelTestCase.getCONFIG().getProperty("validUserName");
    	validLoggedInUserPassword = SelTestCase.getCONFIG().getProperty("validPassword");
    	invalidLoggedInUserName = SelTestCase.getCONFIG().getProperty("invalidUserName");
    	invalidLoggedInUserPassword = SelTestCase.getCONFIG().getProperty("invalidPassword");
       
        SelTestCase.getCurrentFunctionName(false);

    }



}
