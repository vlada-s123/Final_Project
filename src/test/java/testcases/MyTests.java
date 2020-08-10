package testcases;

import org.testng.annotations.Test;
import page_objects.MainPage;

import static page_objects.ElectronicsPage.Sort.PRICE;
import static page_objects.MainPage.Items.*;
import static page_objects.MainPage.Language.AUTOMATION;

public class MyTests extends BaseTest{


    @Test
    public void checkItemsCounter() {
        new MainPage()
                .setLanguage(AUTOMATION)
                .openElectronicsPage()
                .selectShowAsListBtn()
                .setQuantitySelection(FIRST25)
                .countItemsAmountOnPage();
    }

        @Test
        public void checkShowSelect() {
            new MainPage()
                    .setLanguage(AUTOMATION)
                    .openElectronicsPage()
                    .selectShowAsListBtn()
                    .setQuantitySelection(FIRST5)
                    .itemsOnEachPage(5);

            }
        @Test
    public void sortBy(){
        new MainPage()
                .setLanguage(AUTOMATION)
                .openElectronicsPage()
                .selectShowAsListBtn()
                .setQuantitySelection(FIRST25)
                .sortDropdown(PRICE)
                .checkPrice();

            }
        @Test
        public void priceFilter() {
        new MainPage()
                .setLanguage(AUTOMATION)
                .openElectronicsPage()
                .selectShowAsListBtn()
                .setQuantitySelection(FIRST25)
                .priceRange()
                .checkPriceRangeLess100();

            }
        @Test
    public void addToWishList() throws InterruptedException {
        new MainPage()
                .setLanguage(AUTOMATION)
                .registerACustomer()
                .registration()
                .openElectronicsPage()
                .selectShowAsListBtn()
                .setQuantitySelection(FIRST25)
                .addToWishList()
                .checkWishListPageOpened()
                .checkItemInWishList();
        }

        @Test
    public void sale(){
        new MainPage()
                .setLanguage(AUTOMATION)
                .goToSale()
                .selectQuantityDropdown(FIRST36)
                .verifyOldPriceIsHigher();
        }
        @Test
        public void shoppingCart() throws InterruptedException {
        new MainPage()
                .setLanguage(AUTOMATION)
                .registerACustomer()
                .registration()
                .openElectronicsPage()
                .setQuantitySelection(FIRST36)
                .addToCart()
                .verifyCartPageIsOpened()
                .verifyItemNameAndPrice();
        }

    }


