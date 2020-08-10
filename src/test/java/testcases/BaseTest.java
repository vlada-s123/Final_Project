package testcases;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page_objects.MainPage;

import static org.example.DriverManager.getDriver;
import static org.example.DriverManager.killDriver;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void navigate(){
        new MainPage().navigateTo("http://magento.mainacad.com");
        getDriver().manage().window().maximize();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            screenCapture();
        }
        killDriver();
    }


    @Attachment(value = "screenshot", type = "image/png")
    private byte[] screenCapture() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
