package slangDictionary;

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
    public int hashCode(){
        return slang.hashCode();
    }
}
