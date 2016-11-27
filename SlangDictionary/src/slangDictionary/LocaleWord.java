package slangDictionary;

import locator.Locale;

import java.util.Objects;

/**
 * Created by August on 2016-11-27.
 */
public class LocaleWord {

    private Locale locale;
    private String word;

    public LocaleWord(String word, Locale locale){
        this.locale = locale;
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    public Locale getLocale(){
        return this.locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocaleWord that = (LocaleWord) o;
        return Objects.equals(locale, that.locale) &&
                Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locale, word);
    }

}
