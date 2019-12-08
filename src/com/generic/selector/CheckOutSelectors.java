package com.generic.selector;

import com.generic.setup.cselector;

public class CheckOutSelectors
{
	//Done CBI
	//Navigation
	public static final cselector beginSecureCheckoutButton = new cselector("css,.secure-checkout-button","css,.c-checkout-buttons__checkout .pw--primary");
	public static final cselector guestCheckoutButton = new cselector("css,.guest-checkout-button-panel > button:nth-child(1)","css,#single-page-checkout-container > div > div.t-checkout-step0.customer-information-panel > div.guest-checkout-form > button");
	
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
	public static final cselector stepTwoIdentifier2 =new cselector("css,.csb-panels-list-box","css,.t-checkout-step2__truck-delivery-panel");


	
	//Step 3
	public static final cselector thirdStepNextButton = new cselector("css,.billing-address .next-botton-panel .primary-button","css,.c-checkout-accordion__next-cancel-panel .pw--primary");
	public static final cselector emailBillingAddress =new cselector("css,#guest_email_field","css,#guest_email_field");
	
	//Step 4
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
	
	////////End of CBI Cjeckout Selectors
	
	
	
	// Payment Type
	public static final String PaymentTypeSelection_CARD = "PaymentTypeSelection_CARD";
	public static final String PaymentTypeSelection_ACCOUNT = "PaymentTypeSelection_ACCOUNT";
	public static final String PurchaseOrderNumber = "text form-control";
	public static final String PaymentType_continue_button = "btn btn-primary btn-block checkout-next";
	public static final String PaymentTypeCostCenterSelect = "costCenterSelect";
	
	
	//Shipping Address
	//done
	

	public static final String emailAddress = "contactEmailId";
	public static final String  address= "shipLine1";
	public static final String submitShippingAddressBtn = "addressSubmit";
	public static final String pickSuggestedAddrress = "use_suggested_address_button";
	public static final String addresses = "dwfrm_singleshipping_addressList";
	public static final String addAddressChekbx = "Add New Address";
	public static final String addressBookBtn = "css,section>div>div>div>div>div>div>button";
	public static final String CheckSaveAddress = "SaveAddress";
	public static final String title = "address.title";
	public static final String orderTotalShippingAddress = "test_cart_totalPrice_label_$";
	public static final String orderSubTotalShippingAddress = "test_Order_Totals_Subtotal_$";
	public static final String fnameError = "firstName.errors";
	public static final String lnameError = "lastName.errors";
	public static final String address1Error = "line1.errors";
	public static final String cityError = "townCity.errors";
	public static final String postcodeEerror = "postcode.errors";
	public static final String alertInfo = "alert alert-danger alert-dismissable";
	public static final String titleError = "titleCode.errors";
	public static final String countryError = "countryIso.errors";
	
	//shipping method
	//done
	
	public static final String shippingMethod = "shipping-method-1POS_";
	public static final String continueToPaymentMethodBtn = "dwfrm_singleshipping_shippingAddress_save";
	public static final String ShippingMail = "dwfrm_singleshipping_shippingAddress_addressFields_friendshipRewards_email";	
	public static final String addshippingMethodBtn = "change_mode_button";
	public static final String submitShippingMethodbtn = "use_this_delivery_method";
	public static final String orderTotalShippingMethod = "test_cart_totalPrice_label_$";
	public static final String orderSubTotalShippingMethod = "test_Order_Totals_Subtotal_$";
	
	//Payment info
	//done 
	public static final String paypal = "paymentMethodPayPal";
	public static final String cc = "paymentMethodBT";
	public static final String cardHolder = "cardholderName";
	public static final String cardNumber = "credit-card-number";
	public static final String expirationDate = "expiration";
	public static final String CVV = "cvv";
	public static final String Bcountery = "address.country";
	public static final String BfirstName = "address.firstName";
	public static final String BlastName = "address.surname";
	public static final String Baddress= "address.line1";
	public static final String Bcity = "address.townCity";
	public static final String Bstate = "address.region";
	public static final String Bzipcode = "address.postcode";
	public static final String Bphone = "address.phone";
	public static final String submitPayment = "submit_silentOrderPostForm";
	
	
	
	public static final String selectFirstPayment = "creditCardList";
	public static final String orderReviewBtn = "Continue to review Order";
	public static final String cardtype = "dwfrm_billing_paymentMethods_creditCard_type";
	public static final String expireDay = "new_dwfrm_billing_paymentMethods_creditCard_expiration_month";
	public static final String expireYear = "new_dwfrm_billing_paymentMethods_creditCard_expiration_year";
	public static final String checkSavePayment = "SaveDetails";
	public static final String addPaymentBtn = "css,section>div>div>div>div>div>a.linkarrow";
	public static final String checkSame = "differentAddress";
	public static final String savedPaymentBtn = "btn btn-default btn-block js-saved-payments";
	public static final String orderTotalPymentInfo = "test_cart_totalPrice_label_$";
	public static final String orderShippingPymentInfo = "test_Order_Totals_Delivery_$";
	public static final String orderSubtotalPymentInfo = "test_Order_Totals_Subtotal_$";
	public static final String cardTypeError = "card_cardType.errors";
	public static final String accountNumberError = "card_accountNumber.errors";
	public static final String expirationMonthError = "card_expirationMonth.errors";
	public static final String expirationYearError = "card_expirationYear.errors";
	public static final String cvNumberError = "card_cvNumber.errors";
	public static final String billToCountryError = "billTo_country.errors";
		
	//summary - review / place order
	//done

	public static final String summaryTotal = "order-subtotal";
	public static final String placeOrderBtn = "submit";
	public static final String acceptTerm = "Terms1";
	public static final String orderTotalOrderSumary = "test_cart_totalPrice_label_$";
	
	//Order confirmation
	//done
	public static final String orderdetails = "css,div#order-details-section>div";
	
	public static final String orderConfirmationSubtotal = "order-subtotal";
	public static final String orderId = "order-number";
	public static final String orderConfirmationTotal = "test_orderTotal_totalPrice_label_$";
	public static final String orderConfirmationShippingCost = "test_orderTotal_devlieryCost_label_$";
	public static final String orderconfirmationBillingAddress = "col-sm-6 col-md-4 order-billing-address";
	public static final String orderconfirmationshippingAddress = "order-ship-to";
	
	//B2B Order confirmation
	public static final String B2BorderConfirmationTotal = "text-right totals";
	public static final String B2BorderconfirmationBillingAddress = "col-sm-6 order-billing-address";
	public static final String B2BorderconfirmationshippingAddress = "col-md-5 order-ship-to";
	
	//Guest order
	//done
	public static final String choseToLogin = "css,#cart-sign-in-holder .btn-text";
	public static final String returningcustomerUsername = "j_username";
	public static final String returningcustomerPassword = "j_password";
	public static final String returningcustomerloginBtn = "css,#loginForm button";
	
	public static final String ShippingMethodType = "is-test";
	public static final String guestMail = "guest.email";
	public static final String guestConfirmationMail = "confirmGuestEmail form-control";
	public static final String guestCreateAccButton = "test_guest_Register_button_$";
	public static final String guestCreateAccOtpin = "consentForm.consentGiven1";
	public static final String guestCreateAccCPwd = "guest.checkPwd";
	public static final String guestCreateAccPwd = "password";
	
}
