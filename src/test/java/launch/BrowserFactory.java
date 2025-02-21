package launch;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static Browser getBrowser(BrowserTypeEnum browserType, boolean headless, int width, int height) {
        if (browser == null) {
            playwright = Playwright.create();
            browser = switch (browserType) {
                case CHROMIUM -> playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setArgs(java.util.List.of("--window-size=" + width + "," + height)));
                case FIREFOX -> playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setArgs(java.util.List.of("--width=" + width, "--height=" + height)));
                case WEBKIT -> playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(headless));
            };
        }
        return browser;
    }

    public static void closeBrowser() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
