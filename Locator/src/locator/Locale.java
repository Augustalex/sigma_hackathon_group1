package locator;

import java.util.Objects;

/**
 * Created by August on 2016-11-27.
 */
public class Locale {

    private Coordinates coordinates;
    private String language;

    public Locale(Coordinates coordinates, String language){
        this.coordinates = coordinates;
        this.language = language;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public String getLanguage(){
        return this.language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locale locale = (Locale) o;
        return Objects.equals(coordinates, locale.coordinates) &&
                Objects.equals(language, locale.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, language);
    }

    /*
    @Override
    public int hashCode(){
        return coordinates.hashCode() + language.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Locale){
            Locale comp = (Locale) o;
            return(comp.getLanguage().equals(this.getLanguage())
                      && comp.getCoordinates().equals(this.getCoordinates())
            );
        }
        else
            return false;
    }*/
}
