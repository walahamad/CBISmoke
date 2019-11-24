package com.generic.selector;

import com.generic.setup.cselector;

public class HomePageSelectors
{

  public static final cselector yamalCarousels = new cselector("body gray-disabled-arrows" ,"css,#gwt_recommendations_home_1 > div > div > div > div.body > div > div","css,#home_rr_PWA > div > div > div > div > div > div");
	public static final cselector logo = new cselector("css,#logo1 a.logo-anchor","t-header__logo");
	public static final cselector miniCartBtn = new cselector("css,#gwt_minicart_div>table>tbody>tr>td>div","css,#cartAnchor","css,.pw-header-bar__actions.t-header-bar__cart > button");
	public static final cselector miniCartText = new cselector("empty-cart-label","css,.empty-cart-sign-in-container > .gwt-HTML.inst-copy","css,.m-mini-cart__empty-content.u-text-align-center>h2");
	public static final cselector miniCartClose = new cselector("css,.pw-button__inner>#nav-icon");
	public static final cselector miniCartProductContainer = new cselector("mini-cart-items-container","m-mini-cart__content-products");
	public static final cselector miniCartCheckoutBtn = new cselector("css,.mini-cart-product-panel-btn","pw-button pw--primary u-width-full");		
	//public static final cselector miniCartCheckoutBtn = new cselector("css,.mini-cart-product-panel-btn-img.checkout-version2","pw-link mini-cart__link pw-button pw--primary u-width-full u-text-weight-medium");		

	//public static final cselector miniCartMobd = new cselector("css,#cartAnchor","css,.pw-header-bar__actions.t-header-bar__cart > button");
	

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
