import io.qameta.allure.*;
import launch.TestResultListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.CartPageSteps;
import steps.ProductsPageSteps;
import steps.ShopHomePageSteps;
import utils.ScreenshotHelper;

import java.util.Map;


@Epic("Default Suite")
@Feature("Cart Management")
@Listeners(TestResultListener.class)
public class CartManagementTest extends Base {

    private static final Logger logger = LogManager.getLogger(CartManagementTest.class);

    @Test
    @Story("Add Product to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test description: Verify the product was added to the cart")
    public void cartManagementTest() {

        String CATEGORY = "Computers";
        String SUB_CATEGORY = "Picture for category Desktops";
        String PRODUCT_ID = "72";
        String PRODUCT_ID2 = "74";
        int QUANTITY = 6; // Could be parametrized

        logger.info("Test stats the Shopping Cart Management");

        new ShopHomePageSteps(page)
                .open()
                .navigateToCategory(CATEGORY)
                .navigateToSubCategory(SUB_CATEGORY);
        new ProductsPageSteps(page)
                .addByIndexProductToCart(0)
                .addProductToCartById(PRODUCT_ID)
                .clickNavigationBar()
                .addByIndexProductToCart(2)
                .addProductToCartById(PRODUCT_ID2);
        page.mouse().up();

        ScreenshotHelper.attachScreenshot(page, "Shopping Cart Management starts");
        logger.info("Starts the Shopping Cart Management");

        CartPageSteps cartPage = new CartPageSteps(page)
                .goToCart(2);

        Map<String, String> prices = cartPage.takePrices();

        double expectedTotal = cartPage.calculateExpectedTotal(prices, QUANTITY);

        cartPage
                .updateProductQuantity(QUANTITY)
                .updateCart()
                .verifyCartItemCount("Build your own expensive computer", QUANTITY + 1);

        Assert.assertEquals(Double.parseDouble(cartPage.takePrices().get("totalBlanc")), expectedTotal, 0.01);

        ScreenshotHelper.attachScreenshot(page, "Shopping Cart Management finishes");
        logger.info("Test is finished");
    }
}
