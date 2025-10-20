package jg.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;

import jg.factory.BrowserFactory;
import jg.factory.SubcategoryFactory;
import jg.model.ProductDetails;
import jg.model.Subcategory;
import jg.pageElements.HtmlTag;
import jg.pageElements.PrestaPageElements;

public class Application {
	
	private static final String MOTOREX_MAIN_CATEGORY = "#category-11";
	private static final String MOTOREX_COLLAPSING_NAVBAR = "#exCollapsingNavbar11";
	
	private static final String SCOTT_MAIN_CATEGORY = "#category-16";
	private static final String SCOTT_COLLAPSING_NAVBAR = "#exCollapsingNavbar16";
	
	private static final String[] GROUPS = {"#group_1","#group_5","#group_6"};
	private static final String QUANTITY_REGEX = ",&quot;quantity&quot;:([0-9]+),";
	private static final String EAN_REGEX = ",&quot;ean13&quot;:&quot;(\\d+)&quot;,";
	private static final String REFERENCE_REGEX = ",&quot;attributes&quot;:[^}]*?&quot;reference&quot;:&quot;([^&quot;]*)&quot;";
	
	public static void main(String[] args) {
		
		try(Playwright playwright = Playwright.create()){	
			Browser browser = BrowserFactory.createBrowser(playwright);	
			Browser.NewContextOptions contextOptions = BrowserFactory.createBrowserOptions();
			BrowserContext context = BrowserFactory.createBrowserContext(browser,contextOptions);
			
			Page page = context.newPage();
			
			rollThroughWebsites(page);	
			clickButtonByText(page, "#c-p-bn", "Akceptuj wszystko");
			
			// WYEKSTRAHOWAC CATEGORY DO CONFIGU
			//hoverAndClickLocator(page.locator(MOTOREX_MAIN_CATEGORY));
			hoverAndClickLocator(page.locator(SCOTT_MAIN_CATEGORY));
			//
			// TO DO CONFIGU JEST TO KATEGORIA MENU Z GORY, MENU z LEWEJ
			Queue<Subcategory> subcategories = new LinkedList<>();
			System.out.println("Start iterate to get subcategories");
			iterateItems(page, SCOTT_COLLAPSING_NAVBAR, HtmlTag.LI.getTag(), item ->  {	
			//iterateItems(page, MOTOREX_COLLAPSING_NAVBAR,HtmlTag.LI.getTag(), item ->  {	
					if(isLocatorPointDeepestElement(item, HtmlTag.UL))
						subcategories.add(SubcategoryFactory.createSubcategory(item));					
			}
			,1);
			subcategories.forEach(System.out::println);
			humanDelay(3000, 3500);
			System.out.println("End getting subcategories.");
			int current = 1;
			int size = subcategories.size();
			
			//testClose(browser, context);
			
			
			
			
			while(!subcategories.isEmpty()) {
				System.out.println(String.format("%d / %d", current++, size));
				Subcategory subcategory = subcategories.poll();
				System.out.println("Get into : " + subcategory.getName());
								
				page.mouse().move(0, 800);
				humanDelay(750, 1000);
				page.mouse().move(0, -800);
				humanDelay(750, 1000);

				page.navigate(subcategory.getUrl());
				page.waitForLoadState(LoadState.NETWORKIDLE);	
				
				List<String> productsLinksToScrap = getProductsLinks(page);				
				//List<String> productsLinksToScrap = List.of("https://jgsportesklep.pl/motorex/1458-motorex-smar-grease-3000");				
				List<ProductDetails> data = new ArrayList<ProductDetails>();
				
				
				for(String url: productsLinksToScrap) {
					page.waitForLoadState(LoadState.NETWORKIDLE);					
					humanDelay(2000, 3500);
					scrapeProduct2(page, url, GROUPS);
					

				}
				//data.forEach(t -> System.out.println(t.toString()));
				data.clear();	
			}

			
			//zapisuje sesje do pliku przez co po ponownym wlaczeniu bedzie pamietało pliki cookie i sesje
			BrowserFactory.saveBrowserContext(context);
			//zwalnia zasoby
			context.close();
			browser.close();
		}
		
		
	}
	
	private static void scrapeProduct2(Page page,String link,String[] groups) {
		page.navigate(link);

		Locator select = page.locator("select");

		if(select.count() == 0) {
			System.out.println(page.url());
		    String productId = page.locator("input#product_page_product_id").getAttribute("value");
		    String token = page.locator("input[name='token']").getAttribute("value");
	        String xhrUrl = String.format(
            "https://jgsportesklep.pl/index.php?controller=product&token=%s&id_product=%s&id_customization=0&qty=1",
            token, productId
        );
        APIRequestContext requestContext = page.context().request();
        APIResponse response = requestContext.get(xhrUrl);
        String body = response.text();
        String details = body.substring(body.indexOf("data-product="));
        String quantity = getProductDetails(details, QUANTITY_REGEX);
        String reference = getProductDetails(details, REFERENCE_REGEX);
        String ean = getProductDetails(details, EAN_REGEX);        
        String title = page.locator("#main > div > div.col-xs-12.col-md-7 > div > h1").textContent();
        
        System.out.println(String.format("%s;%s;%s;%s;%s", title,page.url(),quantity,reference,ean));
			return ;

		}

		 for(String groupId: groups) {
			 select = page.locator(groupId);
			 if(select.isVisible()){
				 final Locator s = select;
			      Locator options = select.locator("option");
			      int optionCount = options.count();
			      for (int i = 0; i < optionCount; i++) {			    	  
			        String value = options.nth(i).getAttribute("value");
			        Response response = page.waitForResponse(
			                resp -> resp.url().contains("index.php?controller=product") && resp.request().method().equals("POST"),
			                () -> {

			                	s.selectOption(value);
			                	page.waitForTimeout(2000);
			                	
			                }
			            );
			        String body = response.text();
			        String details = body.substring(body.indexOf("data-product="));
			        String quantity = getProductDetails(details, QUANTITY_REGEX);
			        String reference = getProductDetails(details, REFERENCE_REGEX);
			        String ean = getProductDetails(details, EAN_REGEX);
			        String capacity = options.nth(i).innerText();
			        String title = page.locator("#main > div > div.col-xs-12.col-md-7 > div > h1").textContent();
			        
			        System.out.println(String.format("%s %s;%s;%s;%s;%s", title,capacity,page.url(),quantity,reference,ean));
			        
			        humanDelay(1500, 3500);
			        //page.waitForTimeout(3500);

			      }	
			      break;						

		 }
		 }
	}
	

	private static String getProductDetails(String productDetails,String regex){
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(productDetails);
        return matcher.find() ? matcher.group(1):"";
	}
	

	private static List<String> getProductsLinks(Page page) {
		List<String> productsUrlToScrap = new ArrayList<>();
		boolean isNextButtonExist = true;
		
		do {
			Locator nextPageButton = page.locator(PrestaPageElements.NEXT_PAGE_BUTTON);
			isNextButtonExist = nextPageButton.count() > 0;
			iterateItems(
				    page,
				    ".products",           
				    ".js-product.product",           
				    item -> {             
				    	Locator span = item.locator("h3.product-title span[itemprop='url']");
				    	span.waitFor();
				    	String url = span.getAttribute("content");
				    	productsUrlToScrap.add(url);
				    	System.out.println(url);
				    }
				    ,2
				);
																							
			
			if(isNextButtonExist) {
				humanDelay(3000, 3500);
				hoverAndClickLocator(nextPageButton);	
			}
		}while(isNextButtonExist);
		
		return productsUrlToScrap;
	}

	private static void rollThroughWebsites(Page page) {
		page.navigate("http://google.com");
		page.waitForTimeout(3000);
		page.navigate("http://wikipedia.com");
		page.waitForTimeout(3000);
		page.navigate("http://wp.pl");
		page.waitForTimeout(3000);
		page.navigate("https://jgsportesklep.pl/");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForTimeout(3500);
	}
	
	private static void testClose(Browser browser,BrowserContext browserContext) {
		BrowserFactory.saveBrowserContext(browserContext);
		browserContext.close();
		browser.close();
		System.exit(0);
	}
	
	private static boolean isLocatorPointDeepestElement(Locator locator,HtmlTag tag) {
		return locator.locator(tag.getTag()).count() == 0;
	}
	
	private static void clickButtonByText(Page page, String selector, String targetText) {
	    Locator buttons = page.locator(selector);
	    for (int i = 0; i < buttons.count(); i++) {
	        Locator btn = buttons.nth(i);
	        if (btn.textContent().trim().equals(targetText)) {
	            btn.click();
	            page.waitForTimeout(500);
	            break;
	        }
	    }
	}

	private static void iterateItems(Page page,String rootSelector,String itemsSelector, Consumer<Locator> action,int debug) {		
		Locator collapsingNavbar = page.locator(rootSelector);			
		Locator items = collapsingNavbar.locator(itemsSelector);			
		int count = items.count();
		System.out.println("count: " + count);
		for(int i = 0; i < count;i++) {
			Locator item = items.nth(i);	
			//System.out.println("Point -> " + item.textContent());
			action.accept(item);
			humanDelay(750, 1750);	
			//DEBUG OPCJA ZEBY NIE KLIKAL WSZYSTKICH KATEGORI
			if(i == debug)
				break;
		}	
	}
	
	
	private static void hoverAndClickLocator(Locator locator) {		
		locator.hover();
		humanDelay(500, 900);
		locator.click();
		humanDelay(1500, 2000);
	}	

	private static void humanDelay(int minMs, int maxMs) {
		try {
			int ms = ThreadLocalRandom.current().nextInt(minMs, maxMs + 1);
			Thread.sleep(ms);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

