package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    //public static String itemNameTitle;
    public static WebElement randomItem;
    private static final By ITEMS_COUNT_ON_PAGE = By.cssSelector("ol#products-list > li");


    public static Integer extractNumberFromString(String items){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(items);
        Integer intItems = null;
        while (m.find()){
            intItems = Integer.parseInt(m.group());
                }
        return intItems;
            }
    public static Double extractDoubleFromString(String s){
        s=s.replace(""+new DecimalFormatSymbols().getDecimalSeparator(),"");
        Pattern p = Pattern.compile("\\d+.\\d++");
        Matcher m = p.matcher(s);
        Double doublePrice = null;
        while (m.find()){
            doublePrice = Double.parseDouble(m.group());
        }
        return doublePrice;
    }
    public static String dateGeneration(){
        /*
        Random randomNumber = new Random();
        int upperBound = 1000000;
        int random = randomNumber.nextInt(upperBound);
        return random;
               */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yddMMyyyyHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
