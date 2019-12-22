package com.generic.page;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import com.generic.selector.CartSelectors;
import com.generic.selector.CheckOutSelectors;
import com.generic.selector.PayPalSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SelectorUtil;

public class CheckOut extends SelTestCase {

	public static class shippingAddress {

		public static class keys {

			public static final String countery = "countery";
			public static final String title = "title";
			public static final String lastName = "lastName";
			public static final String firstName = "firstName";
			public static final String adddressLine = "adddressLine";
			public static final String city = "city";
			public static final String zipcode = "postal";
			public static final String phone = "phone";
		}

		// Done CBI
		public static void typeFirstName(String firstName, boolean isSingle) throws Exception {
			try {
				getCurrentFunctionName(true);
				if(isSingle)
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.firstNameSingle.get(), firstName);
				else
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.firstName.get(), firstName);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static void typeLastName(String lastName, boolean isSingle) throws Exception {
			try {
				getCurrentFunctionName(true);
				if(isSingle)
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.lastNameSingle.get(), lastName);
				else
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.lastName.get(), lastName);

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static void typeStreetAddress(String streetAddress, boolean isSingle) throws Exception {
			try {
				getCurrentFunctionName(true);
				if(isSingle)
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.streetAddressSingle.get(), streetAddress);
				else
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.streetAddress.get(), streetAddress);

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		

		// Done CBI
		public static void typeCity(String city) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.city.get(), city);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static void typeZipCode(String zip, boolean isSingle) throws Exception {
			try {
				getCurrentFunctionName(true);
				if(isSingle)
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.zipCodeSingle.get(), zip);
				else
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.zipCode.get(), zip);

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static void typePhone(String phone, boolean isSingle) throws Exception {
			try {
				getCurrentFunctionName(true);
				if(isSingle)
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.phoneSingle.get(), phone);
				else
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.phone.get(), phone);

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		//Done CBI
		public static void selectState(String state) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.state.get(), state);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

	}// shipping address



	public static class paymentInnformation {

		public static class keys {
			public static final String name = "name";
			public static final String number = "number";
			public static final String expireYear = "expireYear";
			public static final String expireMonth = "expireMonth";
			public static final String CVCC = "CVCC";

		}

		// Done CBI
		public static void switchBackToMainWindow(String mainWindow) {
			try {
				getCurrentFunctionName(true);
				getDriver().switchTo().window(mainWindow);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static String switchToPayPalWindow() throws InterruptedException {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1000);
				Set<String> winIds = getDriver().getWindowHandles();
				Iterator<String> iter = winIds.iterator();
				logs.debug("number of windows:" + winIds.size());
				String main = iter.next();
				logs.debug("main window " + main);
				String paypal = iter.next();
				logs.debug("paypal window " + paypal);
				getDriver().switchTo().window(paypal);
				getCurrentFunctionName(false);
				return main;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// Done CBI
		public static void typeCVV(String CVV) throws Exception {
			try {
				getCurrentFunctionName(true);
				// Switch to cvv iframe
				Thread.sleep(1000);

				// wait for cvv iframe to load
				waitforCvvFrame();

				getDriver().switchTo().frame(GlobalVariables.CVV_Iframe_ID);
				Thread.sleep(2000);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.cvv.get(), CVV);

				// Switch to default frame
				getDriver().switchTo().defaultContent();

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
		// Done CBI
		public static void waitforCvvFrame() throws Exception {
			try {
				boolean cvvStatus = false;
				int noOfTries = 0;

				while (!cvvStatus) {
					logs.debug(cvvStatus + "  Waiting for cvv iframe");

					cvvStatus = checkCvvIframe();
					noOfTries++;

					if (noOfTries > 25)
						break;

					Thread.sleep(2000);
				}
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void shippingMethodType(String shipType) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				if (shipType.toLowerCase().equals("home"))
					subStrArr.add(CheckOutSelectors.ShippingMethodType + "1");
				else
					subStrArr.add(CheckOutSelectors.ShippingMethodType + "2");
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void clickContinueToPayement() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.continueToPaymentMethodBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(3000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void writeMail(String pemail) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.ShippingMail);
				valuesArr.add(pemail);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(3000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

	}// shipping method

	public static class paymentInnformation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String name = "name";
			public static final String number = "number";
			public static final String expireYear = "expireYear";
			public static final String expireMonth = "expireMonth";
			public static final String CVCC = "CVCC";

		}

		// done-ocm
		// new payment - same shipping address
		public static void fill(String cardtype, String cardHolder, String cardNumber, String expireMonth,
				String expireYear, String CVC) throws Exception {
			try {
				getCurrentFunctionName(true);
				if (!"".equals(cardtype))
					selectCardType(cardtype);
				if (!"".equals(cardHolder))
					typeCardholder(cardHolder);
				if (!"".equals(cardNumber))
					typeCardNumber(cardNumber);
				if (!"".equals(expireMonth) && !"".equals(expireMonth))
					typeExpireMonth(expireMonth + expireYear);
				if (!"".equals(CVC))
					typeCVV(CVC);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// done-ocm
		/**
		 * new payment with new address
		 * 
		 * @param cardtype
		 * @param cardholder
		 * @param cardNumber
		 * @param expireMonth
		 * @param expireYear
		 * @param CVC
		 * @param countery
		 * @param firstName
		 * @param lastName
		 * @param address
		 * @param city
		 * @param postal
		 * @param phone
		 * @throws Exception
		 */
		public static void fillAndclickNext(String cardtype, String cardholder, String cardNumber, String expireMonth,
				String expireYear, String CVC, String countery, String firstName, String lastName, String address,
				String city, String postal, String phone) throws Exception {
			try {

				getCurrentFunctionName(true);
				if (getBrowserName().contains("IE"))
					Thread.sleep(8000);
				// clickAddPaymentMethod();
				if (!cardtype.contains("paypal")) {
					fill(cardtype, cardholder, cardNumber, expireMonth, expireYear, CVC);
					ReportUtil.takeScreenShot(getDriver(), "payment_debug");
				}

				fillBillingAddress(countery, firstName, lastName, address, city, postal, phone);
				ReportUtil.takeScreenShot(getDriver(), "payment_debug");
				clickNext();
				ReportUtil.takeScreenShot(getDriver(), "payment_debug");
				if (cardtype.contains("paypal")) {
					Thread.sleep(20000);
					String mainWindow = switchToPayPalWindow();
					// username and password for paypal is indicated by cardNumber and cvc
					PayPal.clickLoginLink();
					PayPal.signInAndClickContinue(cardNumber, CVC);
					Thread.sleep(10000);
					switchBackToMainWindow(mainWindow);
					Thread.sleep(20000);
				}
				Thread.sleep(1000);
				ReportUtil.takeScreenShot(getDriver(), "payment_debug");
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		private static void switchBackToMainWindow(String mainWindow) {
			try {
				getCurrentFunctionName(true);
				getDriver().switchTo().window(mainWindow);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		private static String switchToPayPalWindow() throws InterruptedException {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1000);
				Set<String> winIds = getDriver().getWindowHandles();
				Iterator<String> iter = winIds.iterator();
				logs.debug("number of windows:" + winIds.size());
				String main = iter.next();
				logs.debug("main window " + main);
				String paypal = iter.next();
				logs.debug("paypal window " + paypal);
				getDriver().switchTo().window(paypal);
				getCurrentFunctionName(false);
				return main;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// saved payment
		public static void fillAndclickNext(String cardtype, String cardholder, String cardNumber, String expireDay,
				String expireYear, String CVC, boolean billSameShip, String countery, String firstName, String lastName,
				String address, String city, String postal, String phone) throws Exception {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1500);
				// clickAddPaymentMethod();
				fill(cardtype, cardholder, cardNumber, expireDay, expireYear, CVC);

				// checkBillingAddressSameshipping(billSameShip);

				// if (!billSameShip) {
				// fillBillingAddress(countery, firstName, lastName, address, city, postal,
				// phone);
				// }
				clickNext();
				Thread.sleep(1000);
				getCurrentFunctionName(false);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// TODO: do it in lowser envs
		// in case of using wallet
		public static void fillAndclickNext(boolean useAlreadySavedPayment) throws Exception {
			getCurrentFunctionName(true);
			// clickAddPaymentMethod();
			// clickOnUseedSavedCard();
			pickFirstpaymentsaved();
			Thread.sleep(4000);
			enterCvc("333");
			Thread.sleep(4000);
			getCurrentFunctionName(false);
		}

		private static void enterCvc(String cvc) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.CVV);
				valuesArr.add(cvc);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void clickContinueToOrderReview() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.orderReviewBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(2000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void fillBillingAddress(String countery, String firstName, String lastName, String address,
				String city, String postal, String phone) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(countery))
				selectCountery(countery);
			if (!"".equals(firstName))
				typeFirstName(firstName);
			if (!"".equals(lastName))
				typeLastName(lastName);
			if (!"".equals(address))
				typeAddress(address);
			if (!"".equals(city))
				typeCity(city);
			if (!"".equals(city))
				selectState(city);
			if (!"".equals(postal))
				typeZipCode(postal);
			if (!"".equals(phone))
				typePhone(phone);

			getCurrentFunctionName(false);
		}

		private static void pickFirstpaymentsaved() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.selectFirstPayment);
				valuesArr.add("FFF1");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void clickNext() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.submitPayment);
				valuesArr.add(String.valueOf(""));
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void checkBillingAddressSameshipping(boolean billSameShip) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.checkSame);
			valuesArr.add(String.valueOf(!billSameShip));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		// Done CBI
		public static void typeCVV(String CVV) throws Exception {
			try {
				getCurrentFunctionName(true);
				// Switch to cvv iframe
				Thread.sleep(1000);

				//wait for cvv iframe to load
				waitforCvvFrame();

				getDriver().switchTo().frame(GlobalVariables.CVV_Iframe_ID);
				Thread.sleep(2000);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.cvv.get(), CVV);
				
				// Switch to default frame
				getDriver().switchTo().defaultContent();

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
		// Done CBI
		public static void waitforCvvFrame() throws Exception {
			try {
				boolean cvvStatus = false;
				int noOfTries = 0;

				while (!cvvStatus) {
					logs.debug(cvvStatus + "  Waiting for cvv iframe");

					cvvStatus = checkCvvIframe();
					noOfTries++;

					if (noOfTries > 30)
						break;

					Thread.sleep(2500);
				}

			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		// Done CBI
		private static boolean checkCvvIframe() throws Exception {
			try {
				
				if (getDriver().findElements(By.id(GlobalVariables.CVV_Iframe_ID)).size() != 0)
				//if ( SelectorUtil.getAllElements((GlobalVariables.CVV_Iframe_ID)).size()!= 0)
					return true;
				else
					return false;
				
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}


		// Done CBI
		private static void typeExpireMonth(String month) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.monthField.get(),month );
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
		// Done CBI
		public static void typeExpireYear(String expireYear) throws Exception {
			try {
				getCurrentFunctionName(true);
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.yearField.get(),expireYear);
				getCurrentFunctionName(false);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void selectExpireDay(String expireDay) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.expireDay);
				valuesArr.add(expireDay);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// Done CBI
		public static void typeCardNumber(String cardNumber) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.creditCardField.get(), cardNumber);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeCardholder(String cardHolder) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.cardHolder);
				valuesArr.add(cardHolder);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void selectCardType(String cardtype) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();

				if (!cardtype.equals("paypal")) {
					subStrArr.add(CheckOutSelectors.cc);
				} else {
					subStrArr.add(CheckOutSelectors.paypal);
				}
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(4000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrdershipping() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderShippingPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrderSubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubtotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getAlertInfo() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.alertInfo));
			subStrArr.add(CheckOutSelectors.alertInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getCardTypeError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.cardTypeError));
			subStrArr.add(CheckOutSelectors.cardTypeError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getCardNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.accountNumberError));
			subStrArr.add(CheckOutSelectors.accountNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getExpirationMonthError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.expirationMonthError));
			subStrArr.add(CheckOutSelectors.expirationMonthError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getExpirationYearError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.expirationYearError));
			subStrArr.add(CheckOutSelectors.expirationYearError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getCVNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, CheckOutSelectors.cvNumberError));
			subStrArr.add(CheckOutSelectors.cvNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		// done-ocm
		public static void selectCountery(String countery) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "Countery ", countery));
				subStrArr.add(CheckOutSelectors.Bcountery);
				valuesArr.add(countery);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(1500);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeFirstName(String firstName) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
				subStrArr.add(CheckOutSelectors.BfirstName);
				valuesArr.add(firstName);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeAddress(String address) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "address ", address));
				subStrArr.add(CheckOutSelectors.Baddress);
				valuesArr.add(address);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeLastName(String lastName) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
				subStrArr.add(CheckOutSelectors.BlastName);
				valuesArr.add(lastName);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeCity(String city) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "city ", city));
				subStrArr.add(CheckOutSelectors.Bcity);
				valuesArr.add(city);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		private static void selectState(String sate) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "state ", sate));
				subStrArr.add(CheckOutSelectors.Bstate);
				valuesArr.add(sate);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typeZipCode(String zip) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "postal", zip));
				subStrArr.add(CheckOutSelectors.Bzipcode);
				valuesArr.add(zip);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-ocm
		public static void typePhone(String phone) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "phone", phone));
				subStrArr.add(CheckOutSelectors.Bphone);
				valuesArr.add(phone);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

	}// payment info

	public static class reviewInformation {

		public static String getSubtotal() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.summaryTotal);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
				getCurrentFunctionName(false);
				return SelectorUtil.textValue.get().split(":")[1].trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String shippingCost() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			//subStrArr.add(CheckOutSelectors.shippingCost);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static void acceptTerms(boolean acceptTerm) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.acceptTerm);
			valuesArr.add(String.valueOf(acceptTerm));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void clickPlaceOrderBtn() throws Exception {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1500);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.placeOrderBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String gettotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// order review information

	public static class orderConfirmation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String orderId = "orderId";
			public static final String email = "email";
			public static final String orderTotal = "orderTotal";
			public static final String orderSubtotal = "orderSubtotal";
			public static final String orderTax = "orderTax";
			public static final String orderShipping = "orderShipping";

		}


			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		// Done CBI
		private static boolean checkCvvIframe() throws Exception {
			try {
				
				if (getDriver().findElements(By.id(GlobalVariables.CVV_Iframe_ID)).size() != 0)
				//if ( SelectorUtil.getAllElements((GlobalVariables.CVV_Iframe_ID)).size()!= 0)
					return true;
				else
					return false;
				
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}


		// Done CBI
		private static void typeExpireMonth(String month) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.monthField.get(),month );
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
		// Done CBI
		public static void typeExpireYear(String expireYear) throws Exception {
			try {
				getCurrentFunctionName(true);
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.yearField.get(),expireYear);
				getCurrentFunctionName(false);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}


		// Done CBI
		public static void typeCardNumber(String cardNumber) throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.creditCardField.get(), cardNumber);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

	}// payment info



	// Done CBI
	public static void searchForProductsandAddToCart(int productsNo) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Add products to cart
			for (int i = 0; i < productsNo; i++) {
				PDP.NavigateToPDP();
				
				if (PDP.bundleProduct()) {
					PDP.clickBundleItems();
				}
				
				PDP.addProductsToCart();
				if (!getBrowserName().contains(GlobalVariables.browsers.iPhone))
					PDP.clickAddToCartCloseBtn();
				
				URI url = new URI(getURL());
				getDriver().get("https://"+url.getHost());
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static void navigatetoCart() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,
					"Navigating to cart ..." + getCONFIG().getProperty("RegistrationPage")));
			getDriver().get(new URI(getDriver().getCurrentUrl()).resolve(getCONFIG().getProperty("Cart")).toString());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
		
	// Done CBI
	public static void clickBeginSecureCheckoutButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.beginSecureCheckoutButton.get());
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Clicking Begin secure checkout button"));
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static void clickGuestCheckoutButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.guestCheckoutButton.get());
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Clicking guest checkout button"));
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static void clickMultipleAddressesTab() throws Exception {
		try {
			getCurrentFunctionName(true);
			 List <WebElement> tabs =SelectorUtil.getAllElements(CheckOutSelectors.multipleAddressesTab.get());
			 tabs.get(1).click();
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Clicking multiple address tab"));
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// Done CBI
	public static void clickAddAddressButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.multipleAddressesTab.get(), "index,1");
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Clicking multiple address tab"));
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static void fillCheckoutFirstStepAndSave(int productsCount, LinkedHashMap<String, String> addressDetalis)
			throws Exception {
		try {
			getCurrentFunctionName(true);
			for (int buttonIndex = 0; buttonIndex < productsCount; buttonIndex++) {
				//Add new address
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Clicking add new address button "));
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.addAddressButton.get(),
						"index," + buttonIndex);

				//Filling address fields
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "filling address att index " + buttonIndex));
				shippingAddress.typeFirstName(addressDetalis.get(CheckOut.shippingAddress.keys.firstName),false);
				shippingAddress.typeLastName(addressDetalis.get(CheckOut.shippingAddress.keys.lastName),false);
				shippingAddress.typeStreetAddress(RandomUtilities.getRandomName(),false);
				shippingAddress.typeZipCode(addressDetalis.get(CheckOut.shippingAddress.keys.zipcode),false);
				shippingAddress.typePhone(RandomUtilities.getRandomPhone(),false);

				//Save address button
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.saveAddressButton.get(), "");
			}

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI  --- This version is for one address case
	public static void fillCheckoutFirstStepAndSave(LinkedHashMap<String, String> addressDetalis) throws Exception {
		try {
			getCurrentFunctionName(true);

			// Filling address fields
			shippingAddress.typeFirstName(addressDetalis.get(CheckOut.shippingAddress.keys.firstName),true);
			shippingAddress.typeLastName(addressDetalis.get(CheckOut.shippingAddress.keys.lastName),true);
			shippingAddress.typeStreetAddress(RandomUtilities.getRandomName(),true);
			shippingAddress.typeZipCode(addressDetalis.get(CheckOut.shippingAddress.keys.zipcode),true);
			shippingAddress.typePhone(RandomUtilities.getRandomPhone(),true);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	

	// Done CBI
	public static int checkProductsinStepTwo() throws Exception {
		try {
			getCurrentFunctionName(true);
			int productsNumber = SelectorUtil.getAllElements(CheckOutSelectors.productContainerInStepTwo.get()).size();	
			getCurrentFunctionName(false);
			return productsNumber;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static void proceedToStepTwo() throws Exception {
		try {
			getCurrentFunctionName(true);
			// Click next to proceed to step 2
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.firstStepNextButton.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static boolean checkIfInStepTwo() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean state=false;
			Thread.sleep(1000);
		
			if (isFG()) {
				state = SelectorUtil.isDisplayed(CheckOutSelectors.stepTwoIdentifier.get());
			} else if (isGR()) {
				state = SelectorUtil.isDisplayed(CheckOutSelectors.stepTwoIdentifierGR.get());
			}
			
			getCurrentFunctionName(false);
	
			return state;		
			
		} catch (NoSuchElementException e) {
			try {
				
				if (isFG()) {
					return SelectorUtil.isDisplayed(CheckOutSelectors.stepTwoIdentifier2.get());

				} else if (isGR()) {
					return SelectorUtil.isDisplayed(CheckOutSelectors.stepTwoIdentifier2GR.get());

				}
				else {
					return false;
				}
			}
			catch (NoSuchElementException e2) {
				return false;

			}

		}
	}
	
	
	// Done CBI
	public static void proceedToStepFour() throws Exception {
		try {
			getCurrentFunctionName(true);
			//Click next to proceed to step 4
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.thirdStepNextButton.get(),"ForceAction,click");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	

	// Done CBI
	public static void proceedToStepThree() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.secondStepNextButton.get(),"ForceAction,click");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static void fillEmailBillingAddress() throws Exception {
		try {
			getCurrentFunctionName(true);
			//Fill email field
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.emailBillingAddress.get(), RandomUtilities.getRandomEmail());
			getCurrentFunctionName(false);
			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
	public static String getShippingCosts() throws Exception {
		try {
			getCurrentFunctionName(true);
			int shppingIndex = 0;

			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				shppingIndex = 1;
			}
			getCurrentFunctionName(false);
			return SelectorUtil.getNthElement(CheckOutSelectors.shippingAndTaxCost.get(), shppingIndex).getText();

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getTaxCosts(int grTaxIndex) throws Exception { //grTaxIndex this value is set 1 for GR and 0 for FG
		try {
			getCurrentFunctionName(true);
			int taxIndex = 1;
			
			if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {		
				taxIndex = 2+grTaxIndex;
			}
			getCurrentFunctionName(false);
			return SelectorUtil.getNthElement(CheckOutSelectors.shippingAndTaxCost.get(), taxIndex).getText();

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done CBI
		public static String getSubTotal() throws Exception {
			try {
				getCurrentFunctionName(true);
				int subTotalIndex = 0;

				if (getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
					getCurrentFunctionName(false);
					return SelectorUtil.getNthElement(CheckOutSelectors.shippingAndTaxCost.get(), subTotalIndex).getText();
				}
				else {
					getCurrentFunctionName(false);
					return SelectorUtil.getelement(CheckOutSelectors.subTotalValue.get()).getText();
				}
				
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
	
	// Done CBI
	public static void fillPayment( LinkedHashMap<String, String> paymentDetails) throws Exception {
		try {		
			getCurrentFunctionName(true);
			CheckOut.paymentInnformation.typeCardNumber(paymentDetails.get(CheckOut.paymentInnformation.keys.number));
			Thread.sleep(1500);
			CheckOut.paymentInnformation.typeExpireMonth (paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth));
			Thread.sleep(1500);
			CheckOut.paymentInnformation.typeExpireYear(paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear));
			Thread.sleep(1500);
			CheckOut.paymentInnformation.typeCVV(paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC));
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	
	// Done CBI
	public static void placeOrder() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.placeSecureOrderButton.get());
			getCurrentFunctionName(false);			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static void closeRegisterButton() throws Exception {
		try {	
			getCurrentFunctionName(true);

			if (!getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.closeRegisterButton.get());
			}
			
			getCurrentFunctionName(false);			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static int checkProductsinConfirmationPage() throws Exception {
		try {	
			getCurrentFunctionName(true);
			int productsNumber = SelectorUtil.getAllElements(CheckOutSelectors.itemID.get()).size();	
			getCurrentFunctionName(false);			
			return productsNumber;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static void closePromotionalModal() throws Exception {
		try {	
			getCurrentFunctionName(true);
			if (!getBrowserName().contains(GlobalVariables.browsers.iPad)) {
				  WebDriverWait	wait = new WebDriverWait(getDriver(), 25);
				  WebElement closeElement = wait.until(visibilityOfElementLocated(By.cssSelector(CheckOutSelectors.closePoromotionalModal.get())));
			      closeElement.click();			
			}
			getCurrentFunctionName(false);			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	// Done CBI
	public static class PayPal {

		public static void clickOnContinue() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug("clicking on Agree and continue btn");
				subStrArr.add(PayPalSelectors.confirmButtonTop);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		public static void paymentPageClickContinue() throws Exception {
			try {
				getCurrentFunctionName(true);
				if (!SelectorUtil.isNotDisplayed(CartSelectors.paymentPageCheckNextBtn.get()))
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.paymentPageCheckNextBtn.get());
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getConfirmationTotalValue() throws Exception {
			try {
				getCurrentFunctionName(true);
				WebElement price = SelectorUtil.getelement(CheckOutSelectors.confirmationTotal.get());
				getCurrentFunctionName(false);
				return price.getText().replace("$", "").replace(",", "").trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getConfirmationPageTaxValue() throws Exception {
			try {
				getCurrentFunctionName(true);
				WebElement price = SelectorUtil.getelement(CheckOutSelectors.confirmationPageTax.get());
				getCurrentFunctionName(false);
				return price.getText().replace("$", "").replace(",", "").trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getConfirmationPageShippingValue() throws Exception {
			try {
				getCurrentFunctionName(true);
				WebElement price = SelectorUtil.getelement(CheckOutSelectors.confirmationShipping.get());
				getCurrentFunctionName(false);
				return price.getText().replace("$", "").replace(",", "").trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getConfirmationPageSubtotalValue() throws Exception {
			try {
				getCurrentFunctionName(true);
				WebElement price = SelectorUtil.getelement(CheckOutSelectors.confirmationPageSubtotal.get());
				getCurrentFunctionName(false);
				return price.getText().replace("$", "").replace(",", "").trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isPayPalPayment() throws Exception {

			try {
				getCurrentFunctionName(true);
				WebElement price = SelectorUtil.getelement(CheckOutSelectors.confirmationPageAccountType.get());
				boolean result = false;
				if (price.getText().trim().equals("PayPal Account"))
					result = true;
				getCurrentFunctionName(false);
				return result;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean checkConfirmationPageImg() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(CheckOutSelectors.confirmationPageProductImg.get())
						&& SelectorUtil.isImgLoaded(CheckOutSelectors.confirmationPageProductImg.get());
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean checkOrderNumberAndEmailAndShippingAddress() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(CheckOutSelectors.orderNumber.get())
						&& SelectorUtil.isDisplayed(CheckOutSelectors.email.get())
						&& SelectorUtil.isDisplayed(CheckOutSelectors.shippingAddress.get());
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static int getCountOFProductForConfPage() throws Exception {
			try {
				getCurrentFunctionName(true);

				List<String> subStrArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.paypalConfermationPageAllProduct.get());
				List<WebElement> elements = SelectorUtil.getAllElements(subStrArr);
				getCurrentFunctionName(false);
				return elements.size();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isSubmitConfermationMessageDisplayed() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(CheckOutSelectors.paypalSubmitConfermationMessage.get());
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void closePayPalSubmitPopup() throws Exception {
			try {
				getCurrentFunctionName(true);
				if (!SelectorUtil.isNotDisplayed(CheckOutSelectors.paymentSubmitPopUpClose.get()))
					SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.paymentSubmitPopUpClose.get());
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void closePayPalSubmitRegestration() throws Exception {
			try {
				getCurrentFunctionName(true);
				closePayPalSubmitPopup();
				if (!SelectorUtil.isNotDisplayed(CheckOutSelectors.paymentPayPalSubmitRegistrationCloseBtn.get()))
					SelectorUtil.initializeSelectorsAndDoActions(
							CheckOutSelectors.paymentPayPalSubmitRegistrationCloseBtn.get());
				closePayPalSubmitPopup();

				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void clickPayPalOrderSubmit() throws Exception {
			try {
				getCurrentFunctionName(true);
				SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.paymentPagePayPalSubmitBtn.get());
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isOrderSummaryDisplayed() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(CartSelectors.addedItemsTotalPrice.get());
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isPaymentPageSelectedAndPayPalSelected() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(CheckOutSelectors.paymentPagePayPalTitle.get());
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isPayPalModelDisplayed() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(PayPalSelectors.userName);
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static boolean isPayPalShipToPageDisplayed() throws Exception {
			try {
				getCurrentFunctionName(true);
				boolean isDisplayed = SelectorUtil.isDisplayed(PayPalSelectors.continueBtn);
				getCurrentFunctionName(false);
				return isDisplayed;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
	}
	public static void clickCreditCardPayment() throws Exception {
		try {	
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CheckOutSelectors.creditCartTab.get());		
			getCurrentFunctionName(false);			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
		
}
