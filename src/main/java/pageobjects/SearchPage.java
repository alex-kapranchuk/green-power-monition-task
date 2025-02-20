package pageobjects;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class SearchPage {
    private final Page page;

    public SearchPage(Page page) {
        this.page = page;
    }
    @Step("Select product and verify visibility")
    public SearchPage selectProduct(String productName) {
        page.locator(".product-grid").click();
        page.locator("h2:has-text('" + productName + "')").isVisible();
        return this;
    }
}
