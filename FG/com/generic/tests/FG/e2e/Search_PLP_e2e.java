package com.generic.tests.FG.e2e;

import java.net.URI;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.page.PLP;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;

public class Search_PLP_e2e extends SelTestCase {


	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			
			
			sassert().assertTrue(PLP.searchAndVerifyResults("mat" , false), "Serach validation failed");

			URI url = new URI(getURL());
			getDriver().get("https://"+url.getHost());
			
			if (isMobile() || isiPad())
				PLP.navigateToRandomPLPMobileIpad();

			else
				PLP.navigateToRandomPLPDesktop();

			
			sassert().assertTrue(PLP.VerifyPLP(), "PLP validation failed");
			
			
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
}
