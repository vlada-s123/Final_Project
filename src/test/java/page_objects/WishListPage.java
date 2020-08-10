package page_objects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static org.example.DriverManager.getDriver;

public class WishListPage extends ElectronicsPage {
 public static final By ITEM = By.cssSelector(".product-name [title]");
    private static final By ADD_TO_WISHLIST = By.linkText("Add to Wishlist");
    private static final By TITLE_OF_ELEMENT = By.cssSelector(".product-name [title]");

    public WishListPage checkWishListPageOpened(){
       String title =  getDriver().getTitle();
        Assert.assertTrue(title.equals("My Wishlist"));
        return this;
    }

    public WishListPage checkItemInWishList(){
        String getItemTitleInWishList = getDriver().findElement(ITEM).getText();

        Assert.assertEquals( itemNameTitleInWishList, getItemTitleInWishList,"Invalid item");
        return this;
    }

}
