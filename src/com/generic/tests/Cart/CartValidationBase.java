package com.generic.tests.Cart;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.page.Cart;
import com.generic.page.PDP;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class CartValidationBase extends SelTestCase {
	
	private static final LinkedHashMap<String, Object> invintory = Common.readLocalInventory();
	private static final LinkedHashMap<String, Object> users = Common.readUsers();
	
	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.cartCaseId ;
	}
	
	public void prepareCartNotLoggedInUser() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));
		try {
			String product = "P3";
			logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
					(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
					(String) productDetails.get(PDP.keys.qty));
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, e.getMessage()));
		}

	}
	
	public void prepareCartLoggedInUser() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));
		try {
			String user = "ibatta@dbi.com";
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, user));
			LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(user);
			logs.debug((String)userDetails.get("password"));
			logs.debug((String)userDetails.get("mail"));
			SignIn.logIn((String)userDetails.get("mail"), (String)userDetails.get("password"));
			
			String product = "P3";
			logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
					(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
					(String) productDetails.get(PDP.keys.qty));
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, e.getMessage()));
		}

	}

}
