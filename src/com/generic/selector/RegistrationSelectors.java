package com.generic.selector;

import com.generic.setup.cselector;

public class RegistrationSelectors {
	//Done CBI Smoke
	//Navigation
	public static final String registrationButton = "css, div.spot:nth-child(3) > button:nth-child(1)";
	public static final String mobileRegistrationButton= "css,button.pw-button:nth-child(2)";
	public static final String mobileRegistrationTab ="css,#app-main > div > div > div > div.pw-tabs__strip-container > ol > li.pw-tabs__tab.pw--is-active > a";
	public static final String registerBtn = "css,#continue";
	public static final String saveButton ="css,#gwt_billshipaddr_btn > button";
	
	//Fields of 1st step
	public static final cselector emailAddress = new cselector("css, #logonId","css,#field-5");
	public static final cselector confirmEmailAddress = new cselector("css,#verifyLogonId","css,#field-6");
	public static final cselector password =  new cselector("css,#logonPassword","css,#field-7");
	public static final cselector confirmPassword = new cselector ("css,#logonPasswordVerify","css,#field-8");
	
	//Fields of 2nd step
	public static final String firstName = "css,#bill_fnbox";
	public static final String lastName = "css,#bill_lnbox";
	public static final String companyName="css,#bill_cnbox";
	public static final String AddressLine1 = "css,#bill_sa1box";
	public static final String city = "css,#bill_citybox";
	public static final String state = "css,#bill_region";
	public static final String Zipcode = "css,#bill_zipbox";
	public static final String phone = "css,#bill_phone1box";
	
	//Registration sucess verfication
	public static final String welcomeMessage = "css,div.data:nth-child(3)";
	
	//Error messages  
	public static final String emailAddressErrorMobile ="css,.c-custom-input-error";
	public static final String emailAddressError =  "css,#error-div-logonId";
	public static final String confEmailAddressErrorMobile = "css,.c-custom-input-error";
	public static final String confEmailAddressError = "css,#error-div-verifyLogonId";
	public static final String passwordRulesErrorMobile = "css,.c-custom-input-error";
	public static final String passwordRulesError = "css,#error-div-logonPassword";
	public static final String confirmPasswordErrorMobile = "css,.c-custom-input-error"; 
	public static final String confirmPasswordError = "css,#error-div-logonPasswordVerify"; 
	
	//done OCM
	public static final String school  = "school-search-input";
	public static final String terms = "termsAndCondition";
	public static final String addressSection = "css,h3>button.btn-show-hide";
	public static String userType = "userType";
	public static final String phoneType = "cellPhone";		
	public static final String alerts = "global-alerts";
	
	//ErrorMessages Selectors
	public static final String emailError = "email.errors";
	public static final String firstNameError = "register.firstName-error";
	public static final String lastNameError = "register.lastName-error";

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
