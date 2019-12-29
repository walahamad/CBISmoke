package com.generic.tests.FG.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.page.PLP;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;

public class Search_PLP_e2e extends SelTestCase {

	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);

			sassert().assertTrue(PLP.searchAndVerifyResults("bath towels", false), "Serach validation failed");

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
