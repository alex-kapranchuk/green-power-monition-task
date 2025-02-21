package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage {
    private final Page page;

    public SearchPage(Page page) {
        this.page = page;
    }

    public Locator productGrid() {
        return page.locator(".product-grid");
    }

    public Locator productTitle(String productName) {
        return page.locator("h2:has-text('" + productName + "')");
    }
}
