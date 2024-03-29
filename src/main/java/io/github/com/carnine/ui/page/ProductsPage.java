package io.github.com.carnine.ui.page;

import com.microsoft.playwright.Locator;

import io.github.com.carnine.factory.BasePageFactory;
import io.github.com.carnine.ui.component.Header;
import io.github.com.carnine.ui.component.SideNavMenu;
import io.qameta.allure.Step;

/**
 * @author mcarnine3
 */
public final class ProductsPage extends BasePage {

    private Header header;
    private SideNavMenu sideNavMenu;

    @Override
    public void initComponents() {
        header = new Header(page);
        sideNavMenu = new SideNavMenu(page);
    }

    @Step("Get title of the 'Products' page")
    public Locator getTitle() {
        return page.locator(".title");
    }

    @Step("Click on 'Logout' button from side navigation menu")
    public LoginPage clickOnLogout() {
        header.clickOnHamburgerIcon();
        sideNavMenu.clickOnLogout();

        return BasePageFactory.createInstance(page, LoginPage.class);
    }
}
