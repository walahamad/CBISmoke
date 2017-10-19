package com.generic.selenium.LogIn;



import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.generic.selenium.report.ReportUtil;
import com.generic.selenium.setup.ActionDriver;
import com.generic.selenium.setup.Common;
import com.generic.selenium.setup.SelTestCase;
import com.generic.selenium.setup.SheetVariables;
import com.generic.selenium.util.SelectorUtil;
import com.generic.selenium.util.TestUtilities;

/**
 * The Class Login_validation used to test the following areas:
 * 1-Verify all sign in page elements exist such as the logo, header, mandatory rules, tab bar tabs, sign in form , footer
 * 2-Type different values in the userName, password.
 * 3-Click on the sign in button and check the results.
 */

public class Login_validation extends SelTestCase {
	
	public String userName ;
	public String password ;
	
	@BeforeClass
	public static void initialSetUp(){
		tempTCID = SheetVariables.SignInTestCaseId;
		testCaseDescription = SheetVariables.SignInTestCaseDescription;

        try {                   
            //Initialize the property file
            TestUtilities.initialize();

        } catch (Exception e) {
            e.printStackTrace();
            Assume.assumeTrue(false);
        }
	}
	
	
	
	@Test
	public void signIn() throws Exception {
	    
	    setStartTime(ReportUtil.now(time_date_format));
	    setTestCaseDescription(testCaseDescription);
	    String[] subStrArr = {"title","firstName","lastName","email","password","checkPwd","Register"};
	    String[] valuesArr = {"Mrs.","Abeer","Alia","aa@gg.cc","123","123",""};
	    try {
	    	SelectorUtil.initializeElementsSelectorsMaps(subStrArr, valuesArr);
	    	for(String key : SelectorUtil.selectorToElementsMap.keySet()) {
	    		Elements foundElements = SelectorUtil.selectorToElementsMap.get(key);
	    		By selVal = SelectorUtil.getBySelectorForElements(foundElements, key.substring(0, key.indexOf("_")));
	    		String val = SelectorUtil.selectorToValuesMap.get(key);
	    		doAppropriateAction(foundElements, selVal, val);
	    	}
	    	Thread.sleep(40000);
	        Common.testPass();
	    } catch (Throwable t) {
	        setTestCaseDescription(getTestCaseDescription());
	        logs.debug(t.getMessage());
	        t.printStackTrace();
	        String temp = getTestCaseId();
	        Common.testFail(t, temp);
	        Common.takeScreenShot();
	        Assert.assertTrue(t.getMessage(),false);
	    }
	
	
	}
	
	private void doAppropriateAction(Elements foundElements, By selector, String value) {
		try {
			for (org.jsoup.nodes.Element e : foundElements) {
				if (selector != null) {
					if (e.tagName().equals("input")) {
						ActionDriver.type(selector, value);
					} else if (e.tagName().equals("select")) {
						ActionDriver.selectByText(selector, value);
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
