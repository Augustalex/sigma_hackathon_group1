package slangDictionary;

/**
 * Created by August on 2016-11-27.
 */
public interface SlangDictionary {

    static SlangDictionary create(){
        return new MapSlangDictionary();
    }

    Slang getSlang(String officialWord, Coordinate coordinates);

    void addSlang(Slang slang, String officialWord, Locale locale);

}
