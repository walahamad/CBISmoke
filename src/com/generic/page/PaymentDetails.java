package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PaymentDetailsSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.LoggingMsg;
import com.generic.util.SelectorUtil;

public class PaymentDetails extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}
	
	public static void clickRemovePaymentDetailsBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_REMOVE_BUTTON, "remove address"));
		subStrArr.add(PaymentDetailsSelectors.removePaymentDetailsBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static String getFirstPaymentDetails() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.PaymentDetailsList);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.PAMENT_DETAILS, SelectorUtil.textValue.get()));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static void clickDeleteBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.deletePaymentDetails);
		valuesArr.add("");
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_DELETE_BUTTON, "confirm delete address"));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	//done
	public static void clickSavePayment() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.savePaymentBtn);
		valuesArr.add("");
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_DELETE_BUTTON, "confirm delete address"));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	//done
	public static void fillandClickSave(String cardtype, String cardNumber, String expireDay, String expireYear,
			String CVC) throws Exception {
		getCurrentFunctionName(true);
		CheckOut.paymentInnformation.fill(cardtype, cardNumber, expireDay, expireYear, CVC);
		clickSavePayment();
		getCurrentFunctionName(false);
	}
	
	//done
	public static void clickOnAddBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.addPaymentBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		Thread.sleep(1500);
		getCurrentFunctionName(false);
	}
}
