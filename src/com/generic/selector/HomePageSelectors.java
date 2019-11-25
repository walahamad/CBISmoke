package com.generic.selector;

import com.generic.setup.cselector;

public class HomePageSelectors
{
	public static final cselector logo = new cselector("css,#logo1 a.logo-anchor","t-header__logo");
	public static final cselector miniCartBtn = new cselector("css,#gwt_minicart_div>table>tbody>tr>td>div","css,#cartAnchor","css,.pw-header-bar__actions.t-header-bar__cart > button");
	public static final cselector miniCartText = new cselector("empty-cart-label","css,.empty-cart-sign-in-container > .gwt-HTML.inst-copy","css,.m-mini-cart__empty-content.u-text-align-center>h2");
	public static final cselector miniCartClose = new cselector("css,.pw-button__inner>#nav-icon");
	public static final cselector miniCartProductContainer = new cselector("mini-cart-items-container","m-mini-cart__content-products");
	public static final cselector miniCartCheckoutBtn = new cselector("css,.mini-cart-product-panel-btn","pw-button pw--primary u-width-full");		
	public static final cselector searchIconOpen = new cselector("css,#uNavTop >.searchboxAsButton","css,.t-header__search");
	public static final cselector searchIconClose = new cselector("css,#search-popup > .search-popup-close-icon","css,.left-container>.leftSideNav>button","css,.c-search-action__close-button");
	public static final cselector searchIconField = new cselector("css,#searchBox>#headerBox","css,#search-0");
	public static final String moduleHeroImg = "css,#homepage-head div.row div.genericESpot div.module-hero.full-width div.col-xs-12 div.wrapper a img.img.img-responsive";
	public static final cselector carusals =new cselector( "css,.tilePanel>div>div>a>img","css,.genericESpot>div>div>div>.pw-scroller>div>.pw-scroller__content>.pw-scroller__item>article>a>div>div>div>div>div>img");
	public static final cselector espots = new cselector("css,.genericESpot>div>div>div>a>img","css,.pw-responsive-html>div>#mainContent>div>div>div>.genericESpot>div>.col-xs-12>div>a>img");
	public static final String HomePageSearchbutton = "toggle-mobile-menu";
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
