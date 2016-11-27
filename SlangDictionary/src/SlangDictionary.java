/**
 * Created by August on 2016-11-27.
 */
public interface SlangDictionary {

    LocationBasedWord getSlang(LocationBasedWord officialWord, Location location);

    void addSlang(LocationBasedWord slang, LocationBasedWord officialWord, Location location);

}
