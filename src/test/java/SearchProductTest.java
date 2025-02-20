import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.ProductsPage;
import pageobjects.ShopHomePage;

import java.io.ByteArrayInputStream;

@Epic("Default Suite")
@Feature("Search Products")
public class SearchProductTest extends Base {

    private static final Logger logger = LogManager.getLogger(SearchProductTest.class);

    @Test
    @Story("Search product via search field")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test description: Verify the product search functionality")
    public void searchProductTest() {

        String PRODUCT_NAME = "Laptop";
        int QUANTITY_IN_CART =1;

        logger.info("Test stats the Search and Product Cart a Laptop");

        new ShopHomePage(page)
                .open()
                .search(PRODUCT_NAME)
                .selectProduct(PRODUCT_NAME);
        new ProductsPage(page)
                .addByIndexProductToCart(0);
        new CartPage(page)
                .goToCart(QUANTITY_IN_CART)
                .verifyCartItemCount(PRODUCT_NAME, QUANTITY_IN_CART);

        logger.info("Test is finished");

        Allure.addAttachment("1", new ByteArrayInputStream(page.screenshot()));
    }
}