package jg.application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import jg.factory.BrowserFactory;
import jg.pageElements.HttpTag;
import jg.pageElements.PrestaPageElements;

public class Application {
	
	private static final String MOTOREX_MAIN_CATEGORY = "#category-11";
	private static final String MOTOREX_COLLAPSING_NAVBAR = "#exCollapsingNavbar11";
	
	private static final String SCOTT_MAIN_CATEGORY = "#category-16";
	private static final String SCOTT_COLLAPSING_NAVBAR = "#exCollapsingNavbar16";
	
	public static void main(String[] args) {
		
		try(Playwright playwright = Playwright.create()){	
			Browser browser = BrowserFactory.createBrowser(playwright);	
			Browser.NewContextOptions contextOptions = BrowserFactory.createBrowserOptions();
			BrowserContext context = BrowserFactory.createBrowserContext(browser,contextOptions);
			
			Page page = context.newPage();
			page.navigate("http://google.com");
			page.waitForTimeout(3000);
			page.navigate("http://wikipedia.com");
			page.waitForTimeout(3000);
			page.navigate("https://jgsportesklep.pl/");
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.waitForTimeout(3500);	
			clickButtonByText(page, "#c-p-bn", "Akceptuj wszystko");
			
			// WYEKSTRAHOWAC CATEGORY DO CONFIGU
			//hoverAndClickLocator(page.locator(MOTOREX_MAIN_CATEGORY));
			hoverAndClickLocator(page.locator(SCOTT_MAIN_CATEGORY));
			//
			// TO DO CONFIGU JEST TO KATEGORIA MENU Z GORY, MENU z LEWEJ
			Queue<String> subcategories = new LinkedList<>();
			System.out.println("Start iterate to get subcategories");
			//iterateItems(page, MOTOREX_COLLAPSING_NAVBAR, item ->  subcategories.add(item.textContent()));
			iterateItems(page, SCOTT_COLLAPSING_NAVBAR, item ->  subcategories.add(item.textContent()));
			humanDelay(3000, 3500);
			System.out.println("End getting subcategories.");
			int current = 1;
			System.exit(0);
			while(!subcategories.isEmpty()) {
				System.out.println(String.format("%d / %d", current++, subcategories.size()));
				String subcategoryTitle = subcategories.poll();
				System.out.println("Get into : " + subcategoryTitle);
				
				//WCHODZI W KATEGORIE Z KOLEJKI
				iterateItems(page, SCOTT_COLLAPSING_NAVBAR, item ->{
					if(item.textContent().equals(subcategoryTitle)) {
						hoverAndClickLocator(item);
					}
				});
				boolean isNextButtonExist = true;
				do {
					Locator nextPageButton = page.locator(PrestaPageElements.NEXT_PAGE_BUTTON);
					isNextButtonExist = nextPageButton.count() > 0;
					
					if(isNextButtonExist)
						hoverAndClickLocator(nextPageButton);
						humanDelay(3000, 3500);
				}while(isNextButtonExist);
				
				
				
				
			}
			
			
			
			
			
			//zapisuje sesje do pliku przez co po ponownym wlaczeniu bedzie pamietało pliki cookie i sesje
			BrowserFactory.saveBrowserContext(context);
			//zwalnia zasoby
			context.close();
			browser.close();
		}
		
		
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

	private static void iterateItems(Page page,String collapsingNavbarId, Consumer<Locator> action) {		
		Locator collapsingNavbar = page.locator(collapsingNavbarId);			
		Locator items = collapsingNavbar.locator(HttpTag.LI.getTag());			
		int count = items.count();
		for(int i = 0; i < count;i++) {
			Locator item = items.nth(i);	
			System.out.println("Iterate through: " + item.textContent());
			action.accept(item);
			humanDelay(500, 1750);	
			//DEBUG OPCJA ZEBY NIE KLIKAL WSZYSTKICH KATEGORI
			//if(i == 2)
				//break;
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

