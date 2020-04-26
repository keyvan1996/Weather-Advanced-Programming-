package Project_1;

/**
 * Created by Keyvan Shabani on 2/15/2020.
 **/

public class City {

    private String name;
    private String state;
    private String icon;
    private String longlat;
    private int temp;
    private int UVIndex;

    public City(String name,String state,String icon,int temp,int UVIndex){
        this.name = name;
        this.state = state;
        this.icon = icon;
        this.temp  = temp;
        this.UVIndex = UVIndex;
    }

    public City(){
    }

    public City(String name,String state,String longlat){
        this.name = name;
        this.state = state;
        this.longlat = longlat;
    }

    public String getName() {
        return name;
    }

    public String getLonglat() {
        return this.longlat;
    }

    public String getState() {
        return state;
    }

    public String getIcon() {
        return icon;
    }

    public int getTemp() {
        return temp;
    }

    public int getUVIndex() {
        return UVIndex;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setUVIndex(int UVIndex) {
        this.UVIndex = UVIndex;
    }
}
