package locator;

/**
 * Created by August on 2016-11-27.
 */
public interface Locator {

    static Locator create(){
        return new Locator() {
            @Override
            public Locale getClosestLocale(Coordinates coordinates) {
                return new Locale(new Coordinates(100, 100), "Swedish");
            }
        };
    }

    Locale getClosestLocale(Coordinates coordinates);

}
