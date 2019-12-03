package com.generic.selector;

import com.generic.setup.cselector;

public class PDPSelectors {
	
	//CBI SMK
	
	public static final cselector title = new cselector("css,.gwt-product-detail-product-name-label","css,.t-product-details-heading__title.u-margin-bottom");
	public static final cselector avaibleOptions = new cselector("css,.gwt-product-option-panel-widget-panel>div","css,.c-product-option-items__container");
	public static final cselector allColors = new cselector("css,.gwt-image-picker-option >img","css,.pw-swatch__chip>.pw-swatch__chip-inner>.c-product-option-items__item-content.gwt-image-picker-option");
	public static final cselector allSizes = new cselector("css,.gwt-product-option-panel-listbox-container>div> .gwt-ListBox","css,.c-product-options__content.u-border-brand-color.u-border-radius");
	public static final cselector allFabrics = new cselector("css,.gwt-image-picker-option-holder>.gwt-selection-chip-picker-option", "css,.pw-swatch__chip>.pw-swatch__chip-inner");
	public static final cselector allShipLeadTime = new cselector("css,.gwt-image-picker-option-holder>.gwt-selection-chip-picker-option","css,.pw-swatch__chip>.pw-swatch__chip-inner");
	public static final cselector addToCartBtn = new cselector("gwt-add-to-cart-btn");
	public static final cselector addToCartCloseBtn = new cselector("gwt-Button ok-cancel-close-btn");
	public static final cselector firstSwatchInOptions = new cselector("css,div#gwt-option-panel > div:nth-child({0})> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child(1)","css,div.c-product-options> div:nth-child({0})> div.u-margin-top > div > div:nth-child(1) > div > button > div > div > div > div > div");
	public static final cselector topPrice = new cselector("gwt-pdp-main-was-now-price-holder","css,.t-product-details-heading__price.t-product-details__price>div");
	public static final cselector addToWLGRBtnEnabled = new cselector("css,.button.secondary.gift-registry","css,.t-product-details>div>.pw-button.u-width-full.u-text-uppercase");
	public static final cselector addToWLGRBtnDisabled = new cselector("css,.button.secondary.gift-registry.disabled","css,.u-wishlist-gift-disable");
	public static final cselector addToCartBtnEnabled = new cselector("css,.button.primary#gwt-add-to-cart-btn","css,.c-add-to-cart");
	public static final cselector addToCartBtnDisabled = new cselector("css,.button.primary.disabled#gwt-add-to-cart-btn","css,.u-bg-disabled-color-add-to-cart");
	public static final cselector bottomPrice = new cselector("gwt-product-detail-widget-total-price-amount","css,.t-product-details-price>span");
	public static final cselector activeListBox = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-listbox>.gwt-product-option-panel-listbox-container>div>select > option:nth-child(2)");
	public static final cselector activeSwatchOption = new cselector("css,div#gwt-option-panel > div:nth-child(1)> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child(1)","css,div.c-product-options> div:nth-child(1)> div.u-margin-top > div > div:nth-child(1) > div > button > div > div > div > div > div");
	public static final cselector ListBox = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-listbox>.gwt-product-option-panel-listbox-container>div>select");
	public static final cselector swatchContainer = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-swatchbox");
	public static final cselector imageOption = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>div#gwt-option-panel > div:nth-child({0})> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child(1)");
	public static final cselector chooseYourItems = new cselector("gwt-choose-your-item-btn");
	public static final cselector numberOfBundleItems = new cselector("css,.gwt-product-detail-products-container>.gwt-product-detail-widget","css,.t-product-details-heading__price.t-product-details__price>div");	
	public static final cselector itemsID = new cselector("css,.gwt-product-detail-products-container>.gwt-product-detail-widget");	
	public static final cselector addToCartModal = new cselector("css,.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div");
	public static final cselector topPriceBundleDesktop = new cselector("css,.gwt-product-detail-right-panel.gwt-product-right-content-panel>.gwt-pdp-main-was-now-price-holder");
	public  final static cselector topPriceSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-right-center-content-panel>.gwt-product-price-container","css,.t-product-details-heading__price.t-product-details__price>div");
	public static final cselector addPersonalizationSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-detail-widget-personalization-panel>.gwt-personalize-link-style");
	public static final cselector addToWLGRBtnEnabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-gift-regestry-panel","css,.t-product-details>div>.pw-button.u-width-full.u-text-uppercase");
	public static final cselector addToWLGRBtnDisabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel.disabled>.product-bottom-gift-regestry-panel","css,.u-wishlist-gift-disable");
	public static final cselector addToCartBtnEnabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-qty-atc-panel>div>button#gwt-add-to-cart-btn.button.primary","css,.c-add-to-cart");
	public static final cselector addToCartBtnDisabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-qty-atc-panel>div>button#gwt-add-to-cart-btn.button.primary.disabled","css,.u-bg-disabled-color-add-to-cart");
	public static final cselector bottomPriceSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-total-price-panel>div>.gwt-product-detail-widget-total-price-amount","css,.t-product-details-price>span");
	public static final cselector bundleItems = new cselector("u-widget-product-icon u-position-absolute");
	public static final cselector bundleItem = new cselector("css,#gwt_bundledetail_json.store", "css,#gwt_bundledetail_json");
	public static final cselector miniPDPPrice = new cselector("css,.t-product-details-heading >div>.t-product-details-heading__price.t-product-details__price");
	
	
	public static final String bottomPriceBundle = "css,#{0}>" + bottomPriceSingle.DesktopSelector.replace("css,", "");
	public static final String addToCartBtnDisabledBundle = "css,#{0}>" + addToCartBtnDisabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToCartBtnEnabledBundle = "css,#{0}>" + addToWLGRBtnEnabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToWLGRBtnEnabledBundle = "css,#{0}>" + addToWLGRBtnEnabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToWLGRBtnDisabledBundle = "css,#{0}>" + addToWLGRBtnDisabledSingle.DesktopSelector.replace("css,", "");
	public final static String topPriceBundle = "css,#{0}>" + topPriceSingle.DesktopSelector.replace("css,", "");
	public static final String ListBoxBundle = "css,#{0}>" + ListBox.DesktopSelector.replace("css,", "");
	public static final String activeListBoxBundle = "css,#{0}>" + activeListBox.DesktopSelector.replace("css,", "");
	public static final String swatchContainerBundle = "css,#{0}>" + swatchContainer.DesktopSelector.replace("css,", "");
	public static final String imageOptionBundle = "css,#{0}>" + imageOption.DesktopSelector.replace("css,", "");
	
	
	// done-OCM
	public static String price = "price-and-special-messaging";
	public static String id = "code";
	public static String desc = "description";
	public static String socialMediaButtons = "css,div.st-btns";
	public static String shareBtn = "st-label";
	public static String numberOfProductsInBundle = "included-items-shipping";
	public static String productsInBundle = "css,div.each-included-item .information";
	public static String reviewsCount = "css,a.bv-rating-label.bv-text-link.bv-focusable>span";
	public static String reviewCountPerEachRate = "bv-inline-histogram-ratings-score";
	public static String starsRating = "bv-rating-stars-on bv-rating-stars";
	public static String secondaryRating = "bv-rating-ratio bv-td";

	public static String closSocialShareBtn = "st-close";
	public static String PDPnavs = "css,div.nav-tabs-container>ul>li";
	public static String PDPnavs_mobile = "css,div.tab-content>div>div>button";
	public static String favButton = "addToWishlist";
	public static String randomVariant = "css,.each-option-holder>div>.btn";
	public static String optionHolder = "each-option-holder";
	public static String optionHolderTitle="css,p>span.variant-text";

	public static String colorLable = "css,body>div>div>div>div>div>div>div>div>div>ul>li.attribute.color>div.label";
	public static String color = "Select color: ";
	public static String sizeAndFamilyLable = "css,div.product-variations > ul > div > ul > li > div.label";
	public static String lengthLable = "css,div>div>div.product-sister-styles>ul>li>div.label";
	public static String cart_popup = "popup-btn-checkout";
	public static String qty = "input-qty no-spin";
	public static String SA = "stock-in-stock";
	public static String cartPopupProductQty = "quantity";
	public static String miniCartProductUnitPrice = "unit-price";
	public static String minicart = "cart-component";
	public static String OverView;
	public static String size;

}
