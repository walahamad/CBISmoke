package com.generic.setup;

import java.text.MessageFormat;
import java.util.ArrayList;

import com.generic.setup.SelTestCase;

public class GlobalVariables extends SelTestCase {

	public class browsers {
		public static final String firefox = "Firefox";
		public static final String chrome = "Chrome";
		public static final String Nexus = "mobile_Nexus 5";
		public static final String iPhone = "mobile_iPhone X";
		public static final String iPad = "mobile_iPad";
		public static final String IE = "IE";
		public static final String edge = "edge";
		public static final String iOS = "IOS_grid";
	}

	// Iframe ID for CVV Filed in chekout
	public static final String CVV_Iframe_ID = "cvv_Tokenizer";

	// Indexes for tax value
	public static final int GR_TAX_CART = 1;
	public static final int GR_TAX_CONFIRMATION = 0;

	public static final int FG_TAX_CART = 0;
	public static final int FG_TAX_CONFIRMATION = 0;

	/*
	 * public String firstName; public String lastName; public String emailAddress;
	 * public String confEmailAddress; public String zipCode; public String country;
	 * public String password; public String confPassword;
	 */

	public ArrayList<String> list;

	public static boolean flag = false;

	public GlobalVariables(String sheetName, int row) {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.READING_DATA_FROM_SHEET, sheetName, row));
		SelTestCase.getCurrentFunctionName(false);

	}

}
