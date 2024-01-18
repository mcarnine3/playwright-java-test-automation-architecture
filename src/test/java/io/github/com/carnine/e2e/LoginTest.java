package io.github.com.carnine.e2e;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import io.github.com.carnine.annotation.DataSource;
import io.github.com.carnine.annotation.Smoke;
import io.github.com.carnine.annotation.Validation;
import io.github.com.carnine.dto.LoginDto;
import io.github.com.carnine.ui.page.LoginPage;
import io.github.com.carnine.ui.page.ProductsPage;
import io.qameta.allure.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author mcarnine3
 */
@Feature("Login Test")
public class LoginTest extends BaseTest {

    private static final String PATH = "login.csv";

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
    @Story("User enters correct login credentials")
    @Owner("Tahanima Chowdhury")
    @Description(
            "Test that verifies user gets redirected to 'Products' page after submitting correct login credentials")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-1", fileName = PATH, clazz = LoginDto.class)
    public void testCorrectLoginCredentials(final LoginDto data) {
        ProductsPage productsPage = loginPage.loginAs(data.getUsername(), data.getPassword());

        assertThat(productsPage.getTitle()).hasText("Products");
    }

    @Validation
    @Story("User enters incorrect login credentials")
    @Owner("Tahanima Chowdhury")
    @Description(
            "Test that verifies user gets error message after submitting incorrect login credentials")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-2", fileName = PATH, clazz = LoginDto.class)
    public void testIncorrectLoginCredentials(final LoginDto data) {
        loginPage.loginAs(data.getUsername(), data.getPassword());

        assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
    }

    @Validation
    @Story("User keeps the username blank")
    @Owner("Tahanima Chowdhury")
    @Description(
            "Test that verifies user gets error message after submitting login credentials where the username is blank")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-3", fileName = PATH, clazz = LoginDto.class)
    public void testBlankUserName(final LoginDto data) {
        loginPage.open().typePassword(data.getPassword()).submitLogin();

        assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
    }

    @Validation
    @Story("User keeps the password blank")
    @Owner("Tahanima Chowdhury")
    @Description(
            "Test that verifies user gets error message after submitting login credentials where the password is blank")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-4", fileName = PATH, clazz = LoginDto.class)
    public void testBlankPassword(final LoginDto data) {
        loginPage.open().typeUsername(data.getUsername()).submitLogin();

        assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
    }

    @Validation
    @Story("User is locked out")
    @Owner("Tahanima Chowdhury")
    @Description(
            "Test that verifies user gets error message after submitting login credentials for locked out user")
    @ParameterizedRepeatedIfExceptionsTest
    @DataSource(id = "TC-5", fileName = PATH, clazz = LoginDto.class)
    public void testLockedOutUser(final LoginDto data) {
        loginPage.loginAs(data.getUsername(), data.getPassword());

        assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
    }
}