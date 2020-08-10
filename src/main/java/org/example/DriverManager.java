package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager{

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal();

    private DriverManager(){}


    public static WebDriver getDriver(){
        if (threadDriver.get()==null){
            initDriver();
        }
        return threadDriver.get();
    }

    private static void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                threadDriver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                threadDriver.set(new FirefoxDriver());
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                threadDriver.set(new InternetExplorerDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                threadDriver.set(new EdgeDriver());
            default:
                throw new WebDriverException(String.format("Driver for browser '%s' not found", browser));
        }
        threadDriver.get().manage().window().fullscreen();
        threadDriver.get().manage().timeouts().pageLoadTimeout(18, TimeUnit.SECONDS);
        threadDriver.get().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
         public static void killDriver(){
        threadDriver.get().close();
         threadDriver.get().quit();
         threadDriver.remove();
    }

}
