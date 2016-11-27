package slangDictionary;

import locator.Coordinate;
import locator.Locale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by August on 2016-11-27.
 */
public interface SlangDictionary {

    static SlangDictionary create(){
        SlangDictionary dictionary = new MapSlangDictionary();

        Locale locale = new Locale(new Coordinate(100, 100), "Swedish");

        Map<String, String> allSlangs = new HashMap<>();

        allSlangs.put("Hej", "Öh");
        allSlangs.put("Hallå", "Öh");
        allSlangs.put("Spring", "Kubba");
        allSlangs.put("Girig", "Åpen");
        allSlangs.put("Öppen", "Åben");
        allSlangs.put("Ärlig", "Åben");
        allSlangs.put("Matsal", "Bamba");
        allSlangs.put("Gråta", "Böla");
        allSlangs.put("Grina", "Böla");
        allSlangs.put("Idiot", "Flane");
        allSlangs.put("Person", "Tjome");
        allSlangs.put("Flytta", "Knö");

        dictionary.addAllSlangs(allSlangs, locale);

        return dictionary;
    }

    Slang getSlang(String officialWord, Coordinate coordinates);

    void addSlang(Slang slang, String officialWord, Locale locale);

    void addAllSlangs(Map<String, String> allSlangs, Locale locale);

}
