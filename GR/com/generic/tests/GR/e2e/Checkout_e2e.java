package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.GR.checkout.GuestCheckoutSingleAddress;
import com.generic.tests.GR.checkout.RegisteredCheckoutSingleAddress;

public class Checkout_e2e extends SelTestCase {

	public static void Validate(int productsCount, LinkedHashMap<String, String> addressDetails,
			LinkedHashMap<String, String> paymentDetails,  LinkedHashMap<String, String> userdetails) throws Exception {
		getCurrentFunctionName(true);

		try {

			GuestCheckoutSingleAddress.startTest(productsCount, addressDetails, paymentDetails);
			RegisteredCheckoutSingleAddress.startTest(productsCount, addressDetails, paymentDetails, userdetails);

			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
