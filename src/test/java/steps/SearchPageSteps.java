package steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pageobjects.SearchPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchPageSteps {

    private final Page page;
    private final SearchPage searchPage;

    public SearchPageSteps(Page page) {
        this.page = page;
        this.searchPage = new SearchPage(page);
    }

    @Step("Select product and verify visibility")
    public SearchPageSteps selectProduct(String productName) {
        searchPage.productGrid().click();
        assertThat(searchPage.productTitle(productName)).isVisible();
        return this;
    }
}
