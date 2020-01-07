package com.generic.selector;

import com.generic.setup.cselector;

public class PLPSelectors {

	//CBI
	public static final cselector SearchIcon = new cselector("css,#uNavTop button.searchboxAsButton", "css,.t-header__fades-in>button");
	public static final cselector RYSearchIcon = new cselector("css,.t-header__search-button>button");
	
	public static final cselector searchBox = new cselector("css,#searchBox input#headerBox","css,form>div>div>input");
	public static final cselector recommendedOption =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid .unbxd-as-popular-product-info","css,.c-arrange.u-flexbox.c--align-middle.u-align-center");
	public static final cselector SearchButton =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid a.unbxd-as-popular-product-info");
    public static final cselector product =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,div.pw-list-tile__content  a.c-product-item__main-image"); 
    public static final cselector productName =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,a.pw-link.c-product-item-title-link > div > div > h2 > div"); 
    public static final cselector productContainer =  new cselector("css,div.unbxd-products-display-panel","css,footer > div.c-custom-accordion"); 
    public static final cselector productsImages = new cselector("css,div.iwc-main-img-wrapper img.iwc-main-img", "css,article > a > div > img");
    public static final cselector productsImagesGR = new cselector("css,.unbxd-grid-product-image", "css,article > a > div > img");
    public static final cselector productsNames = new cselector("css,a.gwt-sub-category-info-panel-link","css,a.pw-link.c-product-item-title-link > div > div > h2 > div > div");
    public static final cselector GHproductsImages = new cselector("css,.gwt-product-info-panel-image-container>a>.unbxd-grid-product-image");

    public static final cselector PriceLowToHigh = new cselector("Price: Low to High","css,#unbxd_normal_sort_price-asc > div");
    public static final cselector PriceHighToLow = new cselector("Price: High to Low","css,#unbxd_normal_sort_price-desc > div");
   
    public static final cselector PriceLowToHighPLP = new cselector("Price: Low to High","css,#unbxd_prime_sort_price-asc > div");
    public static final cselector PriceHighToLowPLP = new cselector("Price: High to Low","css,#unbxd_prime_sort_price-desc > div");

    public static final cselector FilterContainer = new cselector("css,div.text-facets-container-panel div.unbxd-all-options-container span.gwt-InlineLabel.selected-option-span","css,div.c-product-list__container-filter-option.u-flex-1.u-margin-end > div > button > div");
	public static final cselector FilterContainerContents =  new cselector("css,div.unbxd-facet-option.text-facet span.unbxd-facet-option-checkbox input","css,.u-color-neutral-70.u-text-uppercase.u-text-align-center.u-letter-spacing-3");
	public static final cselector mobileSortingMenu = new cselector("","css,.c-product-list__filters-sort-button");
	public static final cselector allCatigories = new cselector("","css,div.c-custom-accordion__list-of-items-item>div>div>div>input");
	public static final cselector allCatigories2 = new cselector("","css,.pw-accordion__content .pw-link");	
	public static final cselector filterPrice = new cselector("","css,.c-price-range__max-price input");	

	
	
	
	public static final cselector GRMobileSorting = new cselector("css,.c-product-list__container-sort-option.u-flex-1 > div > div > div > select");
	public static final cselector GRDeskTopSorting = new cselector("css,.unbxd-all-sort-options .selected-option-span");
	public static final cselector GRDeskTopSortingLowtoHIgh = new cselector("css,#unbxd-sort-options-container > div > div > a:nth-child(2)");
	public static final cselector GRDeskTopSortingHighToLow = new cselector("css,#unbxd-sort-options-container > div > div > a:nth-child(3)");


	public static final cselector GRFilterContainer = new cselector("css,#text-facets-container-panel > div:nth-child(1) > span","css,div.pw-accordion__close-icon");
	public static final cselector GRFilterContainerContents = new cselector("css,.unbxd-facet-option-checkbox input","css,.u-text-letter-spacing-0 > div");
	public static final cselector GRallCatigories = new cselector("","css,.pw-field__label-wrap.pw--end > label > div > div > div");

	
	
	//Random PLP 
	public static final cselector desktopMenuItem = new cselector("css,a.gwt-MenuItem");
	public static final cselector PLPIdentifier = new cselector("css,.product-details-main-container","css,.c-product-item__price");
	
	public static final cselector navigatetoPLP = new cselector(
			"css,#mainContent > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#mainContent > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#app-main > div > div:nth-child(1) > div.u-flexbox.u-direction-row.u-flex-wrap > div:nth-child(1) > a > div.c-lazy-image.u-width-full > div > img");
	
	public static final cselector navigatetoPLP2 = new cselector(
			"css,#mainContent > div.genericESpot > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#mainContent > div.genericESpot > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#app-main > div > div:nth-child(1) > div.u-flexbox.u-direction-row.u-flex-wrap > div:nth-child(1) > a > div.c-lazy-image.u-width-full > div > img");
	
	
	public static final cselector navigatetoPLPGR = new cselector(
			"css,#mainContent > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#mainContent > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#app-main > div > div:nth-child(3) > div:nth-child(1) > a > div.c-lazy-image.u-width-full > div > img");
	
	public static final cselector navigatetoPLP2GR = new cselector(
			"css,#mainContent > div.genericESpot > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#mainContent > div.genericESpot > div.container.no-padding.grid-subcategories > div > div:nth-child(1) > div > a > img",
			"css,#app-main > div > div:nth-child(1) > div.u-flexbox.u-direction-row.u-flex-wrap > div:nth-child(1) > a > div.c-lazy-image.u-width-full > div > img");
	
	
	
}
