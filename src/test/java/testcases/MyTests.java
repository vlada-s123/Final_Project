package testcases;

import org.testng.annotations.Test;
import page_objects.MainPage;

import static page_objects.ElectronicsPage.Items.FIRST25;
import static page_objects.ElectronicsPage.Items.FIRST5;
import static page_objects.ElectronicsPage.Sort.PRICE;
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
}


