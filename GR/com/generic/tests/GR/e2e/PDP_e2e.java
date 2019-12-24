package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.GR.PDP.PDPValidation;
import com.generic.tests.GR.PDP.WistListGuestValidation;

public class PDP_e2e extends SelTestCase{
	
	public static final String singlePDPSearchTerm = "Rugs";
	public static final String BundlePDPSearchTerm = "Collection";
	
	
	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			PDPValidation.validate(singlePDPSearchTerm);
			
			PDPValidation.validate(BundlePDPSearchTerm);	
			
			WistListGuestValidation.validate();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	
	

}
