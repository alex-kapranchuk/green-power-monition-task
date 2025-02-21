import com.microsoft.playwright.*;

import launch.BrowserFactory;
import launch.BrowserTypeEnum;
import org.testng.annotations.*;

import java.nio.file.Paths;

public class Base {

    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    // Add BrowserFactory.getBrowser() in @BeforeSuite
    @BeforeSuite
    public void setup() {
        browser = BrowserFactory.getBrowser(
                BrowserTypeEnum.CHROMIUM,
                true,
                1280,
                720
        );
    }

    @AfterSuite
    public void teardown() {
        BrowserFactory.closeBrowser();
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
