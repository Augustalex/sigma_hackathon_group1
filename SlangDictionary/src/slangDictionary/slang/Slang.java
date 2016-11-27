package slangDictionary.slang;

import java.util.Objects;

/**
 * Created by August on 2016-11-27.
 */
public class Slang {

    private String slang;

    public Slang(String slang){
        this.slang = slang;
    }

    public String getWord(){
        return this.slang;
    }
    @Override
    public String toString(){
        return this.slang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slang slang1 = (Slang) o;
        return Objects.equals(slang, slang1.slang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slang);
    }
}
