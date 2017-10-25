package com.generic.selenium.LogIn;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

import com.generic.selenium.report.ReportUtil;
import com.generic.selenium.setup.ActionDriver;
import com.generic.selenium.setup.Common;
import com.generic.selenium.setup.SelTestCase;
import com.generic.selenium.setup.SheetVariables;
import com.generic.selenium.util.SelectorUtil;
import com.generic.selenium.util.TestUtilities;


@RunWith(Parameterized.class)
public class Login_validation extends SelTestCase {
	
	public String userName ;
	public String password ;
	private static int testCaseID;
	@BeforeClass
	public static void initialSetUp(){
		tempTCID = SheetVariables.registrationTestCaseId+"_"+ testCaseID;
	}
	
	public Login_validation(int tcid) {
		testCaseID = tcid;
	}
	
	@Parameters( name = "caseID_:{0}_{index}" )
    public static List<Object> loadTestData() throws Exception{
		 try {                   
	            //Initialize the property file
	            TestUtilities.initialize();

	        } catch (Exception e) {
	            e.printStackTrace();
	            Assume.assumeTrue(false);
	        }
    	int regExecRowNum = getDatatable().getCellRowNum(SheetVariables.testCasesSheet, "TCID",SheetVariables.registrationTestCaseId);
    	int execNumber = (int) Double.parseDouble(getDatatable().getCellData(SheetVariables.testCasesSheet, "NumberOfTestCases", regExecRowNum));
    	Object[] data = new Object[execNumber];
    	for (int i = 0; i< execNumber; i++) {
    		data[i] = i;
    	}
        return Arrays.asList(data);
    }
	@Test
	public void signIn() throws Exception {
	    setStartTime(ReportUtil.now(time_date_format));
	    int sheetColNum = getDatatable().getColumnCount(SheetVariables.registrationSheet);
	    List<String> subStrArr = new ArrayList<String>();
	    List<String> valuesArr = new ArrayList<String>();
	    int i;
	    try {
		    for (i = 0 ; i < sheetColNum; i++) {
		    	String selIdentifierValue = getDatatable().getCellData(SheetVariables.registrationSheet,i,1);
		    	if (!(selIdentifierValue.contains("error"))) {
		    		subStrArr.add(selIdentifierValue);
			    	valuesArr.add(getDatatable().getCellData(SheetVariables.registrationSheet,i,2+testCaseID));
		    	} else {
		    		break;
		    	}
		    }
		    Thread.sleep(2000);
		    initializeSelectorsAndDoActions(subStrArr, valuesArr);
		    subStrArr.clear();
		    valuesArr.clear();
		    Thread.sleep(2000);
		    for (; i < sheetColNum; i++) {
		    	String selIdentifierValue = getDatatable().getCellData(SheetVariables.registrationSheet,i,1);
		    	if ((selIdentifierValue.contains("error"))) {
		    		subStrArr.add(selIdentifierValue);
			    	valuesArr.add(getDatatable().getCellData(SheetVariables.registrationSheet,i,2+testCaseID));
		    	} else {
		    		continue;
		    	}
		    }
		    Thread.sleep(2000);
		    initializeSelectorsAndDoActions(subStrArr, valuesArr);
		    /*String[] subStrArr = {"title","firstName","lastName","email","password","checkPwd","consentGiven1","Register"};
		    String[] valuesArr = {"Mrs.","Abeer","Alia","aa@gg.cc","123456a","123456a","",""};*/
		    /*String[] subStrArr = {"email","checkEmail","firstName","lastName","country","postalCode","password","checkPwd","createAccount"};
		    String[] valuesArr = {"aa@gg.cc","aa@gg.cc","Abeer","Alia","Canada","44122","123456a","123456a",""};*/
		    
		    	
	    	Thread.sleep(2000);
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
	
	private void initializeSelectorsAndDoActions(List<String> subStrArr, List<String> valuesArr) {
		SelectorUtil.initializeElementsSelectorsMaps(subStrArr, valuesArr);
    	for(String key : SelectorUtil.selectorToElementsMap.keySet()) {
    		Elements foundElements = SelectorUtil.selectorToElementsMap.get(key);
    		By selVal = SelectorUtil.getBySelectorForElements(foundElements, key.substring(0, key.indexOf("_")));
    		String val = SelectorUtil.selectorToValuesMap.get(key);
    		Boolean isErrorSel = SelectorUtil.selectorForErrorMsgsMap.get(key);
    		doAppropriateAction(foundElements, selVal, val,isErrorSel);
    	}
	}
	private void doAppropriateAction(Elements foundElements, By selector, String value, Boolean isErrorSel) {
		try {
			for (org.jsoup.nodes.Element e : foundElements) {
				if (selector != null) {
					if (e.tagName().equals("input") && (e.attr("type").equals("text") || e.attr("type").equals("password") )) {
						ActionDriver.type(selector, value);
					} else if (e.tagName().equals("select")) {
						ActionDriver.selectByText(selector, value);
					} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox")){
						boolean selected = e.attr("checked").equalsIgnoreCase("checked");
				        if(!selected)
				        {
				           ActionDriver.click(selector);
				        }
					} else if (e.tagName().equals("button")) {
						ActionDriver.click(selector);
					} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
						ActionDriver.click(selector);
					} else if (e.tagName().equals("span") && isErrorSel) {
						if (!value.isEmpty()) {
							Assert.assertNotEquals("The "+ e.id() + "has incorrect error msg", e.text(), value);
							logs.debug("The "+ e.id() + "is found and has correct error msg");
						}
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
