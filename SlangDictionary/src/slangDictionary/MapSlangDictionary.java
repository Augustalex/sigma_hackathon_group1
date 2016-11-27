package slangDictionary;

import translator.Translator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-27.
 */
public class MapSlangDictionary implements SlangDictionary {

    private Map<LocaleWord, Slang> dictionary = new HashMap<>();

    @Override
    public Slang getSlang(String officialWord, Locale locale) {
        Translator translator = Translator.create();
        String actualLanguage = translator.identifyLanguage(officialWord);

        String translation;
        if(translator.supportsLanguage(actualLanguage))
            translation = translator.translate(actualLanguage, officialWord, locale.getlanguage());
        else
            throw new UnsupportedLanguageException(actualLanguage);

        LocaleWord key = new LocaleWord(translation, locale);

        return this.dictionary.get(key);
    }

    @Override
    public void addSlang(Slang slang, String officialWord, Locale locale) {
        LocaleWord key = new LocaleWord(officialWord, locale);
        this.dictionary.put(key, slang);
    }
}
