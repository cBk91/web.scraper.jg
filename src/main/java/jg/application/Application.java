package jg.application;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Application {
	
	private static final Path STORAGE = Paths.get("storage-state.json");
	
	public static void main(String[] args) {
		
		try(Playwright playwright = Playwright.create()){
			BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
					.setHeadless(false)
					.setArgs(List.of("--disable-blink-features=AutomationController",
							"--no-sandbox"));
			Browser browser = playwright.chromium().launch(launchOptions);
			
			Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
					.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/121.0.0.0 Safari/537.36")
					.setViewportSize(1280,800)
					.setLocale("en-US")
					.setTimezoneId("Europe/Warsaw")
					.setJavaScriptEnabled(true);
					
			//jezeli istnieje to ustawia sciezke do storage zeby uzywac plikow cookie pomiedzy uruchomieniem
			if(STORAGE.toFile().exists())
				contextOptions.setStorageStatePath(STORAGE);
			
			BrowserContext context = browser.newContext(contextOptions);
			
			// zmienia obiekt navigator na undefinied w celu ukrycia ze jest botem
			context.addInitScript("() => {Object.defineProperty(navigator, 'webdriver', {get: () => undefinied});}}");
			
			Page page = context.newPage();
			page.navigate("http://google.com");
			page.waitForTimeout(3000);
			page.navigate("http://wikipedia.com");
			page.waitForTimeout(3000);
			page.navigate("https://jgsportesklep.pl/");
			page.waitForTimeout(3500);	
			
			// TO DO CONFIGU JEST TO KATEGORIA MENU Z GORY
			Locator category = page.locator("#category-11");
			category.hover();
			humanDelay(500, 900);
			category.click();
			humanDelay(1500, 2000);
			Locator collapsingNavbar = page.locator("#exCollapsingNavbar11");
			
			Locator items = collapsingNavbar.locator("li");
			
			int count = items.count();
			for(int i = 0; i < count;i++) {
				Locator li = items.nth(i);
				System.out.println(li.textContent());
				humanDelay(500, 1750);
				
			}
			
			
			
			
			
			//zapisuje sesje do pliku przez co po ponownym wlaczeniu bedzie pamietało pliki cookie i sesje
			context.storageState(new BrowserContext.StorageStateOptions().setPath(STORAGE));
			
			browser.close();
		}
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

