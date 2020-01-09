package com.generic.selector;

import com.generic.setup.cselector;

public class CartSelectors{
	
	public static final cselector paypalCheckoutBtn=new cselector("css,#checkout-with-paypal-button","css,button > div > div > img");
	public static final cselector shipping = new cselector("css,div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > span","css, tr.t-cart__summary-shipping-charges > td.pw-ledger__value");
	public static final cselector tax = new cselector("css,div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > span","css, table > tbody > tr:nth-child(2) > td.pw-ledger__value");
	public static final cselector paymentPageCheckNextBtn=new cselector("css,div.gwt-accordion.checkout-steps.tabopen > div:nth-child(2) > div.gwt-accordion-tab-content.tabopen > div > div > div > div > div.next-botton-panel > button","css, div.c-checkout-accordion__next-cancel-panel > button");  
	public static final String taxGR="css, table > tbody > tr:nth-child(3) > td.pw-ledger__value";
	public static final cselector addToCartBtn=new cselector("css,#gwt-add-to-cart-btn","css,#gwt-add-to-cart-btn");
	
	//In cart
	public static final cselector addedItemsInCart=new cselector("css,.order-item-display-wrapper","css, div> div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div > div.c-cart-product-item");
	public static final cselector addedItemsImage=new cselector("css,div.order-item-image-holder > img","css, div > div > div > div > div > div > div > div.c-cart-product-item > a > div > img"); 
	public static final cselector addedItemsPrice=new cselector("css, div.left-main-panel > div.items-container  div.order-item-total-price-panel > div > div","css,#app-main > div.t-cart.t--loaded > div > div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item.u-flexbox.u-padding-md > div >  div.c-cart-product__quantity-and-price.order-item-quantity-panel > div.order-item-price-panel  span.t-product-new-price:nth-child(1)");
	public static final cselector GHRYaddedItemsPrice=new cselector("css, div.left-main-panel > div.items-container  div.order-item-total-price-panel > div > div","css,div:nth-child(1) > div > div > div> div:nth-child(1) > div.c-cart-product-item> div > div.c-cart-product__quantity-and-price > div.order-item-price-panel > div > div > span.t-product-new-price");
	public static final cselector addedItemsTotalPrice=new cselector("css,h4.subtotal-value.right","css,#app-footer > footer > div > div > div > div > div > div > table > tbody > tr > td.pw-ledger__value");
	
	//Items options
	public static final cselector firstAddedItemsOption=new cselector("css, div.left-main-panel > div> div:nth-child(1) > div> div > div > div > div> div > div>div","css,div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item > div > div.c-cart-product-item__product-attributes.u-margin-bottom-sm.u-position-relative > div > div");
	public static final cselector lastAddedItemsOption=new cselector("css,#shopping-cart-v2-root > div > div.main-panel > div.left-main-panel > div.items-container > div:nth-child(2) > div > div.order-item-info-panel > div.order-item-details-panel > div.attribute-panel.less > div > div > div > div","css,#app-main > div.t-cart.t--loaded > div > div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(2) > div.c-cart-product-item > div > div.c-cart-product-item__product-attributes.u-margin-bottom-sm.u-position-relative");
	
	//Edit options
	public static final cselector editFromCartLink=new cselector("css,.edit-link", "css,.c-cart-product__actions-edit");
	public static final cselector expandOptionsModal=new cselector("css,.gwt-tab-header-sign-button");
	public static final cselector optionsImage =new cselector( "css,.gwt-image-picker-option-image","css,.pw-swatch__item img");
	public static final cselector optionsButton =new cselector("css,#personalization-modal-options-scroll > div > div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option > div.gwt-product-option-panel-widget-panel > div > div > div:nth-child(2)", "css,.m-product-options-no-padding > div > div:nth-child(2) > div > button");
	public static final cselector optionsDropDown =new cselector( "css,.gwt-ListBox","css,.c-product-options__content");
	public static final cselector finishAndPreviewButton =new cselector("css,.c-personalization-modal__finish.pw--primary");
	public static final cselector finishAndPreviewButtonGR =new cselector("css,.pw-accordion__content .pw--primary");
	public static final cselector GHRYfinishAndPreviewButton =new cselector("css,.pw-accordion__content .pw--primary","css,div.u-flexbox.u-direction-column > button");
	public static final cselector saveEditsButton =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.c-personalization-modal__save.pw--primary"); 
	public static final cselector saveEditsButtonGR =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.pw-accordion--is-open .pw-accordion__content-wrapper .pw--primary");
	public static final cselector GHRYsaveEditsButton =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.c-personalization-accordion__preview-save  button.pw-button.pw--primary");
	//Saved for later wish-list
	public static final cselector firstAddedItemsMoveToLater=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.move-to-wishlist-link","css,#app-main > div > div > div:nth-child(1) > div > div > div > div:nth-child(1) > div > button.u-padding-end-0");
	public static final cselector savedListFirstItem=new cselector("css,#saveForLater > tbody > tr:nth-child(2) > td > div > div > div:nth-child(1)","css,.pw-accordion--is-open>div > div > div > div:nth-child(1)");
	public static final cselector returnFromWishListBtn=new cselector("> div > div> div.order-item-bottom-links-panel > a.move-to-wishlist-link",">div > button.c-cart-product__actions-move-to-wishlist");

	//Remove 
	public static final cselector firstAddedItemsRemove=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.remove-link","css,#app-main > div > div > div:nth-child(1) > div > div > div> div:nth-child(1) > div > button.pw-button.c-cart-product__actions-remove");

	
	public static final cselector WLFirstItem=new cselector("css,tr:nth-child(4) .productInfo","css,div.t-registry-list-product-list.u-width-full > div > div > div:nth-child(1)");
	public static final cselector GHsavedListFirstItem=new cselector("css,tr:nth-child(4)","css,div.t-registry-list-product-list.u-width-full > div > div > div:nth-child(1)");
	public static final cselector GHreturnFromWishListBtn=new cselector(" .wish-list-add-to-cart > div > a"," > div.t-registry-list-product-list__details-add-container > button.t-registry-list-product-list__add-to-cart-btn");
	public static final cselector RYreturnFromWishListBtn=new cselector(" .wish-list-add-to-cart > div > a"," > div.t-registry-list-product-list__product-details > div.t-registry-list-product-list__actions-container > div:nth-child(1) > div > div > button");
	public static final cselector WLModal = new cselector("css,div.checkout-v2-add-to-wish-list-modal > div");
	public static final cselector addedToWLModal = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.checkout-v2-added-to-wish-list-modal");
	public static final cselector viewListBtn = new cselector("css,div.okCancelPanel >  button.button.primary");
	public static final cselector GHRYfirstAddedItemsRemove=new cselector("css,div:nth-of-type(1) > div> div > div> div > .action.order-item-link.remove-link","css,#app-main > div > div > div:nth-child(1) > div > div > div> div:nth-child(1) > div > button.pw-button.c-cart-product__actions-remove");
	public static final cselector selectWLConfiramtionBtn=new cselector("","css,div.c-cart-product__wishlist.u-position-relative > div > button.pw-button");
	public static final cselector selectWL = new cselector("css,select.c-custom-select");
	public static final cselector selectWLOptions = new cselector("css,select.c-custom-select > option");
	public static final cselector checkoutBtn = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div button.button.primary","css,div.u-flexbox.u-direction-column > button.pw-button.pw--primary");
	public static final cselector RYGoToShoppingBagBtn = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div button.button.primary","css,div > div > div.m-add-to-cart__buttons-container > button");
	public static final cselector WLName = new cselector("css,input#wishlist_name_id");
	public static final cselector nameYourNewWLconfirmationBtn = new cselector("css,#gwt-wishlist-create-modal  button.button.primary","css,div > div.u-width-full > button.pw--primary");

}
