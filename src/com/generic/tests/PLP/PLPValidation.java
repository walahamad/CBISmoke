package com.generic.tests.PLP;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.generic.page.PLP;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;

@RunWith(Parameterized.class)
public class PLPValidation extends SelTestCase {

	// used sheet in test
	public static final String testDataSheet = SheetVariables.plpSheet;

	private String caseId;
	private String runTest;
	private String desc;
	private String url;
	private String sortOptions1;
	private String sortOptions2;
	private String userLocationStore;
	private String addToCartProduct;
	private String pickUpInStoreProduct;
	private String pickupNthIconIndex;
	private String pickupInStoreQty;
	private String plpFilter;
	private String nthProductItem;
	private String nthAppliedFacet;
	private boolean doClickAddToCart;
	private boolean doClickPickupInStore;
	private boolean doClickNthProductItem;
	private boolean doClickCheckoutBtn;
	private boolean doClickCloseBtn;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.plpTestCaseId;
	}

	public PLPValidation(String caseId, String runTest, String desc, String url, String sortOptions1,
			String sortOptions2, String userLocationStore, String addToCartProduct, String pickUpInStoreProduct, String pickupNthIconIndex, String pickupInStoreQty,String plpFilter, String nthProductItem,
			String nthAppliedFacet, String doClickAddToCart, String doClickPickupInStore, String doClickNthProductItem, String doClickCheckoutBtn, String doClickCloseBtn) {
		// moving variables from parameterize module to class variables
		this.caseId = caseId;
		this.runTest = runTest;
		this.desc = desc;
		this.url = url;
		this.sortOptions1 = sortOptions1;
		this.sortOptions2 = sortOptions2;
		this.userLocationStore = userLocationStore;
		this.addToCartProduct = addToCartProduct;
		this.pickUpInStoreProduct = pickUpInStoreProduct;
		this.pickupNthIconIndex = pickupNthIconIndex;
		this.pickupInStoreQty = pickupInStoreQty;
		this.plpFilter = plpFilter;
		this.nthProductItem = nthProductItem;
		this.nthAppliedFacet = nthAppliedFacet;
		this.doClickAddToCart = Boolean.valueOf(doClickAddToCart);
		this.doClickPickupInStore = Boolean.valueOf(doClickPickupInStore);
		this.doClickNthProductItem = Boolean.valueOf(doClickNthProductItem);
		this.doClickCheckoutBtn = Boolean.valueOf(doClickCheckoutBtn);
		this.doClickCloseBtn = Boolean.valueOf(doClickCloseBtn);
	}
	
	@Parameters(name = "{index}_:{2}")
	public static Collection<Object[]> loadTestData() throws Exception {
		Object[][] data = TestUtilities.getData(testDataSheet);
		return Arrays.asList(data);
	}
	
	@Test
	public void verifyPLP() throws Exception {
		setTestCaseDescription(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		try {
			getDriver().get(url);
			Thread.sleep(3000);
			
			PLP.selectSortOptions1ByValue(sortOptions1);
			Thread.sleep(3000);
			
			PLP.selectSortOptions2ByValue(sortOptions2);
			Thread.sleep(3000);
			
			String productsNum = PLP.getNumberOfproducts();
			logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, productsNum));
			
			PLP.doesDisplayedProductsNumTextMatchesProductsDisplayed();
			
			PLP.clickFindStores();
			Thread.sleep(3000);
			
			PLP.clickMoreStores();
			Thread.sleep(3000);
			
			PLP.typeUserLocationStore(userLocationStore);
			Thread.sleep(3000);
			
			PLP.clickleftNavCheckBoxCheckBox("London Hospital");
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "Store", PLP.getFacetNavTitleStoresCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "price", PLP.getFacetNavTitlePriceCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "colour", PLP.getFacetNavTitleColourCount()));
			//logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_FILTER_COUNT, "Size", PLP.getFacetNavTitleSizeCount()));
			PLP.compareAppliedFilterWithDisplayedProductNumber(plpFilter);
			
			PLP.removeNthAppliedFacet(nthAppliedFacet);
			
			PLP.clickChangeLocationLink();
			Thread.sleep(3000);
			
		    PLP.verifyChangeLocationLink();
		    
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
		    
			Thread.sleep(4000);
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
