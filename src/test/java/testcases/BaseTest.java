package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page_objects.MainPage;

import static org.example.DriverManager.killDriver;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void navigate(){
        new MainPage().navigateTo("http://magento.mainacad.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        killDriver();
    }


}
