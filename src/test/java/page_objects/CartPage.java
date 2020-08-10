package page_objects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static org.example.DriverManager.getDriver;
import static org.example.Utils.extractDoubleFromString;

public class CartPage extends ElectronicsPage {
    //private static final By ITEM = By.cssSelector(".product-name[title]");
    private static final By ITEM = By.cssSelector(".product-cart-info .product-name");
    //private static final By PRICE = By.cssSelector("li> .product-info  .regular-price");
    private static final By PRICE = By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-price > span");

    public CartPage verifyCartPageIsOpened(){
        String title = getDriver().getTitle();
        Assert.assertEquals(title,"Shopping Cart");
        return this;
    }
    public void verifyItemNameAndPrice(){
        String getItemTitleInCart = getDriver().findElement(ITEM).getText();

        double actualPriceToDouble = extractDoubleFromString(actualPrice);
        String getItemPrice = getDriver().findElement(PRICE).getText();
        double priceInCart = extractDoubleFromString(getItemPrice);

        Assert.assertEquals( itemNameTitleInCart, getItemTitleInCart,"Invalid item");
        Assert.assertEquals(actualPriceToDouble, priceInCart, "Price doesn't match");

    }
}
