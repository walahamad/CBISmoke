package com.generic.selector;


public class HomePageSelectors
{
	// Header selectors.
	public static final String logo = "css,#logo1 a.logo-anchor";
	public static final String menuItems = "css,#css-top-navigation #flyout>.menuItem>a";
	public static final String currentbreadcrumbs = "css,#currentbreadcrumbs";
	public static final String sideBoxHeader = "css,#sideBoxHeader";
	
	// Header table selectors.
	public static final String navigationIcon = "css,.nav-toggle";
	public static final String menuItemsTablet = "css,#gwt-top-navigation .active>.gwt-nav-item-has-submenu";
	public static final String selectedMenuHeader = "css,#gwt-top-navigation .active>.gwt-nav-item-has-submenu.opened";
	public static final String leafMenuItems = "css,.active-menu-item>:not(.gwt-nav-item-has-submenu)";
	
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
