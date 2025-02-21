package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public Locator affToCart(int index) {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).nth(index);
    }

    public Locator getByIdLocator(String id) {
        return page.locator("#add-to-cart-button-" + id);
    }

    public Locator getByName() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Desktops")).first();
    }
}
