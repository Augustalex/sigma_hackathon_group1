package slangDictionary.dictionary;

import locator.Coordinates;
import locator.Locale;
import locator.Locator;
import slangDictionary.slang.LocaleWord;
import slangDictionary.slang.Slang;
import slangDictionary.exceptions.UnsupportedLanguageException;
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
    public Slang getSlang(String officialWord, Coordinates coordinates) {
        LocaleWord key = matchWordToLocalWord(officialWord, coordinates);
        return this.dictionary.get(key);
    }

    @Override
    public void addSlang(Slang slang, String officialWord, Locale locale) {
        LocaleWord key = new LocaleWord(officialWord, locale);
        this.dictionary.put(key, slang);
    }

    @Override
    public void addAllSlangs(Map<String, String> allSlangs, Locale locale) {
        allSlangs.entrySet().parallelStream()
                .forEach((entry) -> addSlang(
                        new Slang(entry.getValue()),
                        entry.getKey(), locale
                ));
    }

    public void store(){

    }

    public void restore(){

    }

    private LocaleWord matchWordToLocalWord(String word, Coordinates coordinates){
        Locale closestLocale = Locator.create().getClosestLocale(coordinates);
        String translatedWord = translateToLocalLanguage(word, closestLocale);

        return new LocaleWord(translatedWord, closestLocale);
    }

    private String translateToLocalLanguage(String word, Locale closestLocale){
        Translator translator = Translator.create();
        String actualLanguage = translator.identifyLanguage(word);

        if(translator.supportsLanguage(actualLanguage))
            return translator.translate(actualLanguage, word, closestLocale.getLanguage());
        else
            throw new UnsupportedLanguageException(actualLanguage);

    }
}
