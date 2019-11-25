package com.generic.selector;

import com.generic.setup.cselector;

public class RegistrationSelectors {
	//Done CBI Smoke
	//Navigation
	public static final String registrationButton = "css, div.spot:nth-child(3) > button:nth-child(1)";
	public static final String mobileRegistrationButton= "css,button.pw-button:nth-child(2)";
	public static final String mobileRegistrationTab ="css,#app-main > div > div > div > div.pw-tabs__strip-container > ol > li.pw-tabs__tab.pw--is-active > a";
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
	
	//Registration success verification
	public static final cselector welcomeMessage = new cselector("css,div.data:nth-child(3)","css,#app-main > div > div.u-padding-md.u-padding-bottom-xsm > p");
	
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
	
	

	
	
	//done OCM
	public static final String school  = "school-search-input";
	public static final String terms = "termsAndCondition";
	public static final String addressSection = "css,h3>button.btn-show-hide";
	public static String userType = "userType";
	public static final String phoneType = "cellPhone";		
	public static final String alerts = "global-alerts";
	
	//ErrorMessages Selectors
	public static final String emailError = "email.errors";
	public static final String schoolError = "school-search-input-error"; 
	public static final String temsError = "termsAndCondition-error";
	
	
	//pending OCM
	public static final String passwordError = "password_minchar";
	public static final String passwordMatchError = "checkPwd.errors";
	public static final String title = "register.title";
	public static final String consentGiven = "consentForm.consentGiven1";
	public static final String register = "btn btn-default btn-block";
	public static final String registrationSuccess = "global-alerts";
	public static final String RegisterNewUserBtn = "Create Account";
	public static final String emailAddressConfirmation = "dwfrm_profile_customer_emailconfirm";
	public static final String birthdayMonth  = "dwfrm_profile_customer_month";
	public static final String birthdayDay = "dwfrm_profile_customer_day";
	
	//ErrorMessages Selectors
	public static final String titleError = "titleCode.errors";
	
}
