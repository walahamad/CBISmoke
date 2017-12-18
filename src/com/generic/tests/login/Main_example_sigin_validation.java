package com.generic.tests.login;

import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class Main_example_sigin_validation extends SelTestCase {

	private static int testCaseID;


	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.checkoutTestCaseId + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.configInitialization();
	}

	
	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			//SignIn.logIn("ibatta@dbi.com", "1234567");
			SignIn.typeUsername("any");
			SignIn.typePassword("any");
			SignIn.clickLogin();
			SignIn.getErrorMsg();
			
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
