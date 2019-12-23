package com.generic.selector;

import com.generic.setup.cselector;

public class CheckOutSelectors
{
	//Done CBI
	//Navigation
	public static final cselector beginSecureCheckoutButton = new cselector("css,.secure-checkout-button","css,.c-checkout-buttons__checkout .pw--primary");
	public static final cselector guestCheckoutButton = new cselector("css,.guest-checkout-button-panel > button:nth-child(1)","css,#single-page-checkout-container > div > div.t-checkout-step0.customer-information-panel > div.guest-checkout-form > button");
	public static final cselector stepLoaderButton = new cselector("button.pw-button.pw--primary.u-width-full>div>div.c-custom-loader");
	
	//Step 1 Multiple Addresses
	public static final cselector multipleAddressesTab = new cselector( "css,.shipping-address .gwt-TabBarItem .gwt-HTML","css,.t-checkout-step1__tabs .pw-tabs__tab a");
	public static final cselector addAddressButton = new cselector( "css,.order-item-shipping-panel .button","css,.t-cart__product-list .add-address-button:nth-child(odd)");
	public static final cselector saveAddressButton = new cselector("css,.okCancelPanel .button.primary","css,.m-address_widget_modal button.pw--primary");
	public static final cselector firstStepNextButton = new cselector( "css,.next-botton-panel .primary-button","css,.c-checkout-accordion__next-cancel-panel .pw--primary");
	public static final cselector firstName =new cselector( "css,.add-address-dialog .AddrFNameSpot input","css,.m-address_widget_modal .c-custom-input-fname input");
	public static final cselector lastName= new cselector("css,.add-address-dialog .AddrLNameSpot input","css,.m-address_widget_modal .c-custom-input-lname input"); 
	public static final cselector streetAddress =new cselector( "css,.add-address-dialog .addrStreet1Spot input","css,.m-address_widget_modal .c-custom-input-street1 input");
	public static final cselector city =new cselector( "css,.add-address-dialog .addrCitySpot input"); 
	public static final cselector state= new cselector("css,.add-address-dialog .addrStateSpot input");
	public static final cselector zipCode = new cselector( "css,.add-address-dialog .addrZipSpot input","css,.m-address_widget_modal .c-custom-input-addrzip input"); 
	public static final cselector phone =new cselector("css,.add-address-dialog .addrPhone1Spot input","css,.m-address_widget_modal .c-custom-input-addphone1 input");
	
	//Step 1 Single Addresses
	public static final cselector firstNameSingle =new cselector( "css,#ship_fnbox","css,#ship_fnbox");
	public static final cselector lastNameSingle= new cselector("css,#ship_lnbox","css,#ship_lnbox"); 
	public static final cselector streetAddressSingle =new cselector( "css,#ship_sa1box","css,#ship_sa1box");
	public static final cselector citySingle =new cselector( "css,#ship_citybox"); 
	public static final cselector stateSingle= new cselector("css,#ship_region");
	public static final cselector zipCodeSingle = new cselector( "css,#ship_zipbox","css,#ship_zipbox"); 
	public static final cselector phoneSingle =new cselector("css,#ship_phone1box","css,#ship_phone1box");
	
	//Step 2
	public static final cselector secondStepNextButton = new cselector("css,.next-botton-panel .ship-method-and-gift-next-button","css,.c-checkout-accordion__next-cancel-panel .pw--primary");
	public static final cselector productContainerInStepTwo =new cselector("css,.shipping-method-and-gift-main-container .address-related-items-panel","css,.c-cart-product__link");
	public static final cselector stepTwoIdentifier =new cselector("css,.csb-panels-list-box","css,.t-checkout-shipping-and-gift__help-btn");
	public static final cselector stepTwoIdentifierGR =new cselector("css,.ship-method-panel .csb-panels-list-box","css,.t-checkout-shipping-and-gift__help-btn");
	public static final cselector stepTwoIdentifier2 =new cselector("css,.csb-panels-list-box","css,.t-checkout-step2__truck-delivery-panel");
	public static final cselector stepTwoIdentifier2GR =new cselector("css,.ship-method-panel .csb-panels-list-box","css,.t-checkout-step2__truck-delivery-panel");


	
	//Step 3
	public static final cselector thirdStepNextButton = new cselector("css,.billing-address .next-botton-panel .primary-button","css,.c-checkout-accordion__next-cancel-panel .pw--primary");
	public static final cselector emailBillingAddress =new cselector("css,#guest_email_field","css,#guest_email_field");
	
	//Step 4
	public static final cselector creditCartTab = new cselector("css,.cc-tab-header", "css,.pw-tabs__link");	
	public static final cselector shippingAndTaxCost = new cselector("css,.costs-holder .gwt-InlineLabel", "css,.pw-ledger__value");
	public static final cselector subTotalValue = new cselector("css,.costs-holder .subtotal-value"); 
	public static final cselector creditCardField = new cselector("css,#accountcc", "css,#accountcc"); 
	public static final cselector monthField = new cselector("css,#exp-month", "css,.c-custom-select");  
	public static final cselector yearField = new cselector("css,#exp-year", "css,.t-checkout-payment__card-year .c-custom-select"); 
	public static final cselector cvv = new cselector("css,#cvv", "css,#cvv","css,html body.cvv-provider-body div.animated-label-textbox-panel input#cvv"); 
	public static final cselector placeSecureOrderButton = new cselector("css,.place-order-panel .primary-button","css,.t-checkout-footer .pw--primary");
	
	//Confirmation Page
	public static final cselector closeRegisterButton = new cselector("css,.okCancelPanel .button.secondary");
	public static final cselector itemID= new cselector("css,.gwt-oid-number", "css,.order-item-part-number");
	public static final cselector closePoromotionalModal= new cselector("css,.extole-js-widget-wrapper.extole-widget-wrapper > a","css,.extole-js-widget-wrapper.extole-widget-wrapper > a");
	
	
	// PayPal
	public static final cselector paymentPagePayPalTitle=new cselector("css,tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2)","css, div > div.pw-accordion__title > a > h1 > span");
    public static final cselector paymentPagePayPalSubmitBtn=new cselector("css,.place-order-panel > button:nth-child(1)","css,div.c-checkout-buttons__checkout > button");
    public static final cselector paymentSubmitPopUpClose=new cselector("css,#extole-6763275864515365558 > div > div.extole-js-widget-wrapper.extole-widget-wrapper > a","css,#extole-6763275864515365558 > div > div.extole-js-widget-wrapper.extole-widget-wrapper > a");
    public static final cselector paymentPayPalSubmitRegistrationCloseBtn=new cselector("css,td > div > button.secondary");
    public static final cselector paypalSubmitConfermationMessage=new cselector("css,div.header-panel > div.brand-confirm-message-panel > div","css, p.t-checkout-confirmation__header-confirm-message");
    public static final cselector paypalConfermationPageAllProduct=new cselector("css,div.ship-method-and-gift-container-panel","css,div.t-checkout-order-details-and-shipping > div > div > div:nth-child(2) > div> div > div");
    public static final cselector orderNumber=new cselector("css,div.header-panel > div.order-number-panel > div:nth-child(2)","css,div.order-number");
    public static final cselector email=new cselector("css,div.header-panel > div.email-address-panel > div:nth-child(2)","css,div.email-address"); 
    public static final cselector shippingAddress=new cselector("css,div.shipping-address-title-holder > div","css,div.city-state-address-panel");
    public static final cselector confirmationPageProductImg=new cselector("css,div.order-item-image-holder > img","css,div.c-cart-product-item__image-container > img");
    public static final cselector confirmationPageAccountType=new cselector("css,div.paypal-account-header-panel > div","css,div.paypal-account-header-panel > div");
    public static final cselector confirmationPageSubtotal=new cselector("css,h4.subtotal-value","css,.t-cart__summary-subtotal > td.pw-ledger__value");
    public static final cselector confirmationShipping=new cselector("css,div.additional-charges-value.shipping > span","css,.t-cart__summary-shipping-charges > td.pw-ledger__value");
    public static final cselector confirmationPageTax=new cselector("css,div.additional-charges-value.tax> span","css,div:nth-child(3) > div > div > table > tbody > tr:nth-child(2) > td.pw-ledger__value");
    public static final cselector confirmationTotal=new cselector("css,div.estimated-total-value.right","css,.estimated-total > span:nth-child(2)");

}
