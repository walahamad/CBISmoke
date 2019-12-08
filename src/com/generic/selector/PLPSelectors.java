package com.generic.selector;

import com.generic.setup.cselector;

public class PLPSelectors {

	//CBI
	public static final cselector SearchIcon = new cselector("css,#uNavTop button.searchboxAsButton", "css,.t-header__fades-in>button");
	public static final cselector searchBox = new cselector("css,#searchBox input#headerBox","css,form>div>div>input");
	public static final cselector recommendedOption =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid a.unbxd-as-popular-product-info","css,.c--align-middle a.pw-link.c-arrange.u-flexbox.c--align-middle.u-align-center");
	public static final cselector SearchButton =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid a.unbxd-as-popular-product-info");
    public static final cselector product =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,div.pw-list-tile__content  a.c-product-item__main-image"); 
    public static final cselector productName =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,a.pw-link.c-product-item-title-link > div > div > h2 > div"); 
    public static final cselector productContainer =  new cselector("css,div.unbxd-products-display-panel","css,footer > div.c-custom-accordion"); 

	//OLD
	public static final String sortingDropDown = "sortOptions1";
	public static final String sortingDropDownMobile = "sort by";
	public static final String sortPHTL = "css,div.custom-dropdown-content.sort-dropdown-content>button";
	public static final String sortPHTLMobile = "Price: High to Low";
	public static final String sortPLTH = "css,div.custom-dropdown-content.sort-dropdown-content>button";
	public static final String sortPLTHMobile = "css,div#PLPSortByModal>div>div>div.modal-body>ul>li";
	public static final String productsPrices = "actual-price";
	public static final String productNmber = "pagination-bar-results";
	public static final String SearchBox = "js-site-search-input";
	public static final String randomProduct = "product__list--name";
	public static final String productsSalePrices = "product-sales-price";
	public static final String sortOptions1 = "sortOptions1";
	public static final String sortOptions2 = "sortOptions2";
	public static final String numberOfProductsFound = "test_searchResults_productsFound_label_$";
	public static final String productItem = "product-item";
	public static final String shopByStore = "Shop by Stores";
	public static final String userLocationStore = "user_location_query";
	public static final String findStoresNearMeAjax = "findStoresNearMeAjax";
	public static final String addToCartForm = "addToCartForm";
	public static final String productPickupInStoreButton = "product_";
	public static final String moreStores = "js-more-stores-facet-values";
	public static final String plpProductPriceLabel = "test_searchPage_price_label_{0}_$";
	public static final String addToCartItemPriceParent = "add-to-cart-item";
	public static final String addToCartItemPrice = "price";
	public static final String addToCartItemCheckoutBtn = "test_checkoutLinkInPopup_$";
	public static final String addToCartItemContinueShoppingBtn = "Continue Shopping";
	public static final String cboxCloseBtn = "cboxClose";
	public static final String facetNavTitleStores = "test_facetNav_title_Stores_$";
	public static final String facetNavTitlePrice = "test_facetNav_title_Price_$";
	public static final String facetNavTitleColour = "test_facetNav_title_Colour_$";
	public static final String facetNavTitleSize = "test_facetNav_title_Size_$";
	public static final String facetNavSecondCount = "test_facetNav_count_$2";
	public static final String facetNavThirdCount = "test_facetNav_count_$3";
	public static final String changeLocationLink = "js-facet-change-link";
//	public static final String locationFacetContainer = "facet__results js-facet-container";
	public static final String locationFacetContainer = "Change Location";
	public static final String nthProductItem = ".product-item:eq({0})";
	public static final String nthAppliedFacets = ".facet.js-facet .facet__list li a:eq({0})";
	public static final String pickupInStoreLocationSearch = "locationForSearch";
	public static final String pickupNthAccessibleTabIcon = "accessibletabsnavigation0-{0}";
	public static final String pickupInStoreList = "pickup-store-list-entry";
	public static final String pickupInStoreProduct = "pickupModal_product_{0}";
	public static final String pickupAddToBagBtn = "js-add-to-cart-for-pickup-popup";
	public static final String pickupDecreaseQtyBtn = "js-qty-selector-minus";
	public static final String pickupIncreaseQtyBtn = "js-qty-selector-plus";
	public static final String pickupQtyInput = "js-qty-selector-input";
	public static final String facetNavFirstPrice = "css,#test_facetNav_title_Price_$>div>div>ul>li>div>form>label>.facet__list__checkbox.js-facet-checkbox.sr-only";


}
