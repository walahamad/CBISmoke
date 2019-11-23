package com.generic.tests.FG.PDP;

import com.generic.page.HomePage;
import com.generic.page.PDP;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);

		HomePage.NavigateToPDP();
		
		
	}

}
