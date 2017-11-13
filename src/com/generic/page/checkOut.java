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

	public static void SelectCountery(String countery) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("Selecting Countery "  + countery);
		subStrArr.add(checkOutSelectors.countery);
		valuesArr.add(countery);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectTitle(String title) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("Selecting title "  + title);
		subStrArr.add(checkOutSelectors.title);
		valuesArr.add(title);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}

	public static void typeFirstName(String firstName) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing firstname "  + firstName);
		subStrArr.add(checkOutSelectors.firstName);
		valuesArr.add(firstName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeAddress(String address) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing address "  + address);
		subStrArr.add(checkOutSelectors.address);
		valuesArr.add(address);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeLastName(String lastName) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing lastname "  + lastName);
		subStrArr.add(checkOutSelectors.lastName);
		valuesArr.add(lastName);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCity(String city) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing city "  + city);
		subStrArr.add(checkOutSelectors.city);
		valuesArr.add(city);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typePostalCode(String postal) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing postal "  + postal);
		subStrArr.add(checkOutSelectors.postal);
		valuesArr.add(postal);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
	public static void typePhone(String phone) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("typing phone "  + phone);
		subStrArr.add(checkOutSelectors.phone);
		valuesArr.add(phone);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void clickNext() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		logs.debug("clicking next in shipping address form");
		subStrArr.add(checkOutSelectors.submitShippingAddressBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void fillAndClickNext(String Countery, String title, String firstName,
			String lastName, String address, String city, String postal, String phone) throws InterruptedException, IOException {
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
		
		clickNext();
		
		getCurrentFunctionName(false);
	}
	
}

public static class shippingMethod 
{

	public static void fillAndclickNext(String shippingMethod) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		selectShippingMethod(shippingMethod);
		clickNext();
		getCurrentFunctionName(false);
		
	}

	private static void selectShippingMethod(String shippingMethod) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.shippingMethod);
		valuesArr.add(shippingMethod);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	private static void clickNext() throws InterruptedException, IOException {
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
					String CVC, boolean billSameShip) throws InterruptedException, IOException {
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
		
		checkBillingAddressSameshipping(billSameShip);
		
		clickNext();
		
	}

	public static void clickNext() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.submitPayment);
		valuesArr.add(String.valueOf(""));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
	}

	public static void checkBillingAddressSameshipping(boolean billSameShip) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.checkSame);
		valuesArr.add(String.valueOf(billSameShip));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCVC(String CVC) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.CVC);
		valuesArr.add(CVC);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeExpireYear(String expireYear) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.expireYear);
		valuesArr.add(expireYear);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectExpireDay(String expireDay) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.expireDay);
		valuesArr.add(expireDay);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCardNumber(String cardNumber) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardNumber);
		valuesArr.add(cardNumber);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void typeCardholder(String cardHolder) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardHolder);
		valuesArr.add(cardHolder);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void selectCardType(String cardtype) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.cardtype);
		valuesArr.add(cardtype);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
}

public static class reviewInformation
{

	public static String getSubtotal() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.summaryTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String shippingCost() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.shippingCost);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static String gettotal() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.orderTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug(SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
		
	}

	public static void acceptTerms(boolean acceptTerm) throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.acceptTerm);
		valuesArr.add(String.valueOf(acceptTerm));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}

	public static void placeOrder() throws InterruptedException, IOException {
		getCurrentFunctionName(true);
		subStrArr.add(checkOutSelectors.placeOrderBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		getCurrentFunctionName(false);
		
	}
	
}

    
}
