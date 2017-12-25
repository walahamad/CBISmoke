package com.generic.tests.account;

import java.text.MessageFormat;
import static org.testng.Assert.*;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.LinkedHashMap;

import com.generic.page.AddressBook;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.selector.AddressBookSelectors;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.SelectorUtil;
import com.generic.util.TestUtilities;

@RunWith(Parameterized.class)
public class AddressBookValidation extends SelTestCase {

	public static final LinkedHashMap<String, Object> addresses = Common.readAddresses();
	boolean defaultAddress = true;
	private String addressbook;
	private int numberofaddresses;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.AddressBookSheet;

	private String caseId;
	private String runTest;
	private String url;
	private String desc;
	private String newaddress;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.addressbookTestCaseId;
		TestUtilities.configInitialization();
	}

	public AddressBookValidation(String caseId, String runTest, String url, String desc, String newaddress) {
		this.caseId = caseId;
		this.runTest = runTest;
		this.url = url;
		this.desc = desc;
		this.newaddress = newaddress;
	}

	@Parameters(name = "{index}_:{3}")
	public static Collection<Object[]> loadTestData() throws Exception {
		Object[][] data = TestUtilities.getData(testDataSheet);
		return Arrays.asList(data);
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test
	public void BookAddressTest() throws Exception {
		try {
			SignIn.typeUsername("etabib@pfsweb.com");
			SignIn.typePassword("password");
			SignIn.clickLogin();
			// AddressBook.clickmyaccount();
			// AddressBook.clickaddressbook();
			getDriver().get(url);
			if (desc.contains("edit")) {
				addressbook = AddressBook.getFirstAddressDetails();
				AddressBook.clickEditAddress();
				AddressBook.updateAddress();
				AddressBook.clickAddressBackBtn();
				// getDriver().get(AddressBookSelectors.addressbookurl);
				assertNotEquals(addressbook, AddressBook.getFirstAddressDetails());
			}
			if (desc.contains("form validation")) {
				addressbook = AddressBook.getFirstAddressDetails();
				AddressBook.clickEditAddress();
				AddressBook.clearAddress();
				AddressBook.verifyAddressFormError();
			}
			if (desc.contains("new")) {
				addressbook = AddressBook.getFirstAddressDetails();
				Thread.sleep(1000);
				AddressBook.clickAddNewAddress();
				getDriver().get(AddressBookSelectors.addaddressurl);
				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses
						.get(newaddress);
				logs.debug("test");
				AddressBook.fillAndClickSave((String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.title),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), defaultAddress);
				AddressBook.clickAddressBackBtn();
				// getDriver().get(AddressBookSelectors.addressbookurl);
				assertNotEquals(addressbook, AddressBook.getFirstAddressDetails());
			}
			if (desc.contains("default")) {
				addressbook = AddressBook.getFirstAddressDetails();
				AddressBook.clickSetAsDefault();
				AddressBook.getAlertInfo();
				// assertNotEquals(addressbook,
				// AddressBook.getFirstAddressDetails());
			}
			if (desc.contains("delete")) {
				AddressBook.getAddressBookList();
				logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT, SelectorUtil.numberOfFoundElements));
				numberofaddresses = SelectorUtil.numberOfFoundElements;
				AddressBook.clickRemoveAddress();
				AddressBook.clickDeleteBtn();
				Thread.sleep(7000);
				AddressBook.getAddressBookList();
				logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT, SelectorUtil.numberOfFoundElements));
				assertNotEquals(numberofaddresses, SelectorUtil.numberOfFoundElements);
			}
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		} // catch
	}// test
}// class
