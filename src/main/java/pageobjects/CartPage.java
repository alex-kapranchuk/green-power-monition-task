package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public Locator shoppingCartLink(int quantity) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shopping cart (" + quantity + ")"));
    }

    public Locator goToCartButton() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go to cart"));
    }

    public Locator quantityInput() {
        return page.locator("form table tbody tr:nth-child(2) td.qty.nobr input");
    }

    public Locator updateCartButton() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Update shopping cart"));
    }

    public Locator totalPrice() {
        return page.locator("span[class='product-price order-total'] strong");
    }

    public Locator productPrice() {
        return page.locator("tbody tr:nth-child(2) td:nth-child(4) span:nth-child(2)");
    }
}