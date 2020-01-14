package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.page.Registration;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;

public class Registeration_e2e extends SelTestCase {

	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			String email = RandomUtilities.getRandomName() + "@testing.com";
			String password = "P@ssword1";

			Registration.registerFreshUser(email, password, "Firstvisa", "Lastvisa");

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
