package jg.factory;

import com.microsoft.playwright.Locator;

import jg.model.Subcategory;

public class SubcategoryFactory {
	
	public static Subcategory createSubcategory(Locator locator) {
		return new Subcategory(locator.textContent(), 
				Integer.valueOf(locator.getAttribute("data-depth")), 
				locator.locator("a").getAttribute("href"));
	}

}
