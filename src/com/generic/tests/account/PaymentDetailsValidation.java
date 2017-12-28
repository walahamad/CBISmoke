package com.generic.tests.account;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;
import com.generic.page.PDP;
import com.generic.page.PaymentDetails;
import com.generic.page.Registration;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.SelectorUtil;

public class PaymentDetailsValidation extends SelTestCase {

	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> invintory = null;
	private static LinkedHashMap<String, Object> paymentCards = null;
	private static LinkedHashMap<String, Object> users = null;
	String DefaultPaymentDetails;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.PaymentDetailsSheet;

	private String email;
	private String orderId;
	private String orderTotal;
	private String orderSubtotal;
	private String orderTax;
	private String orderShipping;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("checkout_setup"));
		testObject = test;
		addresses = Common.readAddresses();
		invintory = Common.readLocalInventory();
		paymentCards = Common.readPaymentcards();
		users = Common.readUsers();
	}

	@DataProvider(name = "Payment", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		if (testObject.getParameter("browserName").equals("firefox"))
			Thread.sleep(500);
		if (testObject.getParameter("browserName").equals("chrome"))
			Thread.sleep(700);

		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Payment")
	public void PaymentDetailsTest(String caseId, String runTest, String desc, String proprties, String url,
			String products, String shippingMethod, String payment, String shippingAddress, String billingAddress,
			String email, String orderId, String orderTotal, String orderSubtotal, String orderTax,
			String orderShipping) throws Exception {
		Testlogs.set(new SASLogger("Payment_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("Payment Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.PAYMENTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), payment, shippingMethod));

		this.email = email.replace("tester", "tester_" + getBrowserName().replace(" ", "_"));

		try {

			// you need to maintain the concurrency and get the main account
			// information and log in in browser account
			LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
			Testlogs.get().debug(this.email);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password));
			// SignIn.logIn(this.email, (String)
			// userdetails.get(Registration.keys.password));

			if (proprties.contains("update default")) {
				SignIn.logIn(this.email, (String) userdetails.get(Registration.keys.password));
				// Go to Payment details page.
				getDriver().get(url);
				// Save first payment(Default Payment).
				DefaultPaymentDetails = PaymentDetails.getFirstPaymentDetails();

				//
				for (String product : products.split("\n")) {
					Testlogs.get().debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
					LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory
							.get(product);
					PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
							(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
							(String) productDetails.get(PDP.keys.qty));
					// Thread.sleep(1000);
					// ReportUtil.takeScreenShot(getDriver());
				}

				// Cart.getNumberOfproducts();
				this.orderSubtotal = Cart.getOrderSubTotal();
				this.orderTax = Cart.getOrderTax();

				Cart.clickCheckout();

				// Validate the order sub total in shipping address form section
				sassert().assertEquals(CheckOut.shippingAddress.getOrdersubTotal(), this.orderSubtotal);

				// checkout- shipping address

				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses
						.get(shippingAddress);

				CheckOut.shippingAddress.fillAndClickNext(
						(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.title),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), true);

				// Validate the order sub total in shipping method section
				sassert().assertEquals(CheckOut.shippingMethod.getOrderSubTotal(), this.orderSubtotal);

				// Shipping method
				CheckOut.shippingMethod.fillAndclickNext(shippingMethod);

				// Validate the order sub total in billing form section
				sassert().assertEquals(CheckOut.paymentInnformation.getOrderSubTotal(), this.orderSubtotal);

				// checkout- payment

				LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards
						.get(payment);
				LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses
						.get(billingAddress);

				CheckOut.paymentInnformation.fillAndclickNext(payment,
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.name),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.number),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC), true,
						billingAddress.equalsIgnoreCase(shippingAddress),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.title),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.postal),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.phone));

				// Validate the order subtotal in order review section
				sassert().assertEquals(CheckOut.reviewInformation.getSubtotal(), this.orderSubtotal);

				CheckOut.reviewInformation.acceptTerms(true);
				CheckOut.reviewInformation.placeOrder();

				// Validate the order sub total in order review section
				sassert().assertEquals(CheckOut.orderConfirmation.getSubTotal(), this.orderSubtotal);

				this.orderTotal = CheckOut.orderConfirmation.getOrderTotal();
				this.orderShipping = CheckOut.orderConfirmation.getShippingCost();
				this.orderId = CheckOut.orderConfirmation.getOrderId();

				// TODO: compare addresses
				CheckOut.orderConfirmation.getBillingAddrerss();
				CheckOut.orderConfirmation.getShippingAddrerss();
				// A new billing information is added successfully.

				// Validate the default billing address is updated correctly
				getDriver().get(url);
				assertEquals(DefaultPaymentDetails, PaymentDetails.getFirstPaymentDetails());
				PaymentDetails.clickSetAsDefault();
				assertNotEquals(DefaultPaymentDetails, PaymentDetails.getFirstPaymentDetails());
			}
			 if (desc.contains("delete")) {
			 SignIn.logIn("etabib@pfsweb.com", "password");
			 getDriver().get(url);
			 PaymentDetails.getFirstPaymentDetails();
			 logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT,
			 SelectorUtil.numberOfFoundElements));
			 int numberofSavedPayments = SelectorUtil.numberOfFoundElements;
			 PaymentDetails.clickRemovePaymentDetailsBtn();
			 Thread.sleep(7000);
			 PaymentDetails.clickDeleteBtn();
			
			 PaymentDetails.getFirstPaymentDetails();
			 logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT,
			 SelectorUtil.numberOfFoundElements));
			 assertNotEquals(numberofSavedPayments,
			 SelectorUtil.numberOfFoundElements);
			 }

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
