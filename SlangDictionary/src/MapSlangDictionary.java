import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-27.
 */
public class MapSlangDictionary implements SlangDictionary {

    private Map<LocationBasedWord, LocationBasedWord> dictionary = new HashMap<>();

    public LocationBasedWord getSlang(LocationBasedWord officialWord, Location location) {
        return null;
    }

    public void addSlang(LocationBasedWord slang, LocationBasedWord officialWord, Location location) {

    }
}
