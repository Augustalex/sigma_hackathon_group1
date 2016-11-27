package slangDictionary;

import locator.Coordinate;
import locator.Locale;
import locator.Locator;
import slangDictionary.exception.UnsupportedLanguageException;
import translator.Translator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-27.
 */
public class MapSlangDictionary implements SlangDictionary {

    private final static String filePath = "dictionaryStorage.json";

    private Map<LocaleWord, Slang> dictionary = new HashMap<>();

    @Override
    public Slang getSlang(String officialWord, Coordinate coordinate) {
        Translator translator = Translator.create();
        String actualLanguage = translator.identifyLanguage(officialWord);

        Locator locator = Locator.create();
        Locale closestLocale = locator.getClosestLocale(coordinate);

        String translation;
        if(translator.supportsLanguage(actualLanguage))
            translation = translator.translate(actualLanguage, officialWord, closestLocale.getLanguage());
        else
            throw new UnsupportedLanguageException(actualLanguage);

        LocaleWord key = new LocaleWord(translation, closestLocale);

        return this.dictionary.get(key);
    }

    @Override
    public void addSlang(Slang slang, String officialWord, Locale locale) {
        LocaleWord key = new LocaleWord(officialWord, locale);
        this.dictionary.put(key, slang);
    }

    @Override
    public void addAllSlangs(Map<String, String> allSlangs, Locale locale) {
        for(Map.Entry<String, String> entry : allSlangs.entrySet()){
            addSlang(new Slang(entry.getValue()), entry.getKey(), locale);
        }
    }

    public void store(){
        
    }

    public void restore(){

    }
}
