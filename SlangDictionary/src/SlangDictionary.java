/**
 * Created by August on 2016-11-27.
 */
public interface SlangDictionary {

    Slang getSlang(String officialWord, Location location);

    void addSlang(Slang slang, String officialWord, Location location);

}
