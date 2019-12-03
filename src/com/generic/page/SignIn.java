package com.generic.page;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.generic.selector.SignInSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;
import com.generic.setup.GlobalVariables;

public class SignIn extends SelTestCase {

	public static String logoffhref = "/Logoff";
	public static String myAccountPageLink = "AccountOverView";

	// done-ocm
	public static void logIn(String userName, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			fillLoginFormAndClickSubmit(userName, Password);
			Thread.sleep(1000);
			if (!checkUserAccount()) {
				throw new Exception("Login failed");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Fill the login form and click submit button.
	*
	* @param email
	* @param Password
	* @throws Exception
	*/
	public static void fillLoginFormAndClickSubmit(String email, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,
					"Navigating to registration page..." + getCONFIG().getProperty("RegistrationPage")));
			getDriver().get(new URI(getDriver().getCurrentUrl()).resolve(getCONFIG().getProperty("RegistrationPage"))
					.toString());
			// clickOnMainMenue();
			typeEmail(email);
			typePassword(Password);
			clickLogin();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Click lSign in button.
	*
	* @throws Exception
	*/
	public static void clickLogin() throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the sign in button and Navigate to the Sign in/Create account page..
			SelectorUtil.initializeSelectorsAndDoActions(SignInSelectors.signInButton.get());
			SelectorUtil.waitingLoadingButton(SignInSelectors.loadingButton);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	/**
	* Type user password.
	*
	* @param Password
	* @throws Exception
	*/
	public static void typePassword(String password) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the password input and Enter the password.
			SelectorUtil.initializeSelectorsAndDoActions(SignInSelectors.signInEmailPasswordInput.get(), password);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Enter user password.
	*
	* @param email
	* @throws Exception
	*/
	public static void typeEmail(String email) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the email input and Enter the email.
			SelectorUtil.initializeSelectorsAndDoActions(SignInSelectors.signInEmailInput.get(), email);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Get Email error message.
	*
	* @throws Exception
	*/
	public static String getMailErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		try {
			String errorMessage = SelectorUtil.getelement(SignInSelectors.emailError.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Get login error message.
	*
	* @throws Exception
	*/
	public static String getErrologinMessage() throws Exception {
		getCurrentFunctionName(true);
		try {
			Thread.sleep(1000);
			String errorMessage = SelectorUtil.getelement(SignInSelectors.errorMessage.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getEmailErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		try {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.emailError.get());
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
		} catch (Exception e) {
			// to make sure the application is throwing the correct exception
			if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
				throw new Exception(ExceptionMsg.noErrorMsg);
			else
				throw new Exception(e);
		}
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	/**
	* Get password error message.
	*
	* @throws Exception
	*/
	public static String getPasswrdErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		try {
			String errorMessage = SelectorUtil.getelement(SignInSelectors.passwordError.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Check user account is logged in.
	*
	* @throws Exception
	*/
	public static boolean checkUserAccount() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isUserLogedIn = false;

			// Check if the device is mobile(PWA site) or (desktop, tablet).
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);

			// Validate the welcome message if it is exist.
			if (isPWAMobile) {
				WebElement welcomeMessageElement = SelectorUtil.getMenuLinkMobilePWA(logoffhref);
				String itemHref = welcomeMessageElement.getAttribute("href");
				if (itemHref.contains(logoffhref)) {
					isUserLogedIn = true;
				}
			} else {
				WebElement welcomeMessage = SelectorUtil.getelement(SignInSelectors.welcomeMessage.get());
				logs.debug("welcomeMessage: " + welcomeMessage.getAttribute("innerText").trim());
				if (welcomeMessage.getAttribute("innerText").trim().toLowerCase().contains("welcome")) {
					isUserLogedIn = true;
				}
			}

			getCurrentFunctionName(false);

			return isUserLogedIn;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Check user account link.
	* @param boolean
	* @throws Exception
	*/
	public static boolean checkExistenceOfAccountLink() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isUserLogedIn = false;

			// Check if the device is mobile(PWA site) or (desktop, tablet).
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
			WebElement myAccountLink;

			// Get my account link.
			if (isPWAMobile) {
				myAccountLink = SelectorUtil.getMenuLinkMobilePWA(myAccountPageLink);
			} else {
				myAccountLink = SelectorUtil.getelement(SignInSelectors.myAccountLink);
			}

			// Check if link is for my account page.
			String itemHref = myAccountLink.getAttribute("href");
			if (itemHref.contains(myAccountPageLink)) {
				isUserLogedIn = true;
			}

			// Go to my account page.
			SelectorUtil.openMobileAccountMenu();
			SelectorUtil.clickOnWebElement(myAccountLink);

			getCurrentFunctionName(false);

			return isUserLogedIn;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	* Check current page if it is my account.
	*
	* @return boolean
	* @throws Exception
	*/
	public static boolean checkMyAccountPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isMyAccountPage = false;

			String currentPageUrl = SelectorUtil.getCurrentPageUrl();

			// Validate the current page is my account page by URL.
			if (currentPageUrl.contains(myAccountPageLink)) {
				isMyAccountPage = true;
			}

			getCurrentFunctionName(false);

			return isMyAccountPage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}


	/**
	* Click on main menu.
	*
	* @throws Exception
	*/
	private static void clickOnMainMenue() throws Exception {
		try {
			WebElement signInLink;

			// Check if the device is mobile(PWA site) or (desktop, tablet).
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);

			if (isPWAMobile) {
				// Validate the sign in for Mobile (PWA site).
				logs.debug("Validate Sign in for Mobile (PWA site)");

				// Get the sign in link or welcome message from the account menu.
				signInLink = SelectorUtil.getMenuLinkMobilePWA("UserLogonView");
			} else {
				// Validate the desktop and tablet sign in form.
				logs.debug("Validate Sign in desktop or tablet");

				// Get the sign in link or welcome.
				signInLink = SelectorUtil.getelement(SignInSelectors.signInNavigation.get());
			}

			// Navigate to the Sign in/Create account page.
			SelectorUtil.clickOnWebElement(signInLink);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void clickForgotPasswordBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.forgotPasswordLnk);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void typeForgottenPwdEmail(String email) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.forgottenPwdInput);
			valuesArr.add(email);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void clickForgotPasswordSubmitBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.forgotPasswordSubmitBtn);
			valuesArr.add("");
			SelectorUtil.getNthElement(subStrArr, 1).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getAlertPositiveForgottenPasswordd() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.alertPositiveForgottenPassword);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getForgottenPwdEmailError() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(SignInSelectors.forgottenPwdEmailError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static void registerNewUser(String userMail, String userPassword) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Run the registration test case before sign in.
			Registration.freshUserValidate(userMail, userPassword);
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
			if (isPWAMobile) {
				WebElement logoffLink = SelectorUtil.getMenuLinkMobilePWA(logoffhref);
				SelectorUtil.clickOnWebElement(logoffLink);
				Thread.sleep(1500);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(SignInSelectors.logoffLink);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
}
