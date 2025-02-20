package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    @Step("Go to cart")
    public CartPage goToCart(int quantity) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shopping cart (" + quantity + ")")).hover();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go to cart")).click();
        return this;
    }

    @Step("Update quantity of {productName} to {quantity}")
    public CartPage updateProductQuantity(int quantity) {
        Locator quantityInput = page.locator("form table tbody tr:nth-child(2) td.qty.nobr input");

        quantityInput.click();
        quantityInput.fill(String.valueOf(quantity));
        quantityInput.press("Enter");
        return this;
    }

    @Step("Update shopping cart")
    public CartPage updateCart() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Update shopping cart")).click();
        return this;
    }

    @Step("Verify cart item count: {expectedCount}")
    public CartPage verifyCartItemCount(String productName, int expectedCount) {
        assertThat(page.locator("body")).containsText(productName);
        assertThat(page.locator("#topcartlink")).containsText("Shopping cart (" + expectedCount + ")");
        return this;
    }

    @Step("Take initial prices")
    public Map<String, String> takePrices() {
        Map<String, String> prices = new HashMap<>();
        prices.put("totalBlanc", page.locator("span[class='product-price order-total'] strong").textContent());
        prices.put("productPrice", page.locator("tbody tr:nth-child(2) td:nth-child(4) span:nth-child(2)").textContent());
        return prices;
    }

    @Step("Calculate expected total after changing quantity")
    public double calculateExpectedTotal(Map<String, String> prices, int quantityToAdd) {
        double productPrice = Double.parseDouble(prices.get("productPrice"));
        return Double.parseDouble(prices.get("totalBlanc")) + productPrice * (quantityToAdd - 1);
    }
}
