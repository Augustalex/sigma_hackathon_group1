package locator;

/**
 * Created by August on 2016-11-27.
 */
public class Locale {

    private Coordinate coordinates;
    private String language;

    public Locale(Coordinate coordinates, String language){
        this.coordinates = coordinates;
        this.language = language;
    }

    public Coordinate getCoordinates(){
        return this.coordinates;
    }

    public String getlanguage(){
        return this.language;
    }

}
