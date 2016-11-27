package slangDictionary.exception;

/**
 * Created by August on 2016-11-27.
 */
public class UnsupportedLanguageException extends RuntimeException {

    public UnsupportedLanguageException(String language){
        super("This language: " + language + " is not supported.");
    }

}
