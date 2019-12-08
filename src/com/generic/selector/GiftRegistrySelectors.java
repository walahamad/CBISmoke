package com.generic.selector;

import com.generic.setup.cselector;

public class GiftRegistrySelectors {
	public static final String GRLink = "css,#giftregistry a";
	public static final cselector createRegistryButtonFG = new cselector("css,.create-registry","css,.responsive-content .create-registry");
	public static final cselector createRegistryButtonGR = new cselector("css,[onclick*=submitCreateRegisty]","css,.responsive-content [onclick*=submitCreateRegisty]");

	// Gift registry step one.
	public static final cselector eventType = new cselector("css,#event_type_id","css,.t-gift-registry-information #event_type_id");
	public static final cselector eventDay = new cselector("css,#gr_event_day","css,.t-gift-registry-information__event-date select");
	public static final cselector eventYear = new cselector("css,#gr_event_year","css,.t-gift-registry-information__event-date select");
	public static final cselector eventMonth = new cselector("css,#gr_event_month","css,.t-gift-registry-information__event-date select");
	public static final cselector registryName = new cselector("css,#gr_reg_name","css,.t-gift-registry-information #gr_reg_name");
	public static final cselector confirmInformtionButton = new cselector("css,.gwt-gr-create-next-button","css,.t-gift-registry__next");
	public static final cselector stepOneContainer = new cselector("css,.GR_create_progressBar_Step1","css,.t-create-gift-registry-step-one");

	// Contact Information (Gift registry step two).
	public static final cselector firstNameGR = new cselector("css,#registrant_fnbox","css,.t-registrant-information #registrant_fnbox");
	public static final cselector lastNameGR = new cselector("css,#registrant_lnbox","css,.t-registrant-information #registrant_lnbox");
	public static final cselector emailAddressGR = new cselector("css,#registrant_emailbox","css,.t-registrant-information #registrant_emailbox");
	public static final cselector streetAddressGR = new cselector("css,#registrant_sa1box","css,.t-registrant-information #registrant_sa1box");
	public static final cselector cityAddressGR = new cselector("css,#registrant_citybox","css,.t-registrant-information #registrant_citybox");
	public static final cselector regionAddressGR = new cselector("css,#registrant_region","css,.t-registrant-information #registrant_region");
	public static final cselector zipCodeGR = new cselector("css,#registrant_zipbox","css,.t-registrant-information #registrant_zipbox");
	public static final cselector phoneGR = new cselector("css,#registrant_phone1box","css,.t-registrant-information #registrant_phone1box");
	public static final cselector createRegistryButtonStepTwo = new cselector("css,.GR_create_buttons_step3 .primary", "css,.t-gift-registry__next");
	public static final cselector stepTwoContainer = new cselector("css,.GR_create_progressBar_Step2","css,.t-create-gift-registry-step-two");

	// Manage gift registry Modal.
	public static final cselector confirmationModalGR = new cselector("css,.gwt-gift-registry-create-confirmation-dialog","css,.m-confirmation_box_modal");
	public static final cselector manageRegistryButton = new cselector("css,.gwt-gift-registry-create-confirmation-dialog .okCancelPanel .secondary","css,.m-confirmation_box_modal .pw--secondary");

	// Manage gift registry page.
	public static final cselector manageGRContainer = new cselector("css,.manage-registry-page","css,.t-manage-gift-registry");
	public static final cselector registryInfo = new cselector("css,#descriptionGR","css,.t-registry-select-list select");
	public static final cselector emptyRegistryMsg = new cselector("css,.gift_registry_manage_WWCM","css,.t-registry-no-products-list__description");
	public static final cselector giftCardContainer = new cselector("css,.gr_custom_add_to_cart","css,.t-personalized-gift-card");
	public static final cselector beginAddingItemsButton = new cselector("css,.gift_registry_manage_WWCM + .spot.actions .primary", "css,.t-registry-no-products-list__add-btn");

}
