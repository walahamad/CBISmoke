package com.generic.selector;

import com.generic.setup.cselector;

public class HomePageSelectors
{
	public static final cselector logo = new cselector("css,#logo1 a.logo-anchor","css,#headerBox > div.u-flexbox.u-width-full.t-header-bar__title > div > a");
	
	//public static final String logo = "css,#logo1 a.logo-anchor";
	
	public static final String header = "header-container";
	public static final String body_topNavLinks = "css,#main-navigation-large>.container>.nav-links";
	public static final String body_categorySlider = "page-section category-slider";
	public static final String body_valuepropPromo = "four-desc page-section ocm-valueprop-promo";
	public static final String body_navTabsContainer = "nav-tabs-container";
	public static final String body = "homepage-value-packs";
	public static final String footerBottomSection = "footer-bottom-section";
	public static final String footerTopSection = "footer-top-section";
	public static final String footerLogo = "footer-ocm-logo";
	public static final String footerEmailSignUp = "footer-email-sign-up";

	
}
