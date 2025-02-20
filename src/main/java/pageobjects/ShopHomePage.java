package pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class ShopHomePage {

    private final Page page;

    public ShopHomePage(Page page) {
        this.page = page;
    }

    @Step("Open Demo Web Shop homepage")
    public ShopHomePage open() {
        page.navigate("https://demowebshop.tricentis.com/");
        return this;
    }

    @Step("Search for product: {product}")
    public SearchPage search(String product) {
        String searchInputSelector = "#small-searchterms";
        page.locator(searchInputSelector).fill(product);
        page.locator(searchInputSelector).press("Enter");
        return new SearchPage(page);
    }

    @Step("Navigate to category: {category}")
    public ShopHomePage navigateToCategory(String category) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(category)).nth(1).click();
        return this;
    }

    @Step("Navigate to sub-category: {subCategory}")
    public ProductsPage navigateToSubCategory(String subCategory) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(subCategory)).click();
        return new ProductsPage(page);
    }
}

