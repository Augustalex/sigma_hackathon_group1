package translator;

/**
 * Created by August on 2016-11-27.
 */
public interface Translator {

    static Translator create(){
        return new Translator() {
            @Override
            public boolean supportsLanguage(String language) {
                return true;
            }

            @Override
            public String identifyLanguage(String word) {
                return "English";
            }

            @Override
            public String translate(String originLanguage, String word, String destinationLanguage) {
                return word;
            }
        };
    }
    boolean supportsLanguage(String language);

    String identifyLanguage(String word);

    String translate(String originLanguage, String word, String destinationLanguage);
}
