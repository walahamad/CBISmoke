package com.generic.tests.Search;

import java.text.MessageFormat;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.page.PLP;
import com.generic.page.SearchResults;

public class SearchResultValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.SearchSheet;
	// int ExpectedNumberOfMenuItems = Integer.parseInt(SelTestCase.getCONFIG().getProperty("ExpectedNumberOfSearchMenuItems"));
	int ExpectedNumberOfMenuItems = 4;
	
	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}
	
	@DataProvider(name = "Search", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "Search")
	public void verifySearchResultsPage(String caseId, String runTest, String desc, String searchValues, String sortOptions1,
			String sortOptions2, String userLocationStore, String addToCartProduct, String pickUpInStoreProduct, String pickupNthIconIndex, String pickupInStoreQty,String plpFilter, String nthProductItem,
			String nthAppliedFacet, String SdoClickAddToCart, String SdoClickPickupInStore, String SdoClickNthProductItem, String SdoClickCheckoutBtn, String SdoClickCloseBtn) throws Exception {
		
		boolean doClickAddToCart = Boolean.valueOf(SdoClickAddToCart);
		boolean doClickPickupInStore = Boolean.valueOf(SdoClickPickupInStore);
		boolean doClickNthProductItem = Boolean.valueOf(SdoClickNthProductItem);
		boolean doClickCheckoutBtn = Boolean.valueOf(SdoClickCheckoutBtn);
		boolean doClickCloseBtn = Boolean.valueOf(SdoClickCloseBtn);
		
		Testlogs.set(new SASLogger("Search" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("Search Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		try {

			if (!desc.contains("No Results")) {
				SearchResults.typeSearchText(searchValues);
				Thread.sleep(1000);
				// verify ò Search Menu Items
				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfMenuItems());
				Thread.sleep(1000);
				sassert().assertTrue(numberOfMenuItems == ExpectedNumberOfMenuItems);
				// Type again because getNumberOfMenuItems will click on the first element.
				 SearchResults.typeSearchText(searchValues);
				Thread.sleep(1000);

				for (int index = 0; index < numberOfMenuItems; index++) {
					// Verify image, name and price for each item.
					for (int c = 0; c < 3; c++) {
						SearchResults.getNthResponsiveListItemColumn(c, index);
						 SearchResults.getNthResponsiveListItemImg(index);
						sassert().assertTrue(SearchResults.checkNthResponsiveListItemImg(index),"<font color=#f442cb>NOT All product images are ok</font>");

					}
				}

				SearchResults.typeSearchTextAndPressEnter(searchValues);
				Thread.sleep(1000);
				String ExpectedText = MessageFormat.format(LoggingMsg.SearchResulstHeader_Search, '"', searchValues,
						'"');
				String ActualText = SearchResults.getSearchResultsHeader();
				String ErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, ActualText, ExpectedText);
				sassert().assertTrue(ActualText.contains(ExpectedText), ErrorMsg);

				PLP.selectSortOptions1ByValue(sortOptions1);
				Thread.sleep(3000);
				
				PLP.selectSortOptions2ByValue(sortOptions2);
				Thread.sleep(3000);
				
//				String productsNum = PLP.getNumberOfproducts();
//				logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, productsNum));
				
				sassert().assertTrue(PLP.doesDisplayedProductsNumTextMatchesProductsDisplayed());
			
				PLP.typeUserLocationStore(userLocationStore);
				Thread.sleep(4000);
				
				PLP.clickMoreStores();
				Thread.sleep(3000);
				
			
				
			//	PLP.clickleftNavCheckBoxCheckBox("London Hospital");
				sassert().assertTrue(PLP.compareAppliedFilterWithDisplayedProductNumber(plpFilter));

				if (doClickAddToCart) {
					PLP.clickAddToCart(addToCartProduct);
					Thread.sleep(3000);
					String productPriceAddedToCart = PLP.getPLPProductPriceFromCartBag();
					logs.debug(MessageFormat.format(LoggingMsg.PLP_PRODUCT_PRICE, productPriceAddedToCart));
					String productPrice = PLP.getPLPProductPrice(addToCartProduct);
					logs.debug(MessageFormat.format(LoggingMsg.PLP_PRODUCT_PRICE, productPrice));
					Thread.sleep(2000);
					if (doClickCloseBtn) {
						PLP.clickCboxCloseBtn();
					} else if (doClickCheckoutBtn) {
						PLP.clickCheckoutBtn();
					} else {
						PLP.clickContinueShoppingBtn();
					}
				}

				if (doClickPickupInStore) {
					PLP.clickProductPickupInStoreButton(pickUpInStoreProduct);
					PLP.typePickUpInStoreLocationForSearch(userLocationStore);
					PLP.clickPickupNthAccessibleTabIcon(pickupNthIconIndex);
					PLP.clickPickUpInStoreDecreaseQtyBtn(pickUpInStoreProduct);
					PLP.clickPickUpInStoreIncreaseQtyBtn(pickUpInStoreProduct);
					PLP.typePickUpInStoreQty(pickUpInStoreProduct, pickupInStoreQty);
					PLP.clickPickUpInStoreAddToBagBtn(pickUpInStoreProduct);
					PLP.clickCboxCloseBtn();
					Thread.sleep(2000);
				}

				if (doClickNthProductItem) {
					PLP.clickNthProductItem(nthProductItem);
				}
			} else {

				SearchResults.typeSearchTextAndPressEnter(searchValues);
				Thread.sleep(1000);

				String ExpectedText = MessageFormat.format(LoggingMsg.SearchResulstHeader_Search_Empty, searchValues);
				String ActualText = SearchResults.getSearchResultsHeaderSearchEmpty();
				String ErrorMsg = MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, ActualText, ExpectedText);
				sassert().assertTrue(ActualText.contains(ExpectedText), ErrorMsg);

			}

			sassert().assertAll();
			Common.testPass();
		}catch (Throwable t) {
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