package slangDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-27.
 */
public class MapSlangDictionary implements SlangDictionary {

    private Map<LocationBasedWord, Slang> dictionary = new HashMap<>();

    @Override
    public Slang getSlang(String officialWord, Location location) {
        LocationBasedWord key = new LocationBasedWord(officialWord, location);
        return this.dictionary.get(key);
    }

    @Override
    public void addSlang(Slang slang, String officialWord, Location location) {
        LocationBasedWord key = new LocationBasedWord(officialWord, location);
        this.dictionary.put(key, slang);
    }
}
