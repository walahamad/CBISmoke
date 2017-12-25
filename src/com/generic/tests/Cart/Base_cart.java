package com.generic.tests.Cart;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Cart;
import com.generic.page.PDP;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.TestUtilities;

public class Base_cart extends SelTestCase {

	private static LinkedHashMap<String, Object> invintory;
	private static LinkedHashMap<String, Object> users;

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.cartSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.cartCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
		users = Common.readUsers();
	}

	@DataProvider(name = "Carts", parallel = false)
	public static Object[][] loadTestData() throws Exception {
		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "Carts")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String products,
			String email, String newQTY, String coupon, String ValidationMsg) throws Exception {
		// Important to add this for logging/reporting
		setTestCaseReportName("cart Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CARTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), coupon, newQTY));
		try {
			
			if(proprties.contains("Loggedin"))
				for(String product : products.split("\n"))
					prepareCartLoggedInUser(email,product);
			else
				for(String product : products.split("\n"))
					prepareCartNotLoggedInUser(product);
			
			//TODO: Add check UI if it is exsist 
				//TODO: add link check 
				//TODO: add picture check
			//TODO: apply coupon and check effect   
			//TODO: Change QTY 
			
			ReportUtil.takeScreenShot(getDriver());
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		} // catch

	}//test

	public void prepareCartNotLoggedInUser(String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
		LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
		PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
				(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
				(String) productDetails.get(PDP.keys.qty));
	}

	public void prepareCartLoggedInUser(String user, String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, user));
		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(user);
		logs.debug((String) userDetails.get("password"));
		logs.debug((String) userDetails.get("mail"));
		SignIn.logIn((String) userDetails.get("mail"), (String) userDetails.get("password"));

		logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
		LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
		PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
				(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
				(String) productDetails.get(PDP.keys.qty));
		
	}
	
	public void prepareCartNotLoggedInUser() {
		// TODO Auto-generated method stub
		
	}
	
	public void prepareCartLoggedInUser() {
		// TODO Auto-generated method stub
		
	}

}
