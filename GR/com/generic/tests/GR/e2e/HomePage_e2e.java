package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.GR.HomePage.GlobalFooterValidation;
import com.generic.tests.GR.HomePage.LogoValidation;

public class HomePage_e2e extends SelTestCase {

	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);

			LogoValidation.validate();
			GlobalFooterValidation.validate();

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
