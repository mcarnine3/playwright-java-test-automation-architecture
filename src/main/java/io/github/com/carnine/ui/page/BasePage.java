package io.github.com.carnine.ui.page;

import com.microsoft.playwright.Page;

import io.github.com.carnine.config.ConfigurationManager;
import io.qameta.allure.Step;

/**
 * @author mcarnine3
 */
public abstract class BasePage {

    protected Page page;

    public void setAndConfigurePage(final Page page) {
        this.page = page;

        page.setDefaultTimeout(ConfigurationManager.config().timeout());
    }

    public void initComponents() {}

    @Step
    public byte[] captureScreenshot() {
        return page.screenshot();
    }
}
