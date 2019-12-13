package com.generic.selector;

import com.generic.setup.cselector;

public class CartSelectors{
	
	//In cart
	public static final cselector addedItemsInCart=new cselector("css,.order-item-display-wrapper","css, div> div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div > div.c-cart-product-item.u-flexbox.u-padding-md"); 	
	public static final cselector addedItemsImage=new cselector("css,div.order-item-image-holder > img","css, div > div > div > div > div > div > div > div.c-cart-product-item.u-flexbox.u-padding-md > a > div > img"); 
	public static final cselector addedItemsPrice=new cselector("css, div.left-main-panel > div.items-container > div > div > div.order-item-total-price-panel > div > div","css,#app-main > div.t-cart.t--loaded > div > div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item.u-flexbox.u-padding-md > div > div.c-cart-product__quantity-and-price.order-item-quantity-panel > div.order-item-price-panel > div > div > div > span:nth-child(1)");
	public static final cselector addedItemsTotalPrice=new cselector("css,h4.subtotal-value.right","css,#app-footer > footer > div > div > div > div > div > div > table > tbody > tr > td.pw-ledger__value");
	
	//Items options
	public static final cselector firstAddedItemsOption=new cselector("css, div.left-main-panel > div> div:nth-child(1) > div> div > div > div > div> div > div>div","css,div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(1) > div.c-cart-product-item.u-flexbox.u-padding-md > div > div.c-cart-product-item__product-attributes.u-margin-bottom-sm.u-position-relative > div > div");
	public static final cselector lastAddedItemsOption=new cselector("css,#shopping-cart-v2-root > div > div.main-panel > div.left-main-panel > div.items-container > div:nth-child(2) > div > div.order-item-info-panel > div.order-item-details-panel > div.attribute-panel.less > div > div > div > div","css,#app-main > div.t-cart.t--loaded > div > div:nth-child(1) > div > div > div.pw-list.items-container.u-bg-color-neutral-00.u-border-light-bottom > div:nth-child(2) > div.c-cart-product-item.u-flexbox.u-padding-md > div > div.c-cart-product-item__product-attributes.u-margin-bottom-sm.u-position-relative");
	
	//Edit options
	public static final cselector editFromCartLink=new cselector("css,.edit-link", "css,.c-cart-product__actions-edit");
	public static final cselector expandOptionsModal=new cselector("css,.gwt-tab-header-sign-button");
	public static final cselector optionsImage =new cselector( "css,.gwt-image-picker-option-image","css,.pw-swatch__item img");
	public static final cselector optionsButton =new cselector("css,.gwt-selection-chip", "css,.c-product-option-items__item .pw-swatch__button");
	public static final cselector optionsDropDown =new cselector( "css,.gwt-ListBox","css,.c-product-options__content");
	public static final cselector finishAndPreviewButton =new cselector("css,.c-personalization-modal__finish.pw--primary");
	public static final cselector finishAndPreviewButtonGR =new cselector("css,.pw-accordion__content .pw--primary");
	public static final cselector saveEditsButton =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.c-personalization-modal__save.pw--primary"); 
	public static final cselector saveEditsButtonGR =new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.primary","css,.pw-accordion--is-open .pw-accordion__content-wrapper .pw--primary");
		
	//Saved for later wish-list
	public static final cselector firstAddedItemsMoveToLater=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.move-to-wishlist-link","css,#app-main > div > div > div:nth-child(1) > div > div > div > div:nth-child(1) > div > button.u-padding-end-0");
	public static final cselector savedListFirstItem=new cselector("css,#saveForLater > tbody > tr:nth-child(2) > td > div > div > div:nth-child(1)","css,.pw-accordion--is-open>div > div > div > div:nth-child(1)");
	public static final cselector returnFromWishListBtn=new cselector("> div > div> div.order-item-bottom-links-panel > a.move-to-wishlist-link",">div > button.c-cart-product__actions-move-to-wishlist");

	//Remove 
	public static final cselector firstAddedItemsRemove=new cselector("css,#shopping-cart-v2-root > div > div > div > div > div:nth-child(1) > div > div> div > div > a.remove-link","css,#app-main > div > div > div:nth-child(1) > div > div > div> div:nth-child(1) > div > button.pw-button.c-cart-product__actions-remove");

	

}
