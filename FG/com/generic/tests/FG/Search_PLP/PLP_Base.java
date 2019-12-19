package com.generic.tests.FG.Search_PLP;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.PLP;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class PLP_Base extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.plpSheet;
	
	public static final String plpTest = "Full PLP";

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private static LinkedHashMap<String, Object> users;

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		users = Common.readUsers();
	}
	
	@DataProvider(name = "PLP", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "PLP")
	public void verifyPLP(String caseId, String runTest, String Proprties, String desc ) throws Exception {
		
		Testlogs.set(new SASLogger("PLP " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("PLP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));	

		try {
			
			if (Proprties.contains(plpTest)) {
				if (isMobile() || isiPad())
					PLP.navigateToRandomPLPMobileIpad();

				else
					PLP.navigateToRandomPLPDesktop();

				sassert().assertTrue(PLP.VerifyPLP(), "Serach validation failed");
			}
					
			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		}

	}

}
