/**
 * Created by August on 2016-11-27.
 */
public interface SlangDictionary {

    String getSlang(String officialWord, Location location);

    void addSlang(String slang, String officialWord, Location location);

}
