package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.example.DriverManager.getDriver;

public class MainPage {

 private static final By LANGUAGE_LIST = By.id("select-language");
 private static final By BUTTON_HOME_DECOR = By.xpath("//*[@id=\"nav\"]/ol/li[4]");


 public MainPage navigateTo(String url) {
  getDriver().get(url);
  return this;
 }


 public enum Language {
  AUTOMATION("Automation"),
  ENGLISH("English");

  Language(String name) {
   this.name = name;
  }

  private String name;


  @Override
  public String toString() {
   return name;
  }
 }

 public MainPage setLanguage(Language language) {
  Select languageSelect = new Select(getDriver().findElement(LANGUAGE_LIST));
  languageSelect.selectByVisibleText(language.toString());
  return this;
 }
 public ElectronicsPage openElectronicsPage() {

  Actions action = new Actions(getDriver());
  WebElement element = getDriver().findElement(BUTTON_HOME_DECOR);
  action.moveToElement(element).build().perform();

  element.findElement(By.linkText("Electronics")).click();
  return new ElectronicsPage();

  //getDriver().findElement(BUTTON_HOME_DECOR).click();
  /*List<WebElement> options = getDriver().findElements(By.xpath("//*[@id=\"nav\"]/ol/li[4]/ul"));
  for (WebElement opt : options) {

   if (opt.getText().equals(option))
    opt.click();
   return new ElectronicsPage();
  }
  throw new NoSuchElementException("Can't find" + option + "in dropdown");
 }
   */

 }
}
