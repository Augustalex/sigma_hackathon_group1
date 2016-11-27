package slangDictionary;

/**
 * Created by August on 2016-11-27.
 */
public class LocationBasedWord {

    private Location location;
    private String word;

    public LocationBasedWord(String word, Location location){
        this.location = location;
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    public Location getLocation(){
        return this.location;
    }
}
