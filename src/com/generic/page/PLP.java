package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.WebElement;

import com.generic.selector.HomePageSelectors;
import com.generic.selector.PLPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	// CBI
	public static boolean searchAndVerifyResults(String SearchTerm, boolean recommendedOption) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad))
				disableMonetate();
			clickSearchicon();
			typeSearch(SearchTerm);
			if (recommendedOption) {
				String productName = pickRecommendedOption();
				result = verifyPickedProduct(productName);
			} else {
				clickSearch(SearchTerm);
				result = verifySearchResultPage();
			}
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	private static boolean verifySearchResultPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(3000);

			getDriver().navigate().refresh();

			boolean result = true;
			result = result && verifyProductImagesDisplayed();

			sortByPriceLowToHigh();
			List<String> L2HproductsNames = getfirst3ProductsNames();

			Thread.sleep(3000);

			sortByPriceHighToLow();
			List<String> H2LsortedProductsNames = getfirst3ProductsNames();

			result = result && compareOperationResults(L2HproductsNames, H2LsortedProductsNames);

			Thread.sleep(3000);

			String firstProductName = getfirst3ProductsNames().get(0);
			SelectFilter();
			Thread.sleep(3000);
			String secondProductName = getfirst3ProductsNames().get(0);
			result = result && (firstProductName != secondProductName);

			logs.debug("Filters check result " + result);

			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean compareFilterResults(int before, int after) throws Exception {
		try {
			getCurrentFunctionName(true);

			boolean result = false;

			if (before != after)
				result = true;
			else
				result = false;

			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static boolean VerifyPLP() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result = true;
			Thread.sleep(3000);

			result = result && verifyProductImagesDisplayed();

			logs.debug("Image cehck result " + result);

			sortByPriceLowToHighPLP();
			List<String> L2HproductsNames = getfirst3ProductsNames();

			Thread.sleep(2500);

			sortByPriceHighToLowPLP();
			List<String> H2LsortedProductsNames = getfirst3ProductsNames();

			result = result && compareOperationResults(L2HproductsNames, H2LsortedProductsNames);

			logs.debug("Sorting result " + result);

			int productsCountBeforeFilter = getProductsCountinPLP();

			if (checkFiltersAvillability()) {
				SelectFilter();
				Thread.sleep(2000);

				result = result && compareFilterResults(productsCountBeforeFilter, getProductsCountinPLP());

				logs.debug("Filters check result " + productsCountBeforeFilter + getProductsCountinPLP() + result);

			}
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void clickOnFilterBy() throws Exception {
		try {
			getCurrentFunctionName(true);
			if (isFG())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.FilterContainer.get());
			if (isGR()) {

				if (!isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainer.get());
				} else
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainer.get());

			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void selectFilterName() throws Exception {
		try {
			getCurrentFunctionName(true);
			if (isFG())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.FilterContainerContents.get(),
						"ForceAction,click");
			if (isGR()) {
				if (!isMobile())
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainerContents.get(),
							"ForceAction,click");
				else
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainerContents.get(),
							"ForceAction,click");

			}

			if (isMobile()) {
				if (isFG()) {
					try {
						SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.allCatigories.get(),
								"ForceAction,click");
					} catch (Exception e) {

						try {

							SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.allCatigories2.get(),
									"ForceAction,click");

						} catch (Exception e2) {
							List<WebElement> maxPriceField = SelectorUtil
									.getElementsList(PLPSelectors.filterPrice.get());
							maxPriceField.get(0).clear();
							maxPriceField.get(0).sendKeys("250");

						}

					}
				}
				if (isGR())
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRallCatigories.get(),
							"ForceAction,click");

			}

			Thread.sleep(3000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	private static void SelectFilter() throws Exception {
		try {
			getCurrentFunctionName(true);
			clickOnFilterBy();
			selectFilterName();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static boolean checkFiltersAvillability() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean state = false;

			if (isGR()) {
				try {
					state = SelectorUtil.isDisplayed(PLPSelectors.GRFilterContainer.get());
				} catch (Exception e) {
					return false;
				}

			} else if (isFG()) {
				try {
					state = SelectorUtil.isDisplayed(PLPSelectors.FilterContainer.get());
				} catch (Exception e) {
					return false;
				}
			}

			getCurrentFunctionName(false);
			return state;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	@SuppressWarnings("unused")
	private static int getNumberOfProducts() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> namesWebElements = SelectorUtil.getAllElements(PLPSelectors.productsNames.get());
			int productNumbers = namesWebElements.size();
			getCurrentFunctionName(false);
			return productNumbers;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static boolean compareOperationResults(List<String> productsNames, List<String> sortedProductsNames) {
		boolean result = false;
		try {
			getCurrentFunctionName(true);
			result = !productsNames.equals(sortedProductsNames);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		return result;
	}

	// CBI
	private static void sortByPriceLowToHigh() throws Exception {

		try {
			getCurrentFunctionName(true);

			if (isFG()) {
				if (isMobile()) {
					clickOnSortMenu();
				}
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.PriceLowToHigh.get(), "forceAction,click");
			}
			if (isGR()) {
				if (isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRMobileSorting.get(), "FFF2");
				} else {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSorting.get());
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSortingLowtoHIgh.get());

				}

			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void sortByPriceHighToLow() throws Exception {

		try {
			getCurrentFunctionName(true);

			if (isFG()) {
				if (isMobile()) {
					clickOnSortMenu();
				}
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.PriceHighToLow.get());
			}
			if (isGR()) {
				if (isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRMobileSorting.get(), "FFF3");
				} else {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSorting.get());
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSortingHighToLow.get());

				}

			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void sortByPriceLowToHighPLP() throws Exception {

		try {
			getCurrentFunctionName(true);

			if (isFG()) {
				if (isMobile()) {
					clickOnSortMenu();
				}
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.PriceLowToHighPLP.get());
			}
			if (isGR()) {
				if (isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRMobileSorting.get(), "FFF2");
				} else {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSorting.get());
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSortingLowtoHIgh.get());

				}

			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void sortByPriceHighToLowPLP() throws Exception {

		try {
			getCurrentFunctionName(true);

			if (isFG()) {
				if (isMobile()) {
					clickOnSortMenu();
				}
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.PriceHighToLowPLP.get());
			}
			if (isGR()) {
				if (isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRMobileSorting.get(), "FFF3");
				} else {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSorting.get());
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRDeskTopSortingHighToLow.get());

				}

			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void clickOnSortMenu() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.mobileSortingMenu.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static List<String> getfirst3ProductsNames() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> productsName = new ArrayList<String>();
			List<WebElement> namesWebElements = SelectorUtil.getAllElements(PLPSelectors.productsNames.get());
			for (int index = 0; index < namesWebElements.size() && index < 3; index++) {
				WebElement prodname = namesWebElements.get(index);
				String prodnameStr = prodname.getText();
				productsName.add(prodnameStr);
			}
			getCurrentFunctionName(false);
			return productsName;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static int getProductsCountinPLP() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> namesWebElements = SelectorUtil.getAllElements(PLPSelectors.productsNames.get());
			getCurrentFunctionName(false);

			return namesWebElements.size();

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static boolean verifyProductImagesDisplayed() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			Thread.sleep(1000);

			if (isGR())
				result = SelectorUtil.isImgLoaded(PLPSelectors.productsImagesGR.get());
			else
				result = SelectorUtil.isImgLoaded(PLPSelectors.productsImages.get());

			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static boolean verifyPickedProduct(String productName) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			String productTitle = PDP.getTitle();
			result = productTitle.contains(productName);
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void clickSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get(), searchTerm + ",pressEnter");

			if (isMobile())
				Thread.sleep(5000);

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static String pickRecommendedOption() throws Exception {
		try {
			getCurrentFunctionName(true);

			String SelectorSS = PLPSelectors.recommendedOption.get();
			WebElement recommendedProduct = SelectorUtil.getElement(SelectorSS);

			String itemTitle = recommendedProduct.getText();
			logs.debug("Picked item: " + itemTitle);
			recommendedProduct.click();

			getCurrentFunctionName(false);
			return itemTitle;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void typeSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get(), searchTerm);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void clickSearchicon() throws Exception {
		try {
			getCurrentFunctionName(true);
			if (!isRY())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.SearchIcon.get());
			else
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.RYSearchIcon.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static String pickPLPFirstProduct() throws Exception {
		try {
			getCurrentFunctionName(true);
			String SelectorSS;
			if (isGHRY())
				SelectorSS = PLPSelectors.GHproductsImages.get();
			else if (isGR())
				SelectorSS = PLPSelectors.productsImagesGR.get();
			else
				SelectorSS = PLPSelectors.productsImages.get();
			String itemTitle = SelectorUtil.getAttrString(SelectorSS, "alt");
			SelectorUtil.initializeSelectorsAndDoActions(SelectorSS);
			getCurrentFunctionName(false);
			return itemTitle;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void navigateToRandomPLPMobileIpad() throws Exception {
		try {
			getCurrentFunctionName(true);

			// Open menu
			HomePage.openNavigationMenu();

			List<WebElement> menueItems = new ArrayList<WebElement>();
			menueItems = CLP.menueWithoutWhatsNew();
			WebElement randomMenuElement = SelectorUtil.getRandomWebElement(menueItems);
			// Click on random menu element
			SelectorUtil.clickOnWebElement(randomMenuElement);

			List<WebElement> leafMenuItems = SelectorUtil.getAllElements(HomePageSelectors.leafMenuItems.get());

			// Select a random item from the leaf items list.
			Random rand = new Random();
			WebElement randomElement = leafMenuItems.get(rand.nextInt(leafMenuItems.size()));

			// Navigate to the selected random page.
			SelectorUtil.clickOnWebElement(randomElement);

			// Check if the target is CLP
			if (isCLP()) {
				// Navigate to a PLP
				Thread.sleep(2000);

				try {
					if (isFG())
						SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLP.get());
					else if (isGR())
						SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLPGR.get());

				} catch (Exception e) {

					if (isFG())
						SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLP2.get());
					else if (isGR())
						SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLP2GR.get());
				}
			}

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void navigateToRandomPLPDesktop() throws Exception {
		try {
			getCurrentFunctionName(true);
			// Get the menu items list in first level.
			List<WebElement> menuFirstLevelElements = HomePage.getFirstLevelMenuItems();
			Random randomGenerator = new Random();
			WebElement randomElement = menuFirstLevelElements
					.get(randomGenerator.nextInt(menuFirstLevelElements.size() - 1));
			SelectorUtil.clickOnWebElement(randomElement);

			if (isCLP()) {
				// Navigate to a PLP
				try {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLP.get());
				} catch (Exception e) {
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.navigatetoPLP2.get());

				}
			}

			Thread.sleep(15000);

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI

	public static boolean isCLP() throws Exception {
		getCurrentFunctionName(true);
		try {
			Thread.sleep(2500);
			return !SelectorUtil.isDisplayed(PLPSelectors.PLPIdentifier.get());
		} catch (Exception e) {
			getCurrentFunctionName(false);

			return true;
		}
	}

}
