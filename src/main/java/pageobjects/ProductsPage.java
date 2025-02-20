package pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    @Step("Add first product to cart")
    public ProductsPage addByIndexProductToCart(int index) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).nth(index).click();
        return this;
    }

    @Step("Add product to cart")
    public ProductsPage addProductToCartById(String id) {
        page.locator("#add-to-cart-button-"+ id).click();
        return this;
    }

    @Step("Select desktops products")
    public ProductsPage clickNavigationBar() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Desktops")).first().click();
        return this;
    }

}
