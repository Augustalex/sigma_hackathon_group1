package locator;

/**
 * Created by August on 2016-11-27.
 */
public interface Locator {

    static Locator create(){
        return new Locator() {
            @Override
            public Locale getClosestLocale(Coordinate coordinate) {
                return new Locale(new Coordinate(100, 100), "Swedish");
            }
        };
    }

    Locale getClosestLocale(Coordinate coordinate);

}
