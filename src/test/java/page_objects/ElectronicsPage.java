package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static org.example.DriverManager.getDriver;
import static org.example.Utils.extractDoubleFromString;
import static org.example.Utils.extractNumberFromString;

public class ElectronicsPage extends MainPage {
    private static final By LIST = By.linkText("List");
    private static final By ITEMS_SELECTION = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/select");
    private static final By ITEMS_LIST_ON_PAGE = By.cssSelector("ol#products-list > li");
    private static final By ITEMS_COUNT_ON_PAGE_GRID = By.cssSelector(".first.last.odd.products-grid.products-grid--max-4-col > li");
    private static final By ACTUAL_ITEMS = By.cssSelector(".category-products > .toolbar:nth-of-type(1) .amount--no-pages");
    private static final By PAGE_SELECTOR = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final By NEXT_BTN = By.linkText("NEXT");
    private static final By SORT_DROPDOWN = By.xpath("//html[@id='top']/body/div[@class='wrapper']/div[@class='page']/div[2]/div[@class='main']//div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[@title='Sort By']");
   // private static final By PRICE = By.xpath("//html[@id='top']//ol[@id='products-list']//div[@class='price-box']");
    //private static final By PRICE = By.cssSelector("li> .product-info  .regular-price");
    private static final By PRICE = By.cssSelector("li > .product-info  .regular-price > .price, li > .product-info  .price-to");
    private static final By PRICE_RANGE = By.cssSelector("#narrow-by-list > dd:nth-child(2) > ol > li:nth-child(1)");
    private static final By ADD_TO_WISHLIST = By.linkText("Add to Wishlist");
    private static final By TITLE_OF_ELEMENT = By.cssSelector(".product-name [title]");
    private static final By ADD_TO_CART_BTN = By.cssSelector("button[title='Add to Cart']");

    public static String itemNameTitleInWishList;
    public static String itemNameTitleInCart;
    public static String actualPrice;


    public ElectronicsPage selectShowAsListBtn() {
        getDriver().findElement(LIST).click();
        return this;
    }
    /*

    public enum Items {
        FIRST5("5"),
        FIRST10("10"),
        FIRST15("15"),
        FIRST20("20"),
        FIRST25("25");

        Items(String quantityElements) {
            this.quantityElements = quantityElements;
        }

        private String quantityElements;

        @Override
        public String toString() {
            return quantityElements;
        }
    }

     */

    public ElectronicsPage setQuantitySelection(Items items) {
        Select itemsSelect = new Select(getDriver().findElement(ITEMS_SELECTION));
        itemsSelect.selectByVisibleText(items.toString());
        return this;
    }

    public void countItemsAmountOnPage() {
        int itemsAmountPerPage = getDriver().findElements(ITEMS_LIST_ON_PAGE).size();

        String actualItems = getDriver().findElement(ACTUAL_ITEMS).getText();
        int itemsCount = extractNumberFromString(actualItems);

        Assert.assertEquals(itemsAmountPerPage, itemsCount);
    }

    public ElectronicsPage itemsOnEachPage(int expected) {
        int pageNumerationSelector = getDriver().findElements(PAGE_SELECTOR).size();

        for (int i = 1; i < pageNumerationSelector; i++) {
            if (i != pageNumerationSelector - 1) {
                int itemsOnPage = getDriver().findElements(ITEMS_LIST_ON_PAGE).size();

                Assert.assertEquals(itemsOnPage, expected);

                getDriver().findElement(NEXT_BTN).click();
            } else {
                int itemsOnLastPage = getDriver().findElements(ITEMS_LIST_ON_PAGE).size();

                Assert.assertTrue(itemsOnLastPage <= expected);
            }
        }
        return this;
    }


    public enum Sort {
        POSITION("Position"),
        NAME("Name"),
        PRICE("Price");

        Sort(String sortBy) {
            this.sortBy = sortBy;
        }

        private String sortBy;

        @Override
        public String toString() {
            return sortBy;
        }
    }

    public ElectronicsPage sortDropdown(Sort sort) {
        Select sortDropdown = new Select(getDriver().findElement(SORT_DROPDOWN));
        sortDropdown.selectByVisibleText(sort.toString());
        return this;
    }

    public void checkPrice() {
        List<WebElement> listOfPrices = getDriver().findElements(PRICE);
        double lastPrice = 0;

        for (WebElement price : listOfPrices) {
            double currentPrice = extractDoubleFromString(price.getText());
            Assert.assertTrue(lastPrice <= currentPrice,
                    String.format("Previous price '%s' should be less or equals to next price '%s'", lastPrice, currentPrice));
            lastPrice = currentPrice;
        }
    }
     public ElectronicsPage priceRange(){
            getDriver().findElement(PRICE_RANGE).click();
            return this;
        }

      public void checkPriceRangeLess100(){
       List<WebElement> listOfPrice = getDriver().findElements(PRICE);

       for(WebElement price : listOfPrice){
           double currentPrice = extractDoubleFromString(price.getText());
           Assert.assertTrue(currentPrice <= 999.99, String.format("Price '$s' should be less or equal 999.99", currentPrice));
       }

    }
        public WishListPage addToWishList() throws InterruptedException {
            List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ITEMS_LIST_ON_PAGE));

            /* int itemsListSize = listOfItems.size();

            Random itemsRandom = new Random();
            int randomItem = itemsRandom.nextInt(itemsListSize);

            WebElement element = listOfItems.get(randomItem);
            String itemNameOfTitle = getDriver().findElement(TITLE_OF_ELEMENT).getText();

            element.findElement(ADD_TO_WISHLIST).click();
             */

        Random random = new Random();
            WebElement randomItem = listOfItems.get(random.nextInt(listOfItems.size()));
            itemNameTitleInWishList = randomItem.findElement(TITLE_OF_ELEMENT).getText();

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomItem);

            Thread.sleep(500);
                randomItem.findElement(ADD_TO_WISHLIST).click();

            return new WishListPage();
        }



        public CartPage addToCart() throws InterruptedException {
            List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ITEMS_COUNT_ON_PAGE_GRID));

            Random random = new Random();
            WebElement randomItem = listOfItems.get(random.nextInt(listOfItems.size()));
            itemNameTitleInCart = randomItem.findElement(TITLE_OF_ELEMENT).getText();
            actualPrice = randomItem.findElement(PRICE).getText();

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomItem);

            Thread.sleep(2000);

            randomItem.findElement(ADD_TO_CART_BTN).click();
        return new CartPage();
    }

}

