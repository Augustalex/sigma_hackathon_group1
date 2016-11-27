package slangDictionary;

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
}
