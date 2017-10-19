package com.generic.selenium.testRunners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.generic.selenium.LogIn.Login_validation;
import com.generic.selenium.setup.SelTestCase;
import com.generic.selenium.util.SendMail;



@RunWith(Suite.class)
@SuiteClasses({
	Login_validation.class  
})

public class SignInRunner {

	@AfterClass
	public static void globalTearDown()
	{
		SelTestCase.getCurrentFunctionName(true);
		
        if (SelTestCase.getCONFIG().getProperty("EmailReport").equalsIgnoreCase("yes"))
        {
        	SendMail.sendSummeryMail(SelTestCase.logDir + "//" +"index.html");
        }
        else
        {
        	SelTestCase.logs.debug("Ignor sending report");
        }
        SelTestCase.getCurrentFunctionName(false);
	}
	
}


