package com.generic.tests.ordersHistory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.generic.page.OrderHistory;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;

@RunWith(Parameterized.class)
public class OrderHistoryValidation extends SelTestCase {

	//used sheet in test
		public static final String testDataSheet = SheetVariables.orderHistorySheet;
		
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
		
		@BeforeClass
		public static void initialSetUp() throws Exception {
			testCaseRepotId = SheetVariables.orderHistoryTestCaseId;
		}
		
		
		public OrderHistoryValidation(String caseId, String runTest, String desc, String userName, String password,
				String url, String sortByText, String ordersNumber, String ordersStatus, String ordersDate, String ordersTotal, String ordersNthChildren, String orderNumToBeClicked) {
			this.caseId = caseId;
			this.runTest = runTest;
			this.desc = desc;
			this.userName = userName;
			this.password = password;
			this.url = url;
			this.sortByText = sortByText;
			this.ordersNumber = ordersNumber;
			this.ordersStatus = ordersStatus;
			this.ordersDate = ordersDate;
			this.ordersTotal = ordersTotal;
			this.ordersNthChildren = ordersNthChildren;
			this.orderNumToBeClicked = orderNumToBeClicked;
		}
		
		@Parameters(name = "{index}_:{2}")
		public static Collection<Object[]> loadTestData() throws Exception {
			Object[][] data = TestUtilities.getData(testDataSheet);
			return Arrays.asList(data);
		}
		
		@Test
		public void verifyOrderHistory() throws Exception {
			setTestCaseDescription(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
					this.getClass().getCanonicalName(), desc));
			
			try {
				
				SignIn.logIn(userName, password);
				Thread.sleep(2000);
				getDriver().get(url);
				Thread.sleep(3000);
				if (!sortByText.isEmpty()) {
					OrderHistory.selectSortOptions1ByValue(sortByText);
					String sortOptionsEqualityWithFirstMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, OrderHistory.getSortOptions2SelectedValue(), OrderHistory.getSortOptions1SelectedValue());
					Assert.assertEquals(sortOptionsEqualityWithFirstMsg, OrderHistory.getSortOptions1SelectedValue(), OrderHistory.getSortOptions2SelectedValue());
					
					OrderHistory.selectSortOptions2ByValue(sortByText);
					String sortOptionsEqualityWithSecondMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, OrderHistory.getSortOptions1SelectedValue(), OrderHistory.getSortOptions2SelectedValue());
					Assert.assertEquals(sortOptionsEqualityWithSecondMsg, OrderHistory.getSortOptions2SelectedValue(), OrderHistory.getSortOptions1SelectedValue());
				}
				
				boolean isOrderNumEqShownOnes = OrderHistory.doesDisplayedOrdersNumTextMatchesOrdersDisplayed();
				Assert.assertTrue("orders shown don't match the number displayed", isOrderNumEqShownOnes);
				int totalNumOfOrders = Integer.parseInt(OrderHistory.getNumberOfOrders());
				// Values to compare with
				String[] ordersNumberArr = ordersNumber.split(";");
				String[] ordersStatusArr = ordersStatus.split(";");
				String[] ordersDateArr = ordersDate.split(";");
				String[] ordersTotalArr = ordersDate.split(";");
				// nthChildren
				String[] ordersNthChildrenArr = ordersNthChildren.split(";");
				
				for (int i = 0; i< totalNumOfOrders; i++) {
					//TODO: assertion to verify values plus verify when no orders exist
					for (int j=0; j< ordersNthChildrenArr.length; j++) {
						OrderHistory.getNthResponsiveTableItemColumn(ordersNthChildrenArr[j],i);
						
					}
					
				}
				OrderHistory.clickNthResponsiveTableItemTableCellAnchor(ordersNthChildrenArr[0],Integer.parseInt(orderNumToBeClicked));
				
				Common.testPass();
			} catch (Throwable t) {
				setTestCaseDescription(getTestCaseDescription());
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
				t.printStackTrace();
				String temp = getTestCaseReportName();
				Common.testFail(t, temp);
				Common.takeScreenShot();
				Assert.assertTrue(t.getMessage(), false);
			}

		}

	}
