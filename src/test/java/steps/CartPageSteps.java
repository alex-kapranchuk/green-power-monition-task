package steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import pageobjects.CartPage;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPageSteps {
    private final Page page;
    private final CartPage cartPage;

    public CartPageSteps(Page page) {
        this.page = page;
        this.cartPage = new CartPage(page);
    }

    @Step("Go to cart")
    public CartPageSteps goToCart(int quantity) {
        cartPage.shoppingCartLink(quantity).hover();
        cartPage.goToCartButton().click();
        return this;
    }

    @Step("Update quantity of product to {quantity}")
    public CartPageSteps updateProductQuantity(int quantity) {
        cartPage.quantityInput().click();
        cartPage.quantityInput().fill(String.valueOf(quantity));
        cartPage.quantityInput().press("Enter");
        return this;
    }

    @Step("Update shopping cart")
    public CartPageSteps updateCart() {
        cartPage.updateCartButton().click();
        return this;
    }

    @Step("Take initial prices")
    public Map<String, String> takePrices() {
        Map<String, String> prices = new HashMap<>();
        prices.put("totalBlanc", cartPage.totalPrice().textContent());
        prices.put("productPrice", cartPage.productPrice().textContent());
        return prices;
    }

    @Step("Calculate expected total after changing quantity")
    public double calculateExpectedTotal(Map<String, String> prices, int quantityToAdd) {
        double productPrice = Double.parseDouble(prices.get("productPrice"));
        return Double.parseDouble(prices.get("totalBlanc")) + productPrice * (quantityToAdd - 1);
    }

    @Step("Verify cart item count: {expectedCount}")
    public CartPageSteps verifyCartItemCount(String productName, int expectedCount) {
        assertThat(page.locator("body")).containsText(productName);
        assertThat(page.locator("#topcartlink")).containsText("Shopping cart (" + expectedCount + ")");
        return this;
    }
}
