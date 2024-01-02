package io.github.com.carnine.ui.page;

import com.microsoft.playwright.Locator;

import io.github.com.carnine.config.ConfigurationManager;
import io.github.com.carnine.factory.BasePageFactory;
import io.qameta.allure.Step;

/**
 * @author mcarnine3
 */
public final class LoginPage extends BasePage {

    @Step("Navigate to the login page")
    public LoginPage open() {
        page.navigate(ConfigurationManager.config().baseUrl());

        return this;
    }

    @Step("Type <username> into 'Username' textbox")
    public LoginPage typeUsername(final String username) {
        page.fill("id=user-name", username);

        return this;
    }

    @Step("Type <password> into 'Password' textbox")
    public LoginPage typePassword(final String password) {
        page.fill("id=password", password);

        return this;
    }

    @Step("Get error message")
    public Locator getErrorMessage() {
        return page.locator(".error-message-container h3");
    }

    @Step("Click on the 'Login' button")
    public ProductsPage submitLogin() {
        page.click("id=login-button");

        return BasePageFactory.createInstance(page, ProductsPage.class);
    }

    @Step("Login attempt to Swag Labs")
    public ProductsPage loginAs(final String username, final String password) {
        open();
        typeUsername(username);
        typePassword(password);

        return submitLogin();
    }
}
