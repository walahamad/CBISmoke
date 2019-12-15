package com.generic.selector;

import com.generic.setup.cselector;

public class GiftRegistrySelectors {
	public static final cselector GRLink = new cselector("css,#giftregistry a");
	public static final cselector FGCreateRegistryButton = new cselector("css,.create-registry","css,.responsive-content .create-registry");
	public static final cselector GRCreateRegistryButton = new cselector("css,[onclick*=submitCreateRegisty]","css,.responsive-content [onclick*=submitCreateRegisty]");

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
	public static final cselector addGRProductToCart = new cselector("css,.gr_add_to_cart_btn", "css,.t-registry-list-product-list__product-add-to-cart .pw-button");
	public static final cselector addCartFromGRModal = new cselector("css,.gwt_addtocart_div", "css,.t-registry-list-product-list__product-add-to-cart .pw-button");
	public static final cselector checkoutFromGRModal = new cselector("css,.gwt_addtocart_div .primary", "css,.t-registry-list-product-list__product-add-to-cart .pw-button");
	public static final cselector productListGR = new cselector("css,[id*=gwt_giftregistry_item_display]", "css,.t-registry-list-product-list");

	// Gift registry at PDP
	public static final cselector selectGRModal = new cselector("css,#gwt-add-to-gift-registry-modal", "css,.m-select_wl_gr_modal");
	public static final cselector saveToGR = new cselector("css,#gift-registry-btn", "css,.t-product-details #gift-registry-btn");
	public static final cselector FGListBoxGR = new cselector("css,.gift-registry-list-bx", "css,.m-select_wl_gr_modal.m-select_wl_gr_modal .c-custom-select");
	public static final cselector GRListBoxGR = new cselector("css,.gift-registry-list-bx", "css,.m-select_wl_gr_modal.m-select_wl_gr_modal select");

	public static final cselector FGAddToGiftRegistySelectButton = new cselector("css,#gwt-add-to-gift-registry-modal .primary", "css,.m-select_wl_gr_modal .pw-sheet__content .pw-button");
	public static final cselector GRAddToGiftRegistySelectButton = new cselector("css,#gwt-add-to-gift-registry-modal .primary", "css,.m-select_wl_gr_modal .pw--primary");

	public static final cselector addToGiftRegistyModal = new cselector("css,.gwt-added-to-gift-registry-modal", "css,.m-add-to-registry");
	public static final cselector viewRegistryButton = new cselector("css,.gwt-added-to-gift-registry-modal .primary", "css,.m-add-to-registry .pw--secondary");
	public static final cselector productAddedToGRContainer = new cselector("css,.addToCartProductsList", "css,.m-registry-confirmation__image");
	public static final cselector movableMiniCart = new cselector("css,.c-movable-cart-item-counter-badge");

	// Gift registry in shopping.
	public static final cselector addedFromGR = new cselector("css,.from-registry-link", "css,.t-cart__messages-from-customer-registry");
	public static final cselector miniCartText = new cselector("css,.cartText", "css,.qa-header__cart .pw-badge span>span");
	public static final cselector cartProductContainer = new cselector("css,.order-item-display-widget-v2", "css,.c-cart-product");
	public static final cselector itemInCart = new cselector("css,.c-cart-product-item");
}
