package com.generic.page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PDPSelectors;
import com.generic.selector.cartSelectors;
import com.generic.selector.checkOutSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class checkOut extends SelTestCase {
    private static final String DOC_URL = getCONFIG().getProperty("testSiteName");
    static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();


public static class shippingAddress 
{

	public static void SelectCountery(String countery) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Selecting Countery "  + countery);
		subStrArr.add(checkOutSelectors.countery);
		valuesArr.add(countery);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectTitle(String title) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Selecting title "  + title);
		subStrArr.add(checkOutSelectors.title);
		valuesArr.add(title);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}

	public static void typeFirstName(String firstName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing firstname "  + firstName);
		subStrArr.add(checkOutSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeAddress(String address) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing address "  + address);
		subStrArr.add(checkOutSelectors.address);
		valuesArr.add(address);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeLastName(String lastName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing lastname "  + lastName);
		subStrArr.add(checkOutSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCity(String city) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing city "  + city);
		subStrArr.add(checkOutSelectors.city);
		valuesArr.add(city);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typePostalCode(String postal) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing postal "  + postal);
		subStrArr.add(checkOutSelectors.postal);
		valuesArr.add(postal);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
	public static void typePhone(String phone) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("typing phone "  + phone);
		subStrArr.add(checkOutSelectors.phone);
		valuesArr.add(phone);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void checkSaveAddress(boolean check) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Saving Address");
		subStrArr.add(checkOutSelectors.CheckSaveAddress);
		valuesArr.add(String.valueOf(check));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
	public static void clickNext() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("clicking next in shipping address form");
		subStrArr.add(checkOutSelectors.submitShippingAddressBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
	public static void fillAndClickNext(String Countery, String title, String firstName,
			String lastName, String address, String city, String postal, String phone , boolean saveAdderss) throws Exception {
		getCurrentFunctionName(true);
		
		if (!Countery.equals(""))
			SelectCountery(Countery);
		
		if (!title.equals(""))
			selectTitle(title);
		
		if (!firstName.equals(""))
			typeFirstName(firstName);
		
		if (!lastName.equals(""))
			typeLastName(lastName);
		
		if (!address.equals(""))
			typeAddress(address);
		
		if (!city.equals(""))
			typeCity(city);
		
		if (!postal.equals(""))
			typePostalCode(postal);
		
		if (!phone.equals(""))
			typePhone(phone);
		
		checkSaveAddress(saveAdderss);
		clickNext();
		
		getCurrentFunctionName(false);
	}
	
	//in case of using the address book
	public static void fillAndClickNext(boolean selectFromAddressBook) throws Exception
	{
		getCurrentFunctionName(true);
		clickOnAddressBook();
		selectFirstAddress();
		getCurrentFunctionName(false);
	}

	private static void selectFirstAddress() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.selectFirstAddress);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	private static void clickOnAddressBook() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.addressBookBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
}

public static class shippingMethod 
{

	public static void fillAndclickNext(String shippingMethod) throws Exception {
		getCurrentFunctionName(true);
		selectShippingMethod(shippingMethod);
		clickNext();
		getCurrentFunctionName(false);
		
	}

	private static void selectShippingMethod(String shippingMethod) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.shippingMethod);
		valuesArr.add(shippingMethod);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	private static void clickNext() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.submitShippingMethodbtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}
	
}

public static class paymentInnformation 
{

	public static void fillAndclickNext(String cardtype, String cardHolder, String cardNumber,
					String expireDay, String expireYear,
					String CVC, boolean savePayment , boolean billSameShip) throws Exception {
		getCurrentFunctionName(true);
		
		if (!cardtype.equals(""))
			selectCardType(cardtype);
		if (!cardHolder.equals(""))
			typeCardholder(cardHolder);
		if (!cardNumber.equals(""))
			typeCardNumber(cardNumber);
		if (!expireDay.equals(""))
			selectExpireDay(expireDay);
		if (!expireYear.equals(""))
			typeExpireYear(expireYear);
		if (!CVC.equals(""))
			typeCVC(CVC);
		
		savePaymentMethod(savePayment);
		
		checkBillingAddressSameshipping(billSameShip);
		
		clickNext();
		getCurrentFunctionName(false);
	}
	
	//in case of using wallet 
	public static void fillAndclickNext(boolean useAlreadySavedPayment) throws Exception {
		getCurrentFunctionName(true);
		clickOnUseedSavedCard();
		pickFirstpaymentsaved();
		getCurrentFunctionName(false);
	}
	
	private static void pickFirstpaymentsaved() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.selectFirstPayment);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	private static void clickOnUseedSavedCard() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.savedPaymentBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}

	public static void clickNext() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.submitPayment);
		valuesArr.add(String.valueOf(""));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}

	public static void checkBillingAddressSameshipping(boolean billSameShip) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.checkSame);
		valuesArr.add(String.valueOf(billSameShip));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
	private static void savePaymentMethod(boolean savePayment) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.checkSavePayment);
		valuesArr.add(String.valueOf(savePayment));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);		
	}

	
	public static void typeCVC(String CVC) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.CVC);
		valuesArr.add(CVC);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeExpireYear(String expireYear) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.expireYear);
		valuesArr.add(expireYear);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectExpireDay(String expireDay) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.expireDay);
		valuesArr.add(expireDay);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCardNumber(String cardNumber) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardNumber);
		valuesArr.add(cardNumber);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCardholder(String cardHolder) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardHolder);
		valuesArr.add(cardHolder);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectCardType(String cardtype) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardtype);
		valuesArr.add(cardtype);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
}

public static class reviewInformation
{

	public static String getSubtotal() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.summaryTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String shippingCost() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.shippingCost);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String gettotal() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static void acceptTerms(boolean acceptTerm) throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.acceptTerm);
		valuesArr.add(String.valueOf(acceptTerm));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void placeOrder() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.placeOrderBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
}

public static class orderConfirmation
{

	public static String getOrderid() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderId);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String getOrdertotal() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderConfirmationTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String getSubtotal() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderConfirmationSubtotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String getShippingcost() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderConfirmationShippingCost);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String getbillingAddrerss() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderconfirmationBillingAddress);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}
	
	public static String getshippingAddrerss() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderconfirmationshippingAddress);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}
	
}

    
}
