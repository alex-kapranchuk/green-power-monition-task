package steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pageobjects.ProductsPage;

public class ProductsPageSteps {

    private final Page page;
    private final ProductsPage productsPage;

    public ProductsPageSteps(Page page) {
        this.page = page;
        this.productsPage = new ProductsPage(page);
    }

    @Step("Add first product to cart")
    public ProductsPageSteps addByIndexProductToCart(int index) {
        productsPage.affToCart(index).click();
        return this;
    }

    @Step("Add product to cart by ID")
    public ProductsPageSteps addProductToCartById(String id) {
        productsPage.getByIdLocator(id).click();
        return this;
    }

    @Step("Select desktops products")
    public ProductsPageSteps clickNavigationBar() {
        productsPage.getByName().click();
        return this;
    }
}
