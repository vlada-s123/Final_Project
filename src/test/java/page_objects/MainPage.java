package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.example.DriverManager.getDriver;

public class MainPage {

 private static final By LANGUAGE_LIST = By.id("select-language");
 private static final By BUTTON_HOME_DECOR = By.xpath("//*[@id=\"nav\"]/ol/li[4]");
 private static final By ELECTRONICS = By.linkText("Electronics");
 private static final By ACCOUNT = By.cssSelector(".skip-account.skip-link");
 private static final By REGISTRATION = By.cssSelector("div#header-account  ul > li:nth-of-type(5)");
 private static final By SALE = By.linkText("SALE");


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






 public enum Items {
  FIRST5("5"),
  FIRST10("10"),
  FIRST12("12"),
  FIRST15("15"),
  FIRST20("20"),
  FIRST24("24"),
  FIRST25("25"),
  FIRST36("36");


  Items(String quantityElements) {
   this.quantityElements = quantityElements;
  }

  private String quantityElements;

  @Override
  public String toString() {
   return quantityElements;
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

  element.findElement(ELECTRONICS).click();
  return new ElectronicsPage();
 }
  public RegistrationPage registerACustomer(){
   //Actions findAccountDropdown = new Actions(getDriver());
   WebElement account = getDriver().findElement(ACCOUNT);
   account.click();
   //findAccountDropdown.moveToElement(account).click();
   getDriver().findElement(REGISTRATION).click();
   return new RegistrationPage();
  }

   public SalePage goToSale(){
  getDriver().findElement(SALE).click();
   return new SalePage();
   }

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

