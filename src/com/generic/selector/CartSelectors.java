package com.generic.selector;

import com.generic.setup.cselector;

public class CartSelectors
{
	public static final cselector paypalCheckoutBtn=new cselector("css,#checkout-with-paypal-button","css,button > div > div > img");
	public static final cselector shipping = new cselector("css,div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > span");
	public static final cselector tax = new cselector("css,div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > span");

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static final cselector addToCartBtn=new cselector("css,#gwt-add-to-cart-btn","css,#gwt-add-to-cart-btn");
	public static final cselector addedItemsInCart=new cselector("css,.order-item-display-wrapper","css, div> div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div > div.c-cart-product-item.u-flexbox.u-padding-md"); 
	public static final cselector addedItemsImage=new cselector("css,div.order-item-image-holder > img","css, div > div > div > div > div > div > div > div.c-cart-product-item.u-flexbox.u-padding-md > a > div > img"); 
	public static final cselector addedItemsPrice=new cselector("css, div.left-main-panel > div.items-container > div > div > div.order-item-total-price-panel > div > div","css,#app-main > div.t-cart.t--loaded > div > div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item.u-flexbox.u-padding-md > div > div.c-cart-product__quantity-and-price.order-item-quantity-panel > div.order-item-price-panel > div > div > div > span:nth-child(1)");
	public static final cselector addedItemsTotalPrice=new cselector("css,h4.subtotal-value.right","css,#app-footer > footer > div > div > div > div > div > div > table > tbody > tr > td.pw-ledger__value");
	public static final cselector firstAddedItemsOption=new cselector("css, div.left-main-panel > div> div:nth-child(1) > div> div > div > div > div> div > div>div","css,div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item.u-flexbox.u-padding-md > div > div.c-cart-product-item__product-attributes.u-margin-bottom-sm.u-position-relative > div > div");
	public static final cselector lastAddedItemsOption=new cselector("div.order-item-info-panel > div.order-item-details-panel > div.attribute-panel > div.oios > div > div> div","div > div.c-cart-product-item__product-attributes > div > div");

	public static final cselector firstAddedItemsEdit=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.edit-link","css,#app-main > div > div > div:nth-child(1) > div > div > div> div:nth-child(1) > div > button.c-cart-product__actions-edit.qa-cart__remove-item");
	public static final cselector firstAddedItemsRemove=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.remove-link","css,#app-main > div > div > div:nth-child(1) > div > div > div> div:nth-child(1) > div > button.pw-button.c-cart-product__actions-remove");
	public static final cselector firstAddedItemsMoveToLater=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.move-to-wishlist-link","css,#app-main > div > div > div:nth-child(1) > div > div > div > div:nth-child(1) > div > button.u-padding-end-0");
	public static final cselector editItemSavedAndCloseBtn=new cselector("css,td > div > div > div > button.button.primary","css,div.c-personalization-accordion__preview-save>div > div > div > div > button.pw--primary");
	public static final cselector editItemOpions=new cselector("css,#personalization-modal-options-scroll>div","css, .pw-sheet>div>div>div>div>div>div>div>div");
	public static final cselector editItemColorOptions=new cselector("css,div.gwt-accordion-tab.first > div.gwt-accordion-tab-content.tabopen > div > div > div > div> div > div> div","css,.m-product-options-no-padding>div>div>div>button");
	public static final cselector editItemSizeSelect=new cselector("css, div> div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option > div.gwt-product-option-panel-listbox-container > div > select","css, div.u-margin-top.m-product-options-no-padding > div > div > select");
	public static final cselector editItemColorOpen=new cselector("div > button.gwt-tab-header-edit-button.third","div>button:nth-child(1)","");
	public static final String editItemSizeOptions="css,div.gwt-personalization-modal-accordions-content-option > div> div > select>option";
//	public static final String editElementBtn="css,#personalization-modal-options-scroll>div>div > button.gwt-tab-header-edit-button.third";
	public static final cselector savedListFirstItem=new cselector("css,#saveForLater > tbody > tr:nth-child(2) > td > div > div > div:nth-child(1)","css,.pw-accordion--is-open>div > div > div > div:nth-child(1)");
	public static final cselector editItemOptionsText=new cselector("div > div.gwt-tab-header-title","button>div>div>div>div>div:nth-child(1)");
	public static final cselector returnFromWishListBtn=new cselector("> div > div> div.order-item-bottom-links-panel > a.move-to-wishlist-link",">div > button.c-cart-product__actions-move-to-wishlist");
	
	public static final cselector editSwatchOptions=new cselector("css,#personalization-modal-options-scroll > div > div> div > div > div > div > div > div > div:nth-child({0})","css,div:nth-child(1)  > div > div > div > div > div > div > div > div > div.m-product-options-no-padding > div > div:nth-child({0}) > div > button > div > div > div > div > div");
	public static final cselector editSwatchOptionsNumber=new cselector("css,#personalization-modal-options-scroll > div:nth-child(1) > div> div > div > div > div > div > div > div","css, div:nth-child(1)  > div > div > div > div > div > div > div > div > div.m-product-options-no-padding > div > div > div > button > div > div > div > div > div");
	//////////////////////////////////////////////////////////////////////////////
	//don
	
	//New Selectors
	public static final cselector editFromCartLink=new cselector("css,.edit-link", "css,.c-cart-product__actions-edit");
	public static final cselector expandOptionsModal=new cselector("css,.gwt-tab-header-sign-button");
	public static final cselector optionsImage =new cselector( "css,.gwt-image-picker-option-image","css,.pw-swatch__item img");
	public static final cselector optionsButton =new cselector("css,.gwt-selection-chip", "css,.c-product-option-items__item .pw-swatch__button");
	//public static final cselector optionsButton =new cselector("css,.gwt-product-option-panel-widget-panel > div  > div > div", "css,div> div > button > div > div > div > div > div.c-product-option-items__item-normal");
	public static final cselector optionsDropDown =new cselector( "css,.gwt-ListBox","css,.c-product-options__content");
	public static final cselector finishAndPreviewButton =new cselector("css,.c-personalization-modal__finish.pw--primary");
	public static final cselector finishAndPreviewButtonGR =new cselector("css,.pw-accordion__content .pw--primary");
	public static final cselector saveEditsButton =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.c-personalization-modal__save.pw--primary"); 
	public static final cselector saveEditsButtonGR =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.pw-accordion--is-open .pw-accordion__content-wrapper .pw--primary");
	
	
	public static final String numberOfProducts = "css,div.cart-heading>h2";
	public static final String unitPrice = "price-value";
	public static final String productSubtotal = "css,div.total-section.each-item-section>span.js-item-total";
	public static final String productSubtotalMobile = "price-holder totals-mobile";
	public static final String cartTotals = "js-cart-totals";
	public static final String checkoutBtn = "CONTINUE WITH CHECKOUT";
	public static final String shippingMethod = "css,select.deliveryMode_select";
	public static final String removeItem = "remove-cart-item";
	
	
	public static final String continueShopping = "dwfrm_cart_continueShopping";
	public static final String orderItemSubtotal = "order-subtotal";
	public static final String orderDiscount = "order-discount discount";
	public static final String orderTotal = "order-value value";
	public static final String totals = "totals-line-items";
	public static final String couponField = "voucherCode";
	public static final String applyCouponButton = "css,.voucher-code-btn";
//	public static final String couponErrorMessage = "css,#voucherForm .information-message";
	public static final String couponMessage = "css,#voucherForm";
	public static final String couponGlobalMessage = "information-message";
	//public static final String couponPositiveMessage = "information-message positive";
	//public static final String couponWarningMessage = "information-message warning";
	public static final String updateBtn = "btn-update-cart-item";
	public static final String productQtyBox = "quantity";
	public static final String errorMessage = "information-message warning";
	public static final String postitiveMsg = "information-message positive";
	public static final String removeCoupon = "css,.voucher-code-btn";
	public static final String OrderSubTotal = "test_Order_Totals_Subtotal_$1";
	public static final String glabalErrorMessage = "alert alert-danger alert-dismissable";
	public static final String qty = "quantity_0";
	public static final String productQtyBoxMobile = "css,td>li>div>div>div>form>div>input#quantity_";
	public static final String updateQunatityBtn = "test_Order_Totals_Subtotal_$1";
	public static final String actionMenue = "editEntry_";
	
	public static final String cartContent = "yCmsComponent yComponentWrapper content__empty";
	public static final String cartOrderTotal = "test_cart_totalPrice_label_$";
	public static final String cartOrderShipping = "cart-totals";
	public static final String cartOrderTax = "cart-totals-taxes text-right";
	public static final String itemImages = "css,li>div>a>img";
	public static final String itemLink = "css,td>li>div>div>a";
	
}
