package com.generic.page;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.generic.selector.GiftRegistrySelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;
import com.generic.util.SelectorUtil.commands;

public class GiftRegistry extends SelTestCase {

	public static String GRPageLink = "GiftRegistryHomeView";
	public static final String CartPageLink = "/OrderCalculate";
	public static String type;
	public static String eventDateMonth;
	public static String eventDateDay;
	public static String eventDateYear;
	public static String registryName;
	public static String emptyMessage;

	// Constants.
	public static String singlePDPSearchTerm = "Rugs";
	public static final String createGiftRegistryString = "Create New Registry";

	/**
	 * Set initial values for Gift registry.
	 * 
	 * @param registryType
	 * @param eventDMonth
	 * @param eventDDay
	 * @param eventDYear
	 * @param emptyMsg
	 *
	 * @throws Exception
	 */
	public static void setRegistryInformtion(String registryType, String eventDMonth, String eventDDay,
			String eventDYear, String emptyMsg, String searchTerm) {
		type = registryType;
		registryName = RandomUtilities.getRandomName();
		eventDateMonth = eventDMonth;
		eventDateDay = eventDDay;
		eventDateYear = eventDYear;
		emptyMessage = emptyMsg;
		singlePDPSearchTerm = searchTerm;
	}

	/**
	 * Set current registry name.
	 * 
	 * @param name
	 *
	 * @throws Exception
	 */
	public static void setRegistryName(String name) {
		logs.debug("Set registry value: " + name);
		registryName = name;
	}

	/**
	 * validate create gift registry.
	 * 
	 * @param email
	 *
	 * @throws Exception
	 */
	public static void validate(String email) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Validate gift registry.");
			Thread.sleep(1000);
			SelectorUtil.waitGWTLoadedEventPWA();
			navigateToGiftRegistry();
			Thread.sleep(1500);
			SelectorUtil.waitGWTLoadedEventPWA();
			String createRegistryButtonSelector = "";

			if (isFG()) {
				createRegistryButtonSelector = GiftRegistrySelectors.FGCreateRegistryButton.get();
			} else if (isGR()) {
				createRegistryButtonSelector = GiftRegistrySelectors.GRCreateRegistryButton.get();
			}

			SelectorUtil.initializeSelectorsAndDoActions(createRegistryButtonSelector);
			Thread.sleep(1500);
			SelectorUtil.waitGWTLoadedEventPWA();

			// Create new gift registry.
			createRegistrySteps(email);

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Create gift registry.
	 * 
	 * @param email
	 *
	 * @throws Exception
	 */
	public static void createRegistrySteps(String email) throws Exception {
		try {
			getCurrentFunctionName(true);

			logs.debug("Create new registry.");

			// Gift registry step one.
			WebElement stepOneContainer = SelectorUtil.getElement(GiftRegistrySelectors.stepOneContainer.get());
			sassert().assertTrue(stepOneContainer != null, "Create Gift registry - not able to navigate to Step 1");
			fillRegistryInformation();
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.confirmInformtionButton.get());
			SelectorUtil.waitGWTLoadedEventPWA();

			// Gift registry step two.
			WebElement stepTwoContainer = SelectorUtil.getElement(GiftRegistrySelectors.stepTwoContainer.get());
			sassert().assertTrue(stepTwoContainer != null, "Create Gift registry - not able to navigate to Step 2");

			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.createRegistryButtonStepTwo.get());

			Thread.sleep(1500);
			SelectorUtil.waitGWTLoadedEventPWA();

			// Check the confirmation modal.
			validateConfirmationModal();

			// Check the created gift registry.
			validateCreatedGiftRegistry();

			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.beginAddingItemsButton.get());

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Fill registry information (Step one).
	 *
	 * @throws Exception
	 */
	public static void fillRegistryInformation() throws Exception {
		try {
			getCurrentFunctionName(true);

			logs.debug("Fill Gift Registry information step 1.");

			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventType.get(), type);
			if (registryName.equals("")) {
				registryName = RandomUtilities.getRandomName();
			}
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.registryName.get(), registryName);

			if (isMobile()) {
				List<WebElement> eventDataList = SelectorUtil.getElementsList(GiftRegistrySelectors.eventDay.get());
				SelectorUtil.setSelectText(eventDataList.get(0), eventDateMonth);
				SelectorUtil.setSelectText(eventDataList.get(1), eventDateDay);
				SelectorUtil.setSelectText(eventDataList.get(2), eventDateYear);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventMonth.get(), eventDateMonth);
				SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventDay.get(), eventDateDay);
				SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventYear.get(), eventDateYear);
			}

			logs.debug("Gift Registry information step 1 {type: " + type + ", registryName:" + registryName
					+ ", event Date Month:" + eventDateMonth + ", event Date Day:" + eventDateDay + ", event Date Year:"
					+ eventDateYear + "}.");

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	/**
	 * Navigate to gift registry home page.
	 *
	 * @throws Exception
	 */
	public static void navigateToGiftRegistry() throws Exception {
		try {
			logs.debug("Navigate to Gift registry page.");
			getCurrentFunctionName(true);
			if (isMobile()) {
				WebElement giftRegistryLink = SelectorUtil.getMenuLinkMobilePWA(GRPageLink);
				// Navigate to the Sign in/Create account page.
				SelectorUtil.clickOnWebElement(giftRegistryLink);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.GRLink.get());
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate create gift registry confirmation modal.
	 *
	 * @throws Exception
	 */
	public static void validateConfirmationModal() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.waitGWTLoadedEventPWA();
			Thread.sleep(1000);
			WebElement confirmationModal = SelectorUtil.getElement(GiftRegistrySelectors.confirmationModalGR.get());
			sassert().assertTrue(confirmationModal != null,
					"Error Confirmation gift registry created modal not displayed");
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.manageRegistryButton.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate created gift registry.
	 *
	 * @throws Exception
	 */
	public static void validateCreatedGiftRegistry() throws Exception {
		try {
			getCurrentFunctionName(true);

			// Validate that the page is manage gift registry.
			WebElement giftRegisrtContainer = SelectorUtil.getElement(GiftRegistrySelectors.manageGRContainer.get());
			sassert().assertTrue(giftRegisrtContainer != null, "Error this page is not manage gift registry");

			// Validate the selected registry.
			String selectedRegistry;
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.registryInfo.get());
			selectedRegistry = SelectorUtil.textValue.get();

			sassert().assertTrue(selectedRegistry.contains(registryName),
					"Error in selected registry, expected " + registryName + " : " + selectedRegistry);

			// Validate the empty registry.
			String emptyMsg = SelectorUtil.getElement(GiftRegistrySelectors.emptyRegistryMsg.get()).getText().trim()
					.toLowerCase();
			sassert().assertTrue(emptyMsg.equals(emptyMessage.toLowerCase().trim()),
					"Error empty messages is not as expected " + emptyMessage.toLowerCase().trim() + " : " + emptyMsg);

			// Validate gift card container.
			WebElement giftCardContainer = SelectorUtil.getElement(GiftRegistrySelectors.giftCardContainer.get());
			sassert().assertTrue(giftCardContainer != null,
					"Error gift card section not displayed at manage gift card page.");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate manage gift registry (Step 2).
	 * 
	 * @param email
	 *
	 * @throws Exception
	 */
	public static void manageRegistryValidate(String email) throws Exception {

		try {
			getCurrentFunctionName(true);

			// Go to PDP by search and select the swatches.
			goToPDPAndSelectSwatches();

			// Click on save to gift registry button.
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.saveToGR.get());
			Thread.sleep(1000);

			// Verify that "Select A Registry Or Wish list" modal is displayed.
			validateSelectGRModal();
			int numberOfItemAddedToCart = 0;
			if (isGR() && isMobile()
					&& !SelectorUtil.isElementExist(By.cssSelector(GiftRegistrySelectors.miniCartText.get()))) {
				numberOfItemAddedToCart = 0;
			} else {
				numberOfItemAddedToCart = Integer
						.parseInt(SelectorUtil.getElement(GiftRegistrySelectors.miniCartText.get()).getText());
			}

			logs.debug("Number of items before add to cart: " + numberOfItemAddedToCart);

			if (registryName.equals("") || registryName == null) {
				logs.debug("Create new gift registry.");
				setRegistryName(RandomUtilities.getRandomName());
				String GRListBoxSelector = "";

				if (isFG()) {
					GRListBoxSelector = GiftRegistrySelectors.FGListBoxGR.get();
				} else if (isGR()) {
					GRListBoxSelector = GiftRegistrySelectors.GRListBoxGR.get();
				}

				SelectorUtil.initializeSelectorsAndDoActions(GRListBoxSelector, "FFFS" + createGiftRegistryString);

				// No need to click on select button in PWA because the selected option will
				// submitted when change the select option value.
				if (!isMobile()) {
					String selectGRSelector = "";
					if (isFG()) {
						selectGRSelector = GiftRegistrySelectors.FGAddToGiftRegistySelectButton.get();
					}

					if (isGR()) {
						selectGRSelector = GiftRegistrySelectors.GRAddToGiftRegistySelectButton.get();
					}
					// Click select button at desktop and tablet.
					SelectorUtil.initializeSelectorsAndDoActions(selectGRSelector);
				}

				SelectorUtil.waitGWTLoadedEventPWA();
				// Create a gift registry.
				createRegistrySteps(email);

			} else {
				logs.debug("Add product to created gift registry: " + registryName);

				String GRListBoxSelector = "";
				if (isFG()) {
					GRListBoxSelector = GiftRegistrySelectors.FGListBoxGR.get();
				} else if (isGR()) {
					GRListBoxSelector = GiftRegistrySelectors.GRListBoxGR.get();
				}
				// Make sure that the current selected option is not the target registry.
				SelectorUtil.initializeSelectorsAndDoActions(GRListBoxSelector);
				String selectedGiftRegistry = SelectorUtil.textValue.get();

				if (!selectedGiftRegistry.contains(registryName)) {
					// Selected created registry.
					SelectorUtil.initializeSelectorsAndDoActions(GRListBoxSelector,
							MessageFormat.format(commands.actions.selectOption, "\"" + registryName + "\""));
				}

				String selectGRSelector = "";
				if (isFG()) {
					selectGRSelector = GiftRegistrySelectors.FGAddToGiftRegistySelectButton.get();

				} else if (isGR()) {
					selectGRSelector = GiftRegistrySelectors.GRAddToGiftRegistySelectButton.get();
				}

				// Click on save to gift registry button.
				SelectorUtil.initializeSelectorsAndDoActions(selectGRSelector);
			}

			Thread.sleep(1500);
			SelectorUtil.waitGWTLoadedEventPWA();

			// Validate product added to gift registry modal.
			validateAddToGRModal();

			// Click on view registry button.
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.viewRegistryButton.get());

			SelectorUtil.waitGWTLoadedEventPWA();
			Thread.sleep(1500);

			// Verify the gift registry contains products.
			validateAddProductGR();

			SelectorUtil.waitGWTLoadedEventPWA();

			// Click on add to cart.
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.addGRProductToCart.get());

			Thread.sleep(1500);
			SelectorUtil.waitGWTLoadedEventPWA();

			// Verify "add to cart" confirmation is displayed.
			validateAddToCartFromGRModal(numberOfItemAddedToCart);

			// Click "Checkout".
			if (isMobile()) {
				WebElement ShoppingCartLink = SelectorUtil.getMenuLinkMobilePWA(CartPageLink);
				// Navigate to the cart page.
				SelectorUtil.clickOnWebElement(ShoppingCartLink);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.checkoutFromGRModal.get());
			}

			Thread.sleep(500);
			SelectorUtil.waitGWTLoadedEventPWA();

			// Verify that the product is added from Gift registry.
			validateProductAddedFromGR(numberOfItemAddedToCart);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Go to PDP by search and select product swatches.
	 *
	 * @throws Exception
	 */
	public static void goToPDPAndSelectSwatches() throws Exception {
		try {
			getCurrentFunctionName(true);

			logs.debug("Navigate to PDP by search on:" + singlePDPSearchTerm);
			PDP.NavigateToPDP(singlePDPSearchTerm);

			logs.debug("Select product swatched.");
			PDP.selectSwatches();

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate add product to gift registry from PDP.
	 *
	 * @throws Exception
	 */
	public static void validateAddToGRModal() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Validate add product to gift registry modal at PDP.");
			WebElement addToGRModal = SelectorUtil.getElement(GiftRegistrySelectors.addToGiftRegistyModal.get());
			sassert().assertTrue(addToGRModal != null, "Error: Product added to gift registry modal displayed.");

			logs.debug("Validate product container in added to gift registry modal at PDP.");
			WebElement productAddedToGRContainer = SelectorUtil
					.getElement(GiftRegistrySelectors.productAddedToGRContainer.get());
			sassert().assertTrue(productAddedToGRContainer != null,
					"Error: Product container contain in added to gift registry modal.");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate the item added to gift registry from PDP.
	 *
	 * @throws Exception
	 */
	public static void validateAddProductGR() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Validate added item in gift registry.");
			WebElement productAddedToGRContainer = SelectorUtil.getElement(GiftRegistrySelectors.productListGR.get());
			sassert().assertTrue(productAddedToGRContainer != null, "Error: Items in gift registry.");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate add product to cart from gift registry.
	 * 
	 * @param oldNumberOfItemAddedToCart
	 *
	 * @throws Exception
	 */
	public static void validateAddToCartFromGRModal(int oldNumberOfItemAddedToCart) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Validate add to cart from gift registry.");
			if (isFG() && isMobile()) {
				Thread.sleep(500);
				// No add to cart modal displayed at mobile.
				int numberOfItemAddedToCart = Integer
						.parseInt(SelectorUtil.getElement(GiftRegistrySelectors.miniCartText.get()).getText());
				int tries = 0;
				while (numberOfItemAddedToCart == oldNumberOfItemAddedToCart && tries < 20) {
					Thread.sleep(1000);
					numberOfItemAddedToCart = Integer
							.parseInt(SelectorUtil.getElement(GiftRegistrySelectors.miniCartText.get()).getText());
					tries++;
				}
				logs.debug("Number of items after add to cart in mini cart: " + numberOfItemAddedToCart);
				sassert().assertTrue(oldNumberOfItemAddedToCart < numberOfItemAddedToCart,
						"Error: Product added corectlly to cart from Gift registry");
			} else {

				String addToCartModalSelector = GiftRegistrySelectors.addCartFromGRModal.get();
				if (isGR() && isMobile()) {
					addToCartModalSelector = GiftRegistrySelectors.movableMiniCart.get();
				} else {
					SelectorUtil.waitElementLoading(By.cssSelector(addToCartModalSelector));
				}
				WebElement addToGRModal = SelectorUtil.getElement(addToCartModalSelector);
				sassert().assertTrue(addToGRModal != null,
						"Error: Product added to cart from gift registry modal displayed.");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate Select gift registry/wishlist modal.
	 *
	 * @throws Exception
	 */
	public static void validateSelectGRModal() throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Validate select registry/wishlist modal.");
			WebElement selectGRModal = SelectorUtil.getElement(GiftRegistrySelectors.selectGRModal.get());
			sassert().assertTrue(selectGRModal != null, "Error: Select gift registry modal displayed.");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Validate cart product and a new items added to cart from gift registry.
	 * 
	 * @param oldNumberOfItemAddedToCart
	 *
	 * @throws Exception
	 */
	public static void validateProductAddedFromGR(int oldNumberOfItemAddedToCart) throws Exception {
		try {
			getCurrentFunctionName(true);
			if (isMobile()) {
				if (!SelectorUtil.isElementExist(By.cssSelector(GiftRegistrySelectors.itemInCart.get()))) {
					boolean gwtValue = SelectorUtil.CheckGWTLoadedEventPWA();
					int tries = 0;
					while (gwtValue) {
						Thread.sleep(1000);
						gwtValue = SelectorUtil.CheckGWTLoadedEventPWA();
						if (tries == 50) {
							throw new NoSuchElementException("Error in Loading GWT.");
						}
						logs.debug("Waiting GWT");
						tries++;
					}
				}
			}
			logs.debug("Validate product added to cart from gift registry.");
			int numberOfItemAddedToCart = SelectorUtil.getAllElements(GiftRegistrySelectors.cartProductContainer.get())
					.size();
			logs.debug("Number of items at cart: " + numberOfItemAddedToCart);
			sassert().assertTrue(oldNumberOfItemAddedToCart < numberOfItemAddedToCart,
					"Error: Product added corectlly to shopping cart from Gift registry");

			logs.debug("Validate product added to cart from gift registry label.");
			WebElement selectGRModal = SelectorUtil.getElement(GiftRegistrySelectors.addedFromGR.get());
			sassert().assertTrue(selectGRModal != null, "Error: Item added from Gift registry label.");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void accessValidAccount(String userMail, String userPassword) throws Exception {
		try {
			getCurrentFunctionName(true);

			// Run the registration test case before sign in.
			Registration.registerFreshUser(userMail, userPassword);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

}
