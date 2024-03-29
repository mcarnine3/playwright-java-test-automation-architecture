package io.github.com.carnine.e2e;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import io.github.com.carnine.annotation.DataSource;
import io.github.com.carnine.annotation.Smoke;
import io.github.com.carnine.dto.ProductsDto;
import io.github.com.carnine.ui.page.LoginPage;
import io.qameta.allure.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author mcarnine3
 */
@Feature("Products Test")
public class ProductsTest extends BaseTest {

    private static final String PATH = "products.csv";

    @BeforeEach
    public void createBrowserContextAndPageAndLoginPageInstances() {
        browserContext = browser.newContext();
        page = browserContext.newPage();

        loginPage = createInstance(LoginPage.class);
    }

    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    protected byte[] captureScreenshotOnFailure() {
        return loginPage.captureScreenshot();
    }

    @AfterEach
    public void closeBrowserContextSession() {
        browserContext.close();
    }

    @Smoke
    @Story("Logging out from Products page should redirect to Login page")
    @Owner("Tahanima Chowdhury")
    @Description("Test that verifies user gets redirected to 'Login' page after logging out")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-1", fileName = PATH, clazz = ProductsDto.class)
    public void testSuccessfulLogout(final ProductsDto data) {
        loginPage.loginAs(data.getUsername(), data.getPassword()).clickOnLogout();

        assertThat(page).hasURL(data.getUrl());
    }
}
