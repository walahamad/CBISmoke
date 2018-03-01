package com.generic.tests.MyAccount_addressBook_inprogress;

import static org.testng.Assert.assertNotEquals;

import java.text.MessageFormat;

import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;

import com.generic.page.Registration;
import com.generic.page.AddressBook;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.selector.AddressBookSelectors;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.SelectorUtil;

public class AddressBookValidationBase extends SelTestCase {
	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> users = null;
	private String numberofaddresses;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.AddressBookSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		addresses = Common.readAddresses();
		users = Common.readUsers();
		testObject = test;
	}

	@DataProvider(name = "AddressBook", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		return data;
		
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "AddressBook")
	public void BookAddressTest(String caseId, String runTest,String desc, String prop, String email, String newaddress)
			throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("Address_Book " + getBrowserName()));
		setTestCaseReportName("Address Book Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.ADDRESSPOOKDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		String caseEmail = getSubMailAccount(email);
		String url = PagesURLs.getAddressBookPage();
		boolean defaultAddress = prop.contains("default Address") ;
		
		try {
			LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
			Testlogs.get().debug(caseEmail);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password));
			
			SignIn.logIn(caseEmail, (String) userdetails.get(Registration.keys.password));
			
			if (prop.contains("new") || prop.contains("default") || prop.contains("delete")) {
				getDriver().get(url);
				Thread.sleep(1000);
				AddressBook.clickAddNewAddress();
				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses
						.get(newaddress);
				AddressBook.fillAndClickSave(caseEmail,
						(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						"NEW_" + RandomUtils.nextInt(1000, 9000),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), defaultAddress);
				if (prop.contains("new")) {
					String siteAddress = AddressBook.getFirstAddressDetails();
					Testlogs.get().debug(siteAddress);
					sassert().assertTrue(siteAddress.contains((String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine)),
							"Address doesnt contain line1: " + siteAddress);
				}
				
			}
			
			if (prop.contains("edit")) {
				getDriver().get(url);
				String addressbook = AddressBook.getFirstAddressDetails();
				AddressBook.clickEditAddress();
				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses.get(newaddress);
				AddressBook.fillAndClickUpdate(caseEmail,
						(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						"NEW_" + RandomUtils.nextInt(1000, 9000),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), false);
				assertNotEquals(addressbook, AddressBook.getFirstAddressDetails());
			}
			
			//Remove the created address.
			if (prop.contains("delete"))
				AddressBook.clickRemoveAddress();
			
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
}// class
