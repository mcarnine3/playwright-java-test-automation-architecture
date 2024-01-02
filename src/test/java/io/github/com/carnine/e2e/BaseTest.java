package io.github.com.carnine.e2e;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import static org.junit.jupiter.api.TestInstance.*;

import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.github.com.carnine.config.ConfigurationManager;
import io.github.com.carnine.factory.BasePageFactory;
import io.github.com.carnine.util.BrowserManager;
import io.github.com.carnine.ui.page.BasePage;
import io.github.com.carnine.ui.page.LoginPage;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Optional;

/**
 * @author mcarnine3
 */
@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;

    @RegisterExtension
    AfterTestExecutionCallback callback =
            context -> {
                Optional<Throwable> exception = context.getExecutionException();

                if (exception.isPresent()) {
                    captureScreenshotOnFailure();
                }
            };

    protected abstract byte[] captureScreenshotOnFailure();

    protected <T extends BasePage> T createInstance(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }

    @BeforeAll
    public void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", StringUtils.capitalize(ConfigurationManager.config().browser()))
                        .put("Context URL", ConfigurationManager.config().baseUrl())
                        .build(),
                ConfigurationManager.config().allureResultsDir() + "/");
    }

    @AfterAll
    public void closeBrowserAndPlaywrightSessions() {
        browser.close();
        playwright.close();
    }
}
