package com.generic.test_runners;

import java.text.MessageFormat;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.checkout.Base_checkout;
import com.generic.util.SendMail;



@RunWith(Suite.class)
@SuiteClasses({
	Base_checkout.class  
})

public class CheckoutRunner {

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
        	SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, "Ignor sending report"));
        }
        SelTestCase.getCurrentFunctionName(false);
	}
	
}


