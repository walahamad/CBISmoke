package com.generic.selector;

import com.generic.setup.cselector;

public class HomePageSelectors
{
	public static final cselector logo = new cselector("css,#logo1 a.logo-anchor","css,#headerBox > div.u-flexbox.u-width-full.t-header-bar__title > div > a");
	
	// Header navigation menu selectors.
	public static final cselector navigationIcon = new cselector("","css,.nav-toggle", "css,.t-header__menu-icon .pw-button");
	public static final cselector menuItems = new cselector("css,#css-top-navigation #flyout>.menuItem>a","css,#gwt-top-navigation .active>.gwt-nav-item-has-submenu", "css,.navigation-modal_class .pw-link");
	public static final cselector selectedMenuHeader = new cselector("", "css,#gwt-top-navigation .active>.gwt-nav-item-has-submenu.opened", "css,.m-navigation__fades-in .pw-header-bar .c-sheet__title");
	public static final cselector leafMenuItems = new cselector("", "css,.active-menu-item>:not(.gwt-nav-item-has-submenu)", "css,.navigation-modal_class .pw-list-tile:not(.pw--has-child) .pw-link");
	public static final String sideBoxHeader = "css,#sideBoxHeader";

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
