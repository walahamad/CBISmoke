package com.generic.tests.LogIn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.LinkedHashMap;

import com.generic.report.ReportUtil;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.SelectorUtil;
import com.generic.util.TestUtilities;
import com.generic.util.RandomUtilities;

@RunWith(Parameterized.class)
public class Login_validation extends SelTestCase {

	private static int testCaseID;

	static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();

	String desc; 
	String email;
	String checkEmail;
	String firstName;
	String lastName;
	String country;
	String postalCode;
	String password;
	String checkPwd;
	String createAccount;
	String email_errors;
	String checkEmail_errors;
	String firstName_errors;
	String lastName_errors;
	String country_errors;
	String postalCode_errors;
	String pwd_errors;
	String checkPwd_errors;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		tempTCID = SheetVariables.registrationTestCaseId + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.initialize();

		int sheetColNum = getDatatable().getColumnCount(SheetVariables.registrationSheet);
		for (int i = 0; i < sheetColNum; i++) {
			String selIdentifierValue = getDatatable().getCellData(SheetVariables.registrationSheet, i, 1);
			if (selIdentifierValue.contains("sel"))
				subStrArr.add(selIdentifierValue.replace("sel_", ""));
		}

	}

	public Login_validation(String desc, String email, String checkEmail, String firstName, String lastName, String country,
			String postalCode, String password, String checkPwd, String createAccount, String email_errors,
			String checkEmail_errors, String firstName_errors, String lastName_errors, String country_errors,
			String postalCode_errors, String pwd_errors, String checkPwd_errors) {

		// have those variables to give readability and power to control/customize them
		this.desc = desc;
		
		if (email.equals("random_not_equal")) {
			this.email = RandomUtilities.getRandomEmail().toLowerCase();
			this.checkEmail = RandomUtilities.getRandomEmail().toLowerCase();
		} else if (email.equals("random_equal")) {
			this.email = RandomUtilities.getRandomEmail().toLowerCase();
			this.checkEmail = this.email;
		} else {
			this.email = email;
			this.checkEmail = checkEmail;
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.postalCode = postalCode;
		this.password = password;
		this.checkPwd = checkPwd;
		this.createAccount = createAccount;
		this.email_errors = email_errors;
		this.checkEmail_errors = checkEmail_errors;
		this.firstName_errors = firstName_errors;
		this.lastName_errors = lastName_errors;
		this.country_errors = country_errors;
		this.postalCode_errors = postalCode_errors;
		this.pwd_errors = pwd_errors;
		this.checkPwd_errors = checkPwd_errors;

	}

	@Parameters(name = "caseID_:{0}_{index}")
	public static Collection<Object[]> loadTestData() throws Exception {

		Object[][] data = TestUtilities.getData(SheetVariables.registrationTestCaseId);
		return Arrays.asList(data);

	}

	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {

			valuesArr.add(email);
			valuesArr.add(checkEmail);
			valuesArr.add(firstName);
			valuesArr.add(lastName);
			valuesArr.add(country);
			valuesArr.add(postalCode);
			valuesArr.add(password);
			valuesArr.add(checkPwd);
			valuesArr.add(createAccount);
			valuesArr.add(email_errors);
			valuesArr.add(checkEmail_errors);
			valuesArr.add(firstName_errors);
			valuesArr.add(lastName_errors);
			valuesArr.add(country_errors);
			valuesArr.add(postalCode_errors);
			valuesArr.add(pwd_errors);
			valuesArr.add(checkPwd_errors);

			Thread.sleep(2000);
			initializeSelectorsAndDoActions(subStrArr, valuesArr);
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(t.getMessage());
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

	private void initializeSelectorsAndDoActions(List<String> subStrArr, List<String> valuesArr) throws InterruptedException, IOException {
		LinkedHashMap<String, LinkedHashMap> webElementsInfo = new LinkedHashMap<String, LinkedHashMap>();

		int index = 0;
		boolean isValidationStep = false;
		for (String key : subStrArr) {
			//logs.debug(key);
			LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
			webElementInfo.put("value", valuesArr.get(index));
			webElementInfo.put("selector", "");
			webElementInfo.put("action", "");
			webElementInfo.put("SelType", "");
			index++;

			webElementsInfo.remove(key);
			webElementsInfo.put(key, webElementInfo);
		}
		logs.debug(Arrays.asList(webElementsInfo));
		SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
		logs.debug(Arrays.asList(webElementsInfo));

		for (String key : webElementsInfo.keySet()) {
			LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
			SelectorUtil.doAppropriateAction(webElementInfo);
		}

		
		Thread.sleep(3000);
		
		if ( !email_errors.trim().equals("")
			 || !checkEmail_errors.trim().equals("")
			 || !firstName_errors.trim().equals("")
			 || !lastName_errors.trim().equals("")
			 || !country_errors.trim().equals("")
			 || !postalCode_errors.trim().equals("")
			 || !pwd_errors.trim().equals("")
			 || !checkPwd_errors.trim().equals(""))
		{
			logs.debug("==============> Validation <================");
			logs.debug("email: "+email);
			logs.debug("checkEmail: "+checkEmail);
			logs.debug("firstName: "+firstName);
			logs.debug("lastName: "+lastName);
			logs.debug("country: "+country);
			logs.debug("postalCode: "+postalCode);
			logs.debug("password: "+password);
			logs.debug("checkPwd: "+checkPwd);
			logs.debug("createAccount: "+createAccount);
			logs.debug("email_errors: "+email_errors);
			logs.debug("checkEmail_errors: "+checkEmail_errors);
			logs.debug("firstName_errors: "+firstName_errors);
			logs.debug("lastName_errors: "+lastName_errors);
			logs.debug("country_errors: "+country_errors);
			logs.debug("postalCode_errors: "+postalCode_errors);
			logs.debug("pwd_errors: "+pwd_errors);
			logs.debug("checkPwd_errors: "+checkPwd_errors);
			
			isValidationStep = true;
			// TODO: make isValidationStep value come with case 
			logs.debug(Arrays.asList(webElementsInfo));
			SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
			logs.debug(Arrays.asList(webElementsInfo));
			for (String key : webElementsInfo.keySet())
			{
			   LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
			   SelectorUtil.doAppropriateAction(webElementInfo);
			}
			SelectorUtil.isAnErrorSelector = Boolean.FALSE;
			
			
			
		}
		
		valuesArr.clear();
		logs.debug("FINISHED");

	}

}
