package com.generic.tests.ordersHistory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.page.OrderHistory;
import com.generic.page.PaymentDetails;

public class OrderHistoryValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.orderHistorySheet;
	private int caseIndexInDatasheet;

	private String caseId;
	private String runTest;
	private String desc;
	private String userName;
	private String password;
	private String url;
	private String sortByText;
	private String ordersNumber;
	private String ordersStatus;
	private String ordersDate;
	private String ordersTotal;
	private String ordersNthChildren;
	private String orderNumToBeClicked;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}

	@DataProvider(name = "ordersHistory", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		if (testObject.getParameter("browserName").equals("firefox"))
			Thread.sleep(500);
		if (testObject.getParameter("browserName").equals("chrome"))
			Thread.sleep(700);

		Object[][] data = TestUtilities.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "ordersHistory")
	public void verifyOrderHistory(String caseId, String runTest, String desc, String userName, String password,
			String url, String sortByText, String ordersNumber, String ordersStatus, String ordersDate,
			String ordersTotal, String ordersNthChildren, String orderNumToBeClicked) throws Exception {

		Testlogs.set(new SASLogger("ordersHistory" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("ordersHistory Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		// this.email = email.replace("tester",
		// "tester_"+getBrowserName().replace(" ", "_"));
		caseIndexInDatasheet = getDatatable().getCellRowNum(testDataSheet, PaymentDetails.keys.caseId, caseId);

		try {

			SignIn.logIn(userName, password);
			Thread.sleep(2000);
			getDriver().get(url);
			Thread.sleep(3000);
			if (!sortByText.isEmpty()) {
				OrderHistory.selectSortOptions1ByValue(sortByText);
				String sortOptionsEqualityWithFirstMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						OrderHistory.getSortOptions2SelectedValue(), OrderHistory.getSortOptions1SelectedValue());
				Assert.assertEquals(OrderHistory.getSortOptions1SelectedValue(),
						OrderHistory.getSortOptions2SelectedValue(),sortOptionsEqualityWithFirstMsg);

				OrderHistory.selectSortOptions2ByValue(sortByText);
				String sortOptionsEqualityWithSecondMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR,
						OrderHistory.getSortOptions1SelectedValue(), OrderHistory.getSortOptions2SelectedValue());
				Assert.assertEquals(OrderHistory.getSortOptions2SelectedValue(),
						OrderHistory.getSortOptions1SelectedValue(),sortOptionsEqualityWithSecondMsg);
			}

			boolean isOrderNumEqShownOnes = OrderHistory.doesDisplayedOrdersNumTextMatchesOrdersDisplayed();
			sassert().assertEquals(isOrderNumEqShownOnes, "orders shown don't match the number displayed");
			int totalNumOfOrders = Integer.parseInt(OrderHistory.getNumberOfOrders());
			// Values to compare with
			String[] ordersNumberArr = ordersNumber.split(";");
			String[] ordersStatusArr = ordersStatus.split(";");
			String[] ordersDateArr = ordersDate.split(";");
			String[] ordersTotalArr = ordersDate.split(";");
			// nthChildren
			String[] ordersNthChildrenArr = ordersNthChildren.split(";");

			for (int i = 0; i < totalNumOfOrders; i++) {
				// TODO: assertion to verify values plus verify when no orders
				// exist
				for (int j = 0; j < ordersNthChildrenArr.length; j++) {
					OrderHistory.getNthResponsiveTableItemColumn(ordersNthChildrenArr[j], i);

				}

			}
			OrderHistory.clickNthResponsiveTableItemTableCellAnchor(ordersNthChildrenArr[0],
					Integer.parseInt(orderNumToBeClicked));

			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		}

	}

}
