package com.generic.selector;

import com.generic.setup.cselector;

public class RegistrationSelectors {
	//Done CBI Smoke
	//Navigation
	public static final cselector closeOfferButtonGH = new cselector("css,#Close1-Item4 .offer-control");
	public static final cselector registrationButton = new cselector("css, div.spot:nth-child(3) > button:nth-child(1)");
	public static final cselector registrationButtonRY = new cselector("css,.registration a","css,.t-registration-panel__action .pw-button__inner");
	public static final cselector mobileRegistrationButton= new cselector("css,button.pw-button:nth-child(2)");
	public static final cselector mobileRegistrationButtonGH= new cselector("css,.t-registration-panel__button");
	public static final cselector mobileRegistrationTab =new cselector("css,#app-main > div > div > div > div.pw-tabs__strip-container > ol > li.pw-tabs__tab.pw--is-active > a");
	public static final cselector mobileRegistrationTabGH =new cselector("css,.pw-tabs__link");				
	public static final cselector registerBtn = new cselector("css,#continue");
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
	public static final cselector emailAddressErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector confEmailAddressErrorMobile = new cselector("css,.c-custom-input-error");
	public static final cselector passwordRulesErrorMobile = new cselector("css,.c-custom-input-error");
	public static final cselector confirmPasswordErrorMobile = new cselector("css,.c-custom-input-error"); 
	
	public static final cselector firstNameErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector lastNameErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector streetAddreesErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector cityErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector stateErrorMobile =new cselector("css,#register-form > div:nth-child(1) > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div.pw-field__error");
	public static final cselector ZIPCodeErrorMobile =new cselector("css,.c-custom-input-error");
	public static final cselector phoneErrorMobile =new cselector("css,.c-custom-input-error");
	
	//Desktop
	public static final cselector emailAddressError = new cselector( "css,#error-div-logonId");	
	public static final cselector confEmailAddressError = new cselector("css,#error-div-verifyLogonId");
	public static final cselector passwordRulesError = new cselector("css,#error-div-logonPassword");
	public static final cselector confirmPasswordError = new cselector("css,#error-div-logonPasswordVerify"); 
	
	public static final cselector firstNameError = new cselector("css,#error-div-bill_fnbox");
	public static final cselector lastNameError = new cselector("css,#error-div-bill_lnbox");
	public static final cselector streetAddreesError =new cselector( "css,#error-div-bill_sa1box");
	public static final cselector cityError = new cselector("css,#error-div-bill_citybox");
	public static final cselector stateError =new cselector("css,#error-div-bill_region");
	public static final cselector ZIPCodeError = new cselector("css,#error-div-bill_zipbox");
	public static final cselector phoneError = new cselector("css,#error-div-bill_phone1box");
	

	
}
