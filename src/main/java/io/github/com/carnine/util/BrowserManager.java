package io.github.com.carnine.util;

import static io.github.com.carnine.config.ConfigurationManager.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import io.github.com.carnine.factory.BrowserFactory;

/**
 * @author mcarnine3
 */
public final class BrowserManager {

    private BrowserManager() {}

    public static Browser getBrowser(final Playwright playwright) {
        return BrowserFactory.valueOf(config().browser().toUpperCase()).createInstance(playwright);
    }
}
