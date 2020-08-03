package org.example;

import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
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
        //Pattern p = Pattern.compile("\\d+.+");
        s=s.replace(""+new DecimalFormatSymbols().getDecimalSeparator(),"");
        Pattern p = Pattern.compile("\\d+.\\d++");
        Matcher m = p.matcher(s);
        Double doublePrice = null;
        while (m.find()){
            doublePrice = Double.parseDouble(m.group());
        }
        return doublePrice;
    }
}
