package com.generic.selector;

import com.generic.setup.cselector;

public class RegistrationSelectors {
	//Done CBI Smoke
	//Navigation
	public static final String closeOfferButtonGH = "css,#Close1-Item4 .offer-control";
	public static final String registrationButton = "css, div.spot:nth-child(3) > button:nth-child(1)";
	public static final cselector registrationButtonRY = new cselector("css,.registration a","css,.t-registration-panel__action .pw-button__inner");
	public static final String mobileRegistrationButton= "css,button.pw-button:nth-child(2)";
	public static final String mobileRegistrationButtonGH= "css,.t-registration-panel__button";
	public static final String mobileRegistrationTab ="css,#app-main > div > div > div > div.pw-tabs__strip-container > ol > li.pw-tabs__tab.pw--is-active > a";
	public static final String mobileRegistrationTabGH ="css,.pw-tabs__link";				
	public static final String registerBtn = "css,#continue";
	public static final cselector saveButton = new cselector("css,#gwt_billshipaddr_btn > button","css,#register-form > div.pw-field-row.t-registration__continue-button > button");
	
	//Fields of 1st step
	public static final cselector emailAddress = new cselector("css, #logonId","css,#field-5");
	public static final cselector confirmEmailAddress = new cselector("css,#verifyLogonId","css,#field-6");
	public static final cselector password =  new cselector("css,#logonPassword","css,#field-7");
	public static final cselector confirmPassword = new cselector ("css,#logonPasswordVerify","css,#field-8");
	
	//Fields of 2nd step
	public static final cselector firstName =new cselector ("css,#bill_fnbox","css,#field-10");
	public static final cselector lastName =new cselector ( "css,#bill_lnbox","css,#field-12");
	public static final cselector companyName=new cselector ("css,#bill_cnbox","css,#field-13");
	public static final cselector AddressLine1 = new cselector ("css,#bill_sa1box", "css,#field-14");
	public static final cselector city = new cselector ("css,#bill_citybox","css,#field-16");
	public static final cselector state = new cselector("css,#bill_region","css,#field-17");
	public static final cselector Zipcode = new cselector("css,#bill_zipbox","css,#field-18");
	public static final cselector phone = new cselector("css,#bill_phone1box", "css,#field-19");
	public static final cselector phoneGH = new cselector("css,#bill_phone1box", "css,#field-20");
	
	//Registration success verification
	public static final cselector welcomeMessage = new cselector("css,div.data:nth-child(3)","css,#app-main > div > div.u-padding-md.u-padding-bottom-xsm > p");
	public static final cselector welcomeMessageGH = new cselector("css,div.data:nth-child(3)","css,.t-my-account__signout");
	public static final cselector welcomeMessageRY = new cselector("css,div.data:nth-child(3)","css,.t-my-account-accordion__header");

	//Error messages  
	
	//Mobile
	public static final String emailAddressErrorMobile ="css,.c-custom-input-error";
	public static final String confEmailAddressErrorMobile = "css,.c-custom-input-error";
	public static final String passwordRulesErrorMobile = "css,.c-custom-input-error";
	public static final String confirmPasswordErrorMobile = "css,.c-custom-input-error"; 
	
	public static final String firstNameErrorMobile ="css,.c-custom-input-error";
	public static final String lastNameErrorMobile ="css,.c-custom-input-error";
	public static final String streetAddreesErrorMobile ="css,.c-custom-input-error";
	public static final String cityErrorMobile ="css,.c-custom-input-error";
	public static final String stateErrorMobile ="css,#register-form > div:nth-child(1) > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div.pw-field__error";
	public static final String ZIPCodeErrorMobile ="css,.c-custom-input-error";
	public static final String phoneErrorMobile ="css,.c-custom-input-error";
	
	//Desktop
	public static final String emailAddressError =  "css,#error-div-logonId";	
	public static final String confEmailAddressError = "css,#error-div-verifyLogonId";
	public static final String passwordRulesError = "css,#error-div-logonPassword";
	public static final String confirmPasswordError = "css,#error-div-logonPasswordVerify"; 
	
	public static final String firstNameError = "css,#error-div-bill_fnbox";
	public static final String lastNameError = "css,#error-div-bill_lnbox";
	public static final String streetAddreesError = "css,#error-div-bill_sa1box";
	public static final String cityError = "css,#error-div-bill_citybox";
	public static final String stateError ="css,#error-div-bill_region";
	public static final String ZIPCodeError = "css,#error-div-bill_zipbox";
	public static final String phoneError = "css,#error-div-bill_phone1box";
	

	
}
