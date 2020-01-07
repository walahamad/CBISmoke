package com.generic.tests.GR.PDP;

import java.text.MessageFormat;
import java.util.Arrays;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.GiftRegistry;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.tests.GR.login.LoginBase;
import com.generic.util.RandomUtilities;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class GRBase extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.GRSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	public static final String singlePDPSearchTerm = "lights";

	// Test cases.
	public static final String createRegistry = "create registry";
	public static final String manageRegistry = "manage registry";

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		LoginBase.initialSetUp(test);
	}

	@DataProvider(name = "GR_SC", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "GR_SC")
	public void GRTest(String caseId, String runTest, String desc, String proprties, String registryType,
			String eventDateMonth, String eventDateDay, String eventDateYear, String emptyMessage) throws Exception {
		Testlogs.set(new SASLogger("GR_SC " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.GRCaseId);
		Testlogs.get().debug("Case Browser: " + testObject.getParameter("browserName"));
		String CaseDescription = MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc.replace("\n", "<br>--"));
		initReportTime();

		try {
			// Prepare registration data.
			String userMail = RandomUtilities.getRandomEmail();
			String userPassword = "P@ssword11";
			GiftRegistry.accessValidAccount(userMail, userPassword);

			GiftRegistry.setRegistryInformtion(registryType, eventDateMonth, eventDateDay, eventDateYear, emptyMessage,
					singlePDPSearchTerm);

			if (proprties.contains(createRegistry)) {
				GiftRegistry.validate(userMail);
			} else {
				// Clear the initial value for gift registry name.
				GiftRegistry.setRegistryName("");
			}

			if (proprties.contains(manageRegistry)) {
				// Add item to GR then add the item to cart from GR.
				GiftRegistry.manageRegistryValidate(userMail);
			}

			sassert().assertAll();

			Common.testPass(CaseDescription);
		} catch (Throwable t) {
			if ((getTestStatus() != null) && getTestStatus().equalsIgnoreCase("skip")) {
				throw new SkipException("Skipping this exception");
			} else {
				Common.testFail(t, CaseDescription, testDataSheet + "_" + caseId);
			}

		} // catch
	}// test
}
