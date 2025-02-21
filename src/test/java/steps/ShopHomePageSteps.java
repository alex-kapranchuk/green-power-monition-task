package steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pageobjects.ProductsPage;
import pageobjects.SearchPage;
import pageobjects.ShopHomePage;

public class ShopHomePageSteps {
    private final Page page;
    private final ShopHomePage shopHomePage;

    public ShopHomePageSteps(Page page) {
        this.page = page;
        this.shopHomePage = new ShopHomePage(page);
    }

    @Step("Open Demo Web Shop homepage")
    public ShopHomePageSteps open() {
        shopHomePage.open();
        return this;
    }

    @Step("Search for product: {product}")
    public SearchPage search(String product) {
        shopHomePage.searchInput().fill(product);
        shopHomePage.searchInput().press("Enter");
        return new SearchPage(page);
    }

    @Step("Navigate to category: {category}")
    public ShopHomePageSteps navigateToCategory(String category) {
        shopHomePage.categoryLink(category).click();
        return this;
    }

    @Step("Navigate to sub-category: {subCategory}")
    public ProductsPage navigateToSubCategory(String subCategory) {
        shopHomePage.subCategoryLink(subCategory).click();
        return new ProductsPage(page);
    }
}
