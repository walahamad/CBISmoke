package com.generic.tests.LogIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import java.util.LinkedHashMap;

import com.generic.report.ReportUtil;
import com.generic.setup.ActionDriver;
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
			subStrArr.add(selIdentifierValue);
		}

	}

	public Login_validation(String email, String checkEmail, String firstName, String lastName, String country,
			String postalCode, String password, String checkPwd, String createAccount, String email_errors,
			String checkEmail_errors, String firstName_errors, String lastName_errors, String country_errors,
			String postalCode_errors, String pwd_errors, String checkPwd_errors) {

		// have those variables to give readability and power to control/customize them
		this.email = RandomUtilities.getRandomEmail().toLowerCase();
		this.checkEmail = this.email;
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

	private void initializeSelectorsAndDoActions(List<String> subStrArr, List<String> valuesArr) {
		LinkedHashMap<String, LinkedHashMap> webElementsInfo = new LinkedHashMap<String, LinkedHashMap>();

		int index = 0;
		for (String key : subStrArr) {
			logs.debug(key);
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
		SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo);
		logs.debug(Arrays.asList(webElementsInfo));

		for (String key : webElementsInfo.keySet()) {
			LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
			SelectorUtil.doAppropriateAction(webElementInfo);
		}
		valuesArr.clear();
		logs.debug("FINISHED");

	}

}
