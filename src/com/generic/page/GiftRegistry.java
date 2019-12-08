package com.generic.page;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.generic.selector.GiftRegistrySelectors;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.ReportUtil;
import com.generic.util.SelectorUtil;

public class GiftRegistry extends SelTestCase {

	public static String GRPageLink = "GiftRegistryHomeView";
	public static String type;
	public static String eventDateMonth;
	public static String eventDateDay;
	public static String eventDateYear;
	public static String registryName;
	public static String emptyMessage;
	public static boolean validateContactInformation;
	public static boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);

	public static void setRegistryInformtion(String registryType, String eventDMonth, String eventDDay, String eventDYear, String emptyMsg) {
		isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
		type = registryType;
		registryName = generatRegistryName();
		eventDateMonth = eventDMonth;
		eventDateDay = eventDDay;
		eventDateYear = eventDYear;
		emptyMessage= emptyMsg;
	}

	public static void setValidateContactInformation(boolean validate) {
		validateContactInformation = validate;
	}

	public static boolean getValidateContactInformation() {
		return validateContactInformation;
	}

	public static void validate(String email, String createRegistryButtonSelector) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Validate gift registry.");
		navigateToGiftRegistry();
		Thread.sleep(1500);

		SelectorUtil.initializeSelectorsAndDoActions(createRegistryButtonSelector);
		SelectorUtil.waitGWTLoadedEventPWA();

		// Gift registry step one.
		WebElement stepOneContainer = SelectorUtil.getelement(GiftRegistrySelectors.stepOneContainer.get());
		sassert().assertTrue(stepOneContainer != null,
				"Error user not in step one gift registry.");
		fillRegistryInformation();
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.confirmInformtionButton.get());
		SelectorUtil.waitGWTLoadedEventPWA();

		// Gift registry step two.
		WebElement stepTwoContainer = SelectorUtil.getelement(GiftRegistrySelectors.stepTwoContainer.get());
		sassert().assertTrue(stepTwoContainer != null,
				"Error user not in step two gift registry.");
		boolean validateContactInformation = validateConfirmContactInformation(email);
		sassert().assertTrue(validateContactInformation,
				"Error in Gift registry step two contact information.");
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.createRegistryButtonStepTwo.get());

		Thread.sleep(1500);
		SelectorUtil.waitGWTLoadedEventPWA();

		// Check the confirmation modal.
		validateConfirmationModal();

		// Check the created gift registry.
		validateCreatedGiftRegistry();

		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.beginAddingItemsButton.get());

		getCurrentFunctionName(false);
	}

	public static void fillRegistryInformation() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Fill Gift Registry information step 1.");

		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventType.get(), type);
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.registryName.get(), registryName);

		if (isPWAMobile) {
			List<WebElement> eventDataList = SelectorUtil.getElementsList(GiftRegistrySelectors.eventDay.get());
			SelectorUtil.setSelectText(eventDataList.get(0), eventDateMonth);
			SelectorUtil.setSelectText(eventDataList.get(1), eventDateDay);
			SelectorUtil.setSelectText(eventDataList.get(2), eventDateYear);
		} else {
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventMonth.get(), eventDateMonth);
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventDay.get(), eventDateDay);
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.eventYear.get(), eventDateYear);
		}

		logs.debug("Gift Registry information step 1 {type: " + type + ", registryName:" + registryName + ", event Date Month:" + eventDateMonth + ", event Date Day:" + eventDateDay + ", event Date Year:" +  eventDateYear + "}.");

		// Thread.sleep(5000);
		getCurrentFunctionName(false);

	}

	public static void navigateToGiftRegistry() throws Exception {
		logs.debug("Navigate to Gift registry page.");
		getCurrentFunctionName(true);
		if (isPWAMobile) {
			WebElement giftRegistryLink = SelectorUtil.getMenuLinkMobilePWA(GRPageLink);
			// Navigate to the Sign in/Create account page.
			SelectorUtil.clickOnWebElement(giftRegistryLink);
		} else {
			SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.GRLink);
		}
		getCurrentFunctionName(false);
	}

	public static String generatRegistryName() {
		getCurrentFunctionName(true);
		int stringLength = 6;
	    StringBuilder sb = new StringBuilder(stringLength);
	    String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	    String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	    String NUMBER = "0123456789";

	    String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	    SecureRandom random = new SecureRandom();
	    for (int i = 0; i < stringLength; i++) {

			// 0-62 (exclusive), random returns 0-61
	        int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
	        char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

	        // debug
	        System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

	        sb.append(rndChar);

	    }
	    getCurrentFunctionName(false);
	    return sb.toString();
	}

	public static String getElementText(String selector) throws Exception {
		return SelectorUtil.getelement(selector).getAttribute("value");
	}

	@SuppressWarnings("unchecked")
	public static boolean validateConfirmContactInformation(String email) throws Exception {
		getCurrentFunctionName(true);

		LinkedHashMap<String, String> addressDetails = Registration.userAddress;
		setValidateContactInformation(false);
		// Select the addresses from the form.
		final String firstName = getElementText(GiftRegistrySelectors.firstNameGR.get());
		final String lastName = getElementText(GiftRegistrySelectors.lastNameGR.get());
		final String emailAddress = getElementText(GiftRegistrySelectors.emailAddressGR.get());
		final String streetAddress = getElementText(GiftRegistrySelectors.streetAddressGR.get());
		final String cityAddress = getElementText(GiftRegistrySelectors.cityAddressGR.get());
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.regionAddressGR.get());
		final String regionAddress = SelectorUtil.textValue.get();
		final String zipCode = getElementText(GiftRegistrySelectors.zipCodeGR.get());
		final String phone = getElementText(GiftRegistrySelectors.phoneGR.get()).replace("-", "");

		if (addressDetails == null) {
			addresses.forEach((key, address) -> {
				String firstNameValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.firstName);
				String lastNameValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.lastName);
				String streetAddressValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.adddressLine);
				String cityValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.city);
				String zipCodeValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.zipcode);
				String phoneValue = (String)((LinkedHashMap<String, Object>) address).get(AddressBook.shippingAddress.keys.phone);

				boolean validateFirstName = firstName.equals(firstNameValue);
				boolean validateLastName = lastName.equals(lastNameValue);
				boolean validateEmail= emailAddress.equals(email);
				boolean validateStreetAddress = streetAddress.equals(streetAddressValue);
				boolean validateCity= cityAddress.equals(cityValue);
				boolean validateRegion = regionAddress.equalsIgnoreCase(cityValue);
				boolean validateZipCode = zipCode.equals(zipCodeValue);
				boolean validatePhone = phone.equals(phoneValue);

				if (validateFirstName && validateLastName && validateEmail && validateStreetAddress && validateCity &&
						validateRegion && validateZipCode && validatePhone) {
					try {
						GiftRegistry.setValidateContactInformation(true);
						validateContactInfo(firstNameValue, lastNameValue, email, streetAddressValue,
								cityValue, cityValue, zipCodeValue, phoneValue);
					} catch (Exception e) {
						setTestCaseDescription(getTestCaseDescription());
						logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, e.getMessage()));
						e.printStackTrace();
						String temp = getTestCaseReportName();
						Common.testFail(e, temp);
						Assert.assertTrue(false, e.getMessage());
					}
				}
	        });

			sassert().assertTrue(getValidateContactInformation(),
				"Error check the user address in excel sheet, user email: " + email);
		} else {
			boolean validateContactInformation = validateContactInfo(Registration.userFirstName, Registration.userLastName, Registration.userEmail,
					addressDetails.get(AddressBook.shippingAddress.keys.adddressLine), addressDetails.get(AddressBook.shippingAddress.keys.city),
					addressDetails.get(AddressBook.shippingAddress.keys.city), addressDetails.get(AddressBook.shippingAddress.keys.zipcode),
					addressDetails.get(AddressBook.shippingAddress.keys.phone));
			GiftRegistry.setValidateContactInformation(validateContactInformation);

		}
		getCurrentFunctionName(false);
		return getValidateContactInformation();
	}

	public static void validateConfirmationModal() throws Exception {
		getCurrentFunctionName(true);
		if (isPWAMobile) {
			SelectorUtil.waitGWTLoadedEventPWA();
		}
		WebElement confirmationModal = SelectorUtil.getelement(GiftRegistrySelectors.confirmationModalGR.get());
		sassert().assertTrue(confirmationModal != null,
				"Error Confirmation gift registry created modal not displayed");
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.manageRegistryButton.get());
		getCurrentFunctionName(false);
	}

	public static void validateCreatedGiftRegistry() throws Exception {
		getCurrentFunctionName(true);

		// Validate that the page is manage gift registry.
		WebElement giftRegisrtContainer = SelectorUtil.getelement(GiftRegistrySelectors.manageGRContainer.get());
		sassert().assertTrue(giftRegisrtContainer != null,
				"Error this page is not manage gift registry");

		// Validate the selected registry.
		String selectedRegistry;
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.registryInfo.get());
		selectedRegistry = SelectorUtil.textValue.get();

		sassert().assertTrue(selectedRegistry.contains(registryName),
				"Error in selected registry, expected " + registryName + " : " + selectedRegistry);

		// Validate the empty registry.
		String emptyMsg = SelectorUtil.getelement(GiftRegistrySelectors.emptyRegistryMsg.get()).getText().trim().toLowerCase();
		sassert().assertTrue(emptyMsg.equals(emptyMessage.toLowerCase().trim()),
				"Error empty messages is not as expected " + emptyMessage.toLowerCase().trim() + " : " + emptyMsg);

		// Validate gift card container.
		WebElement giftCardContainer = SelectorUtil.getelement(GiftRegistrySelectors.giftCardContainer.get());
		sassert().assertTrue(giftCardContainer != null,
				"Error gift card section not displayed at manage gift card page.");
		getCurrentFunctionName(false);
	}

	public static boolean validateContactInfo(String firstName,String lastName,String emailAddress,String streetAddress,String cityAddress,String regionAddress,String zipCode,String phone) throws Exception {

		getCurrentFunctionName(true);
		boolean validateContactInformation = true;

		// Select the addresses from the form.
		String firstNameValue = getElementText(GiftRegistrySelectors.firstNameGR.get());
		String lastNameValue = getElementText(GiftRegistrySelectors.lastNameGR.get());
		String emailAddressValue = getElementText(GiftRegistrySelectors.emailAddressGR.get());
		String streetAddressValue = getElementText(GiftRegistrySelectors.streetAddressGR.get());
		String cityAddressValue = getElementText(GiftRegistrySelectors.cityAddressGR.get());
		SelectorUtil.initializeSelectorsAndDoActions(GiftRegistrySelectors.regionAddressGR.get());
		String regionAddressValue = SelectorUtil.textValue.get();
		String zipCodeValue = getElementText(GiftRegistrySelectors.zipCodeGR.get());
		String phoneValue = getElementText(GiftRegistrySelectors.phoneGR.get()).replace("-", "");

		// Validate the contact information.
		boolean validateFirstName = firstNameValue.equals(firstName);
		boolean validateLastName = lastNameValue.equals(lastName);
		boolean validateEmail= emailAddressValue.equals(emailAddress);
		boolean validateStreetAddress = streetAddressValue.equals(streetAddress);
		boolean validateCity= cityAddressValue.equals(cityAddress);
		boolean validateRegion = regionAddressValue.toLowerCase().equals(regionAddress.toLowerCase());
		boolean validateZipCode = zipCodeValue.equals(zipCode);
		boolean validatePhone = phoneValue.equals(phone);

		sassert().assertTrue(validateFirstName,
				"Error first name is not as expected" + firstName + " : " + firstNameValue);
		sassert().assertTrue(validateLastName,
				"Error last name is not as expected" + lastName + " : " + lastNameValue);
		sassert().assertTrue(validateEmail,
				"Error email address is not as expected" + emailAddress + " : " + emailAddressValue);
		sassert().assertTrue(validateStreetAddress,
				"Error street address not as expected" + streetAddress + " : " + streetAddressValue);
		sassert().assertTrue(validateCity,
				"Error city address is not as expected" + cityAddress + " : " + cityAddressValue);
		sassert().assertTrue(validateRegion,
				"Error region address is not as expected" + regionAddress + " : " + regionAddressValue);
		sassert().assertTrue(validateZipCode,
				"Error zip code is not as expected" + zipCode + " : " + zipCodeValue);
		sassert().assertTrue(validatePhone,
				"Error phone is not as expected" + phone + " : " + phoneValue);

		if (!validateFirstName || !validateLastName ||
				!validateEmail ||
				!validateStreetAddress ||
				!validateCity ||
				!validateRegion ||
				! validateZipCode||
				!validatePhone
				) {
			validateContactInformation = false;

		}
		getCurrentFunctionName(false);
		return validateContactInformation;
	}
}
