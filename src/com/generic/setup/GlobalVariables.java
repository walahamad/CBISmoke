package com.generic.setup;

import java.util.ArrayList;

import com.generic.setup.SelTestCase;



public class GlobalVariables extends SelTestCase {


	/*public String firstName;
    public String lastName;
    public String emailAddress;
    public String confEmailAddress;
    public String zipCode;
    public String country;
    public String password;
    public String confPassword;*/
   
    public ArrayList<String> list;

    public static boolean flag = false;

   
    public GlobalVariables(String sheetName, int row) {
    	getCurrentFunctionName(true);
    	logs.debug("reading data from sheet: " + sheetName + " row:" + row);

       /* firstName = SelTestCase.getCONFIG().getProperty("firstName");
        lastName = SelTestCase.getCONFIG().getProperty("lastName");
        emailAddress = SelTestCase.getCONFIG().getProperty("emailAddress");
        confEmailAddress = SelTestCase.getCONFIG().getProperty("confEmailAddress");
        zipCode = SelTestCase.getCONFIG().getProperty("postalCode");
        password = SelTestCase.getCONFIG().getProperty("password");
        confPassword = SelTestCase.getCONFIG().getProperty("confPassword");
        country = SelTestCase.getCONFIG().getProperty("country");
        */
        SelTestCase.getCurrentFunctionName(false);

    }
     
}
