package com.generic.tests.FG.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.FG.HomePage.GlobalFooterValidation;
import com.generic.tests.FG.HomePage.LogoValidation;

public class HomePage_e2e extends SelTestCase {


	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			
			LogoValidation.validate();
			sassert().assertTrue(GlobalFooterValidation.validate(), "Global footer validation has some problems");	
			
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
}
