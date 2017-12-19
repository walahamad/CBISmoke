package com.generic.tests.Registration;

import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.generic.page.Registration;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class RegistrationValidation extends SelTestCase {

	private static int testCaseID;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.registrationSheet + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.configInitialization();
	}

	
	@Test
	public void register() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			/*Registration.selectTitle("MRS.");
			Registration.typeFirstName("Abeer");
			Registration.typeLastName("Alia");
			Registration.typeEmailAddress("aalia@itg.com");
			Registration.typePassword("123456aA");
			Registration.typeConfirmPassword("123456aA");
			Registration.checkConsentGiven1(false);
			Registration.clickRegistration();*/
			Registration.fillAndClickRegister("MRS.", "Abeer", "Alia", "aalia@itg.com", "123456aA", "123456aA", true);
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
