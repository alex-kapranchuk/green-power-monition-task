import com.microsoft.playwright.*;

import org.testng.annotations.*;

import java.nio.file.Paths;

public class Base {

    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeSuite
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterSuite
    public static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    @AfterMethod
    public void closeContext() {
        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
            context.close();
        }
    }
}
