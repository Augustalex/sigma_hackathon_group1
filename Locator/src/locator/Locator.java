package locator;

/**
 * Created by August on 2016-11-27.
 */
public interface Locator {

    static Locator create(){
        return null;
    }

    Locale getClosestLocale(Coordinate coordinate);

}
