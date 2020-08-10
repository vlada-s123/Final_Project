package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static org.example.DriverManager.getDriver;
import static org.example.Utils.extractDoubleFromString;

public class SalePage extends MainPage{
    private static final By ITEMS_QUANTITY_DROPDOWN = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/select");
    private static final By GRID_BTN = By.linkText("Grid");
    private static final By ITEMS_ON_PAGE = By.cssSelector("ul.products-grid > li");
    private static final By OLD_PRICE = By.cssSelector("li > .product-info  .old-price");
    private static final By NEW_PRICE = By.cssSelector("li > .product-info  .special-price");
    private static final By TITLE_OF_ELEMENT = By.cssSelector(".product-name [title]");


    private double doubleOldPrice;
    private double doubleNewPrice;


    public SalePage showAsGridButton(){
       WebElement grid = getDriver().findElement(GRID_BTN);
       if (grid.isSelected()){
           return this;
       }
       else {
           grid.click();
           }
       return this;
    }
    public SalePage selectQuantityDropdown(Items items){
        Select itemsSelect = new Select(getDriver().findElement(ITEMS_QUANTITY_DROPDOWN));
        itemsSelect.selectByVisibleText(items.toString());
        return this;
    }
    public void verifyOldPriceIsHigher() {

        List<WebElement> oldPriceList = getDriver().findElements(OLD_PRICE);
        List<WebElement> newPriceList = getDriver().findElements(NEW_PRICE);

        for (int i = 0; i < oldPriceList.size(); i++){
            doubleOldPrice = extractDoubleFromString(oldPriceList.get(i).getText());
            doubleNewPrice = extractDoubleFromString(newPriceList.get(i).getText());
            Assert.assertTrue(doubleOldPrice > doubleNewPrice, String.format("Old price '$s' is lower than sales price '$s", doubleOldPrice, doubleNewPrice));
        }

    }

}


