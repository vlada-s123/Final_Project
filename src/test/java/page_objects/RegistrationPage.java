package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.example.DriverManager.getDriver;
import static org.example.Utils.dateGeneration;

public class RegistrationPage {

    private static final By FIRST_NAME = By.id("firstname");
    private static final By LAST_NAME = By.id("lastname");
    private static final By EMAIL = By.id("email_address");
    private static final By PASSWORD = By.id("password");
    private static final By CONFIRM_PASSWORD = By.id("confirmation");
    private static final By SIGN_UP_NEWSLETTERS_CHECKBOX = By.cssSelector("input#is_subscribed");
    private static final By REGISTER_BTN = By.cssSelector("button[title='Register']");

    public MainPage registration() throws InterruptedException {
        getDriver().findElement(FIRST_NAME).sendKeys("test");

        getDriver().findElement(LAST_NAME).sendKeys("test");

        getDriver().findElement(EMAIL).sendKeys("testauto"+dateGeneration()+"@gmail.com");

        getDriver().findElement(PASSWORD).sendKeys("123123");

        WebElement confirmPassword = getDriver().findElement(CONFIRM_PASSWORD);
        confirmPassword.sendKeys("123123");

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", confirmPassword);
        Thread.sleep(2000);

        getDriver().findElement(SIGN_UP_NEWSLETTERS_CHECKBOX).click();

        getDriver().findElement(REGISTER_BTN).click();
        Thread.sleep(2000);

        //registrationButton.click();

        return new MainPage();
    }
}
