package com.generic.tests.GH.PDP;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.generic.page.PDP;
import com.generic.selector.PDPSelectors;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;

public class PDPValidation extends SelTestCase {

	public static void validate(String searchTerm) throws Exception {
		getCurrentFunctionName(true);
		// To be removed after R1 build, this is to handle blank page in mobile for new session.
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone))
			Common.refreshBrowser();

		// Navigate to PDP page.
		PDP.NavigateToPDP(searchTerm);

		// Verify user is navigated to PDP page.
		validateIsPDPPage();
		SelectorUtil.waitGWTLoadedEventPWA();

		Boolean bundle = PDP.getNumberOfItems() > 1;
		String ProductID = null;

		// For bundle PDP mobile, validate the price is displayed in mini PDP page
		 if (isMobile() && bundle) {
		 	PDP.clickBundleItems();
		 	sassert().assertTrue(PDP.validateMobileBundlePriceIsDisplayed(),
		 			"Top price for the bundle item (mini PDP) is not dispayed");
		 }
		if (bundle)
			ProductID = PDP.getProductID(0);
		String priceErrorMessage;
		// price error message
		//for single PDP, validate the price is displayed below the title of the page for both desktop and mobile
		//for bundle PDP Desktop, validate the top price is displayed for the collection. (this is not displayed in mobile).
		//for bundle PDP mobile and desktop,validate the prices are displayed in bundle landing page for all items.
		if (!bundle) {
			priceErrorMessage = "Top price is not dispayed";
		} else if (!isMobile() && bundle) {
			priceErrorMessage = "Top price for the bundle items are not dispayed";
		} else {
			priceErrorMessage = "Price for the bundle items are not dispayed";
		}

		// The desktop and tablet didn't contains a top price.
		if (isMobile()) {
			sassert().assertTrue(PDP.validatePriceIsDisplayed(), priceErrorMessage);
		}
		// Select all required swatches.
		PDP.selectSwatches();

		String bottomPrice = PDP.getBottomPrice(bundle, ProductID);
		sassert().assertTrue(!bottomPrice.equals("$0.00"), "Bottom price is not updated correctly, Current price: " + bottomPrice);

		// Check if the personalization button exist.
		if (PDP.PersonalizedItem()) {

			// Click on "add Personalization" button
			PDP.clickAddPersonalizationButton();

			// Verify "Personalization" modal is opened correctly.
			sassert().assertTrue(PDP.validatePersonalizedModal(), "Personalization Modal is not dispayed");

			// Select all required swatches in personalized modal.
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				selectPersonalizationModalSwatchesForiPhone();
			} else {
				selectPersonalizationModalSwatches();
				PDP.clickPersonalizationSaveAndCloseButton();
			}
			sassert().assertTrue(PDP.validateAddedPersonalizedDetails(bundle, ProductID),
					"Added personalization details is not dispayed");

			// Verify prices are displayed.
			validatePriceAfterAddedPersonalized();
		}

		int initialNumberOfCartItems = PDP.getNumberOfCartItems();

		// Verify "Add to Registry / Wish list" is enabled.
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(), "Add to WL/GR button is not enabled");

		// Verify "Add to Cart" is enabled.
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(), "Add to Cart button is not enabled");

		int quantity = PDP.getQuantity();

		// Click "Add To Cart".
		PDP.clickAddToCartButton();

		// Verify "add to cart" confirmation is displayed.
		sassert().assertTrue(PDP.validateProductIsAddedToCart(), "Product is not added successfully");

		int numberOfCartItems = PDP.getNumberOfCartItems();
		// Verify the product added to the cart.
		sassert().assertTrue(numberOfCartItems == (quantity + initialNumberOfCartItems) , "There is an error in add item to cart or in mini cart items number.");

		getCurrentFunctionName(false);
	}

	public static void selectPersonalizationModalSwatches() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.personalizedItems.get());
		for (int i = 0; i < elementsList.size(); i++) {
			int index = i +1;
			String inputSelector = MessageFormat.format(PDPSelectors.GHPersonalizedInputValue.get(), index);
			String colorSelector = MessageFormat.format(PDPSelectors.GHPersonalizedItemColors.get(), index);
			String styleSelector = MessageFormat.format(PDPSelectors.personalizedItemStyle.get(), index);
			if (PDP.isPersonalizedInputSwatchesDisplayed(inputSelector)) {// input container like MONOGRAM or any value
				logs.debug("Input personalized item.");
				WebElement input = SelectorUtil.getElement(inputSelector);
				String perosnalizedString = RandomUtilities.getRandomName();
				perosnalizedString = perosnalizedString.substring(0, Math.min(perosnalizedString.length(), 3));
				input.sendKeys(perosnalizedString);
			} else if (PDP.isPersonalizedInputSwatchesDisplayed(colorSelector)) { // like item color
				logs.debug("Color personalized item.");
				List<WebElement> itemColors = SelectorUtil.getAllElements(colorSelector);
				selectRandomItem(itemColors);
			}else if (PDP.isPersonalizedInputSwatchesDisplayed(styleSelector)) { // like item style
				logs.debug("Style personalized item.");
				List<WebElement> itemStyle = SelectorUtil.getAllElements(styleSelector);
				selectRandomItem(itemStyle);
			}
		}
		getCurrentFunctionName(false);
	}

	/**
	* Check if the current page is PDP.
	*
	* @throws Exception
	*/
	public static void validateIsPDPPage() throws Exception {
		getCurrentFunctionName(true);
		WebElement PDPContainer = SelectorUtil.getElement(PDPSelectors.PDPPageClassName.get());
		sassert().assertTrue(PDPContainer != null, "The current page is PDP");
		getCurrentFunctionName(false);
	}

	public static void validatePriceAfterAddedPersonalized() throws Exception {
		getCurrentFunctionName(true);
		String finalPrice = PDP.getBottomPrice(); // take final price after added personalization
		String currency = getCONFIG().getProperty("currency");

		String perosnalizationPrice = "";

		if (isMobile()) {
			List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.personalizationPrice.get());
			for (int i = 0; i < elementsList.size(); i++) {
				WebElement item = elementsList.get(i);
				String itemText = item.getText();
				if (itemText.contains(currency)) {
					perosnalizationPrice = itemText;
				}
			}
		} else {
			perosnalizationPrice = SelectorUtil.getElement(PDPSelectors.personalizationPrice.get()).getText();
		}

		logs.debug("Final Price Value: " + finalPrice);
		logs.debug("Perosnalization Price: " + perosnalizationPrice);

		sassert().assertTrue(perosnalizationPrice != null,
				"Perosnalization price is not displayed.");

		sassert().assertTrue(finalPrice != null,
				"Total price is not displayed.");
		getCurrentFunctionName(false);

	}

	/**
	* Select a personalization options for mobile.
	* @param options.
	* @throws Exception
 	*/
	public static void selectPersonalizationModalSwatchesForiPhone() throws Exception {
		getCurrentFunctionName(true);

		// Get the list of personalization containers.
		List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.GHPersonalizedItems.get());

		// Loop on the personalizations containers.
		for (int i = 0; i < elementsList.size(); i++) {
			String	personalizedInputValueSelector = PDPSelectors.GHPersonalizedInputValue.get();
			String	personalizedItemColorsSelector = PDPSelectors.GHPersonalizedItemColors.get();
			// Get the current element.
			WebElement currentItem = elementsList.get(i);
			String currentItemClassName = currentItem.getAttribute("class");
			// Check if the current order accordion is opened.
			//The accordions should be opened by order after the next on click on next button.
			sassert().assertTrue(currentItemClassName.contains("pw-accordion--is-open"),
					"The target personalization swatch accordion is not opened.");

			if (PDP.isPersonalizedInputSwatchesDisplayed(personalizedInputValueSelector)) {
				// Input container like MONOGRAM or any value
				logs.debug("Input personalized item.");
				// Get a random text contains three characters.
				String perosnalizedString = RandomUtilities.getRandomName();
				perosnalizedString = perosnalizedString.substring(0, Math.min(perosnalizedString.length(), 3));
				SelectorUtil.initializeSelectorsAndDoActions(personalizedInputValueSelector, perosnalizedString);
			} else if (PDP.isPersonalizedInputSwatchesDisplayed(personalizedItemColorsSelector)) { // like item color
				// Select a random color or style option.
				logs.debug("Color personalized item.");
				List<WebElement> itemColors = SelectorUtil.getAllElements(personalizedItemColorsSelector);
				selectRandomItem(itemColors);
			} else if (PDP.isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemMenu.get())) {// like item size 
				// Need an example (Not test on GH).
				logs.debug("Style personalized item.");
				WebElement menu = SelectorUtil.getElement(PDPSelectors.personalizedItemMenu.get());
				List<WebElement> options =  menu.findElements(By.cssSelector(PDPSelectors.personalizedMenuOptions.get()));
				selectRandomItem(options);
			}
			SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.continuePersonalizationSelection.get());
		}
		getCurrentFunctionName(false);
	}

	/**
	* Select a random item from a list of items and click on it.
	* @param options.
	* @throws Exception
 	*/
	public static void selectRandomItem(List<WebElement> options) throws Exception {
		Random random = new Random();
		int randomIndex = random.nextInt(options.size());
		logs.debug("Swatch selected index: " + randomIndex);
		options.get(randomIndex).click();
	}
}
