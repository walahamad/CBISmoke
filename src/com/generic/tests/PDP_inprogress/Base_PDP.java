/**
 * this generic test for PDP regression that will pull tests from PDPRegression tab from
 * datasheet.xlsx. 
*/
package com.generic.tests.PDP_inprogress;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class Base_PDP extends SelTestCase {

	private static LinkedHashMap<String, Object> invintory;
	private static LinkedHashMap<String, Object> users;

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PDPSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	
	LinkedHashMap<String, Object> productDetails;

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.PDPCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
		users = Common.readUsers();
	}

	@DataProvider(name = "PDPs", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "PDPs")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String product,
			String email, String ValidationMsg) throws Exception {
		String UsedEmail;
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("PDP_" + getBrowserName()));
		setTestCaseReportName("PDP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.PDPDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		UsedEmail = getSubMailAccount(email);
		try {

			if (proprties.contains("Loggedin")) {
				LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
				Testlogs.get().debug("Used mail to login: "+UsedEmail);
				Testlogs.get().debug("Used Password to login: "+(String) userdetails.get(Registration.keys.password) );
				SignIn.logIn(UsedEmail, (String) userdetails.get(Registration.keys.password));
			}
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			Testlogs.get().debug("productDetails" + Arrays.asList(productDetails));
			Testlogs.get().debug("url key " + PDP.keys.url);
			Testlogs.get().debug("url key value " + (String) productDetails.get(PDP.keys.url));
			getDriver().get((String) productDetails.get(PDP.keys.url));

			
			if (proprties.contains("price") ){
				logs.debug("checking PDP price");
				String price = PDP.getPrice();
				sassert().assertTrue(price.contains((String) productDetails.get(PDP.keys.price)), "<font color=#f442cb>product price is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//price check

			if (proprties.contains("title") ){
				logs.debug("checking PDP title");
				String title = PDP.getTitle();
				sassert().assertTrue(title.contains((String) productDetails.get(PDP.keys.title)), "<font color=#f442cb>product title is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//title check
			
			if (proprties.contains("add to cart button") ){
				logs.debug("checking PDP add to cart button");
				sassert().assertTrue(PDP.checkAddToCartButton(), "<font color=#f442cb>product desc is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//add to cart button check
			
			if (proprties.contains("stock availability") ){
				logs.debug("checking PDP stock availability");
				String SL = PDP.getStockAvailability();
				Testlogs.get().debug(SL);
				ReportUtil.takeScreenShot(getDriver());
			}//stock availability check
			
			if (proprties.contains("id") ){
				logs.debug("checking PDP ID");
				String Id = PDP.getId();
				sassert().assertTrue(Id.contains((String) productDetails.get(PDP.keys.id)), "<font color=#f442cb>product id is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//id check
			
			if (proprties.contains("overview") ){
				logs.debug("checking PDP overview");
				String overView = PDP.getOverView();
				sassert().assertTrue(overView.trim().contains(((String) productDetails.get(PDP.keys.overview)).trim()),
						"<font color=#f442cb>product OverView is not expected found: " + overView + " <br>expected: "
								+ (String) productDetails.get(PDP.keys.overview) + "</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//summary check
			
			if (proprties.contains("feature") ){
				logs.debug("checking PDP feature");
				String Pdesc = PDP.getFeatures();
				sassert().assertTrue(Pdesc.contains((String) productDetails.get(PDP.keys.features)), "<font color=#f442cb>product features is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//desc check
			
			sassert().assertAll();
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

	}// test

}
