package com.generic.tests.Cart;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.page.Cart;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class CartValidationContinueShopping extends CartValidationBase {
	@Test
	public void signIn() throws Exception {
		setTestCaseDescription("");
		try {
			prepareCartLoggedInUser();
			Cart.clickContinueShoping();
			getDriver().getCurrentUrl().equals("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/");
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

	@Test
	public void notSignIn() throws Exception {
		setTestCaseDescription("");
		try {
			prepareCartNotLoggedInUser();
			Cart.clickContinueShoping();
			getDriver().getCurrentUrl().equals("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/");
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
