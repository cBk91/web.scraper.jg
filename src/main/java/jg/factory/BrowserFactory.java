package jg.factory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

/** POTEM DODAC CONFIGI
 * 
 */
public class BrowserFactory {
	
	private static final Path STORAGE = Paths.get("storage-state.json");
	
	public static Browser createBrowser(Playwright playwright) {
		BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
				.setHeadless(false)
				.setArgs(List.of("--disable-blink-features=AutomationControlled",
						"--no-sandbox"));
		Browser browser = playwright.chromium().launch(launchOptions);
		return browser;
	}
	
	public static NewContextOptions createBrowserOptions() {
		NewContextOptions contextOptions = new Browser.NewContextOptions()
				.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
		                "AppleWebKit/537.36 (KHTML, like Gecko) " +
		                "Chrome/121.0.0.0 Safari/537.36")
				.setViewportSize(1280,800)
				.setLocale("en-US")
				.setTimezoneId("Europe/Warsaw")
				.setJavaScriptEnabled(true);
		
		contextOptions.setBypassCSP(true);
		//jezeli istnieje to ustawia sciezke do storage zeby uzywac plikow cookie pomiedzy uruchomieniem
		if(STORAGE.toFile().exists())
			contextOptions.setStorageStatePath(STORAGE);
		return contextOptions;
	}
	
	public static void saveBrowserContext(BrowserContext context) {
		context.storageState(new BrowserContext.StorageStateOptions().setPath(STORAGE));
	}
	
	public static BrowserContext createBrowserContext(Browser browser,NewContextOptions contextOptions) {	    		
	    BrowserContext browserContext = browser.newContext(contextOptions); 
	 // zmienia obiekt navigator na undefinied w celu ukrycia ze jest botem
	    browserContext.addInitScript("() => { Object.defineProperty(navigator, 'webdriver', { get: () => undefined }); }");
	    return  browserContext;
	}

}
