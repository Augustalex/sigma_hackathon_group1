package locator;

import java.util.Objects;

/**
 * Created by August on 2016-11-27.
 */
public class Coordinates {

    public double longitude;
    public double latitude;

    public Coordinates(double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

}
