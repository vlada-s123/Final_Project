package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static org.example.DriverManager.getDriver;
import static org.example.Utils.extractNumberFromString;
import static org.example.Utils.extractDoubleFromString;

public class ElectronicsPage extends MainPage {
    private static final By LIST = By.linkText("List");
    private static final By ITEMES_SELECTION = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div/select");
    private static final By ITEMS_COUNT_ON_PAGE = By.cssSelector("ol#products-list > li");
    private static final By ACTUAL_ITEMS = By.cssSelector(".category-products > .toolbar:nth-of-type(1) .amount--no-pages");
    private static final By PAGE_SELECTOR = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final By NEXT_BTN = By.linkText("NEXT");
    private static final By SORT_DROPDOWN = By.xpath("//html[@id='top']/body/div[@class='wrapper']/div[@class='page']/div[2]/div[@class='main']//div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[@title='Sort By']");
    private static final By PRICE = By.xpath("//html[@id='top']//ol[@id='products-list']//div[@class='price-box']");


    public ElectronicsPage selectShowAsListBtn() {
        getDriver().findElement(LIST).click();
        return this;
    }

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

    public ElectronicsPage setQuantitySelection(Items items) {
        Select itemsSelect = new Select(getDriver().findElement(ITEMES_SELECTION));
        itemsSelect.selectByVisibleText(items.toString());
        return this;
    }

    public void countItemsAmountOnPage() {
        int itemsAmountPerPage = getDriver().findElements(ITEMS_COUNT_ON_PAGE).size();
        String actualItems = getDriver().findElement(ACTUAL_ITEMS).getText();
        int itemsCount = extractNumberFromString(actualItems);

        Assert.assertEquals(itemsAmountPerPage, itemsCount);
    }

    public ElectronicsPage itemsOnEachPage(int expected) {
        int pageNumerationSelector = getDriver().findElements(PAGE_SELECTOR).size();

        for (int i = 1; i < pageNumerationSelector; i++) {
            if (i != pageNumerationSelector - 1) {
                int itemsOnPage = getDriver().findElements(ITEMS_COUNT_ON_PAGE).size();

                Assert.assertEquals(itemsOnPage, expected);

                getDriver().findElement(NEXT_BTN).click();
            } else {
                int itemsOnLastPage = getDriver().findElements(ITEMS_COUNT_ON_PAGE).size();

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
}
//Create list
//and use for each

