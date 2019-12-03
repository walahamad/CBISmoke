package com.generic.selector;

import com.generic.setup.cselector;

public class SignInSelectors
{
	//done
	public static final String userName = "j_username";
	public static final String  password = "j_password";
	public static final cselector emailError = new cselector("css,#userLogonForm #error-div-logonId","css,#signin-form .pw-field-row:not(.t-login__password):not(.t-login__submit-button):not(.t-login__remember-submit) .c-custom-input-error");
	public static final cselector passwordError = new cselector("css,#userLogonForm #error-div-logonPassword","css,#signin-form .t-login__password .c-custom-input-error");
	public static final cselector errorMessage = new cselector("css,#gwt-error-placement-div","css,.t-login__form>.u-color-error");
	public static final String WelcomeMsg = "welcome-text";
	public static final String WelcomeMsgMobile = "mobile-user";
	public static final String forgotPasswordLnk = "login-forgot-password-link";
	public static final String forgottenPwdInput = "forgottenPwd.email";
	public static final String forgotPasswordSubmitBtn = "Reset Password";
	public static final String alertPositiveForgottenPassword = "success-password-text";
	public static final String forgottenPwdEmailError = "forgottenPwd.email-error";
	public static final String mainMenuebutton = "toggle-mobile-menu";
	public static final String loginBtn = "css,div.login-section>form>div.form-group>button";

	// Sign in selectors.
	public static final cselector signInNavigation = new cselector("css,#login a","css,.my-account-controls-modal_class .pw-link");
	public static final cselector signInEmailInput = new cselector("css,[name=logonId]", "css,[name=logonId].c-custom-input");
	public static final cselector signInEmailPasswordInput = new cselector("css,[name=logonPassword]", "css,[name=logonPassword].c-custom-input");
	public static final cselector signInButton = new cselector("css,#logonButton", "css, .t-login__submit-button .pw-button");
	public static final cselector welcomeMessage = new cselector("css,#welcome");
	public static final cselector accountMenuIcon = new cselector("css,#gwt_dropdownmenu_my_account", "css,#pwa-my-account-button .pw-button");
	public static final String accountMenuList = "css,.my-account-controls-modal_class .pw-link";
	public static final String loadingButton = "css,.loading-button";
	public static final String logoffLink = "css,[href*=Logoff]";

	public static final String myAccountLink = "css,#myAccount a";
	public static final String wrongPassword_mail = "css,div>div>form>div.error-form";
	public static final String myAccountModal = "css,.m-my-account-controls-modal";
}
