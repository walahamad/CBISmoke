package com.generic.tests.FG.e2e;

import java.net.URI;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.FG.PDP.PDPValidation;
import com.generic.tests.FG.PDP.WistListGuestValidation;

public class PDP_e2e extends SelTestCase{
	
	public static final String singlePDPSearchTerm = "Rugs";
	public static final String BundlePDPSearchTerm = "Collection";
	
	
	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			PDPValidation.validate(singlePDPSearchTerm);
					
			URI url = new URI(getURL());
			getDriver().get("https://"+url.getHost());
			
			/*
			WistListGuestValidation.validate();
			
			url = new URI(getURL());
			getDriver().get("https://"+url.getHost());
			*/
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	
	

}
