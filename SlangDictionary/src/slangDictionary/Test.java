package slangDictionary;

import locator.Coordinate;
import locator.Locale;

/**
 * Created by August on 2016-11-27.
 */
public class Test {

    public static void main(String[] args){
        Slang slang = new Slang("Öh");

        SlangDictionary dictionary = SlangDictionary.create();

        Locale locale = new Locale(new Coordinate(100, 100), "Swedish");
        dictionary.addSlang(slang, "Hej", locale);

        Slang result = dictionary.getSlang("Hej", new Coordinate(100, 100));
        System.out.println(result.toString());
    }
}
