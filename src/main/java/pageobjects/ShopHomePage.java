package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ShopHomePage {

    private final Page page;

    public ShopHomePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://demowebshop.tricentis.com/");
    }

    public Locator searchInput() {
        return page.locator("#small-searchterms");
    }

    public Locator categoryLink(String category) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(category)).nth(1);
    }

    public Locator subCategoryLink(String subCategory) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(subCategory));
    }
}

