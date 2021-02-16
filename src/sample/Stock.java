package sample;
import java.util.Date;

public class Stock {
    private String name;
    private Date date;
    private double openValue;
    private double highValue;
    private double lowValue;
    private double closeValue;
    /*private double adjCloseValue;
    private int volume;*/

    public Stock(String name, Date date, double closeValue, double openValue, double highValue, double lowValue){

        this.name = name;
        this.date = date;
        this.openValue = openValue;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.closeValue = closeValue;
            }
    /*public Stock(String name, Date date, double closeValue,double openValue, double highValue, double lowValue, double adjCloseValue, int volume){
        this.name = name;
        this.date = date;
        this.openValue = openValue;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.closeValue = closeValue;
        this.adjCloseValue = adjCloseValue;
        this.volume = volume;
    }*/
    public String getName(){
        return name;
    }
    public Date getDate(){

        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public double getOpenValue(){
        return openValue;
    }
    public void setOpenValue(double openValue){
        this.openValue = openValue;
    }
    public double getHighValue(){
        return highValue;
    }
    public void setHighValue(double highValue){
        this.highValue = highValue;
    }
    public double getLowValue(){
        return lowValue;
    }
    public void setLowValue(double lowValue){
        this.lowValue = lowValue;
    }
    public double getCloseValue(){
        return closeValue;
    }
    public void setCloseValue(double closeValue){
        this.closeValue = closeValue;
    }
    public double getAdjCloseValue(){
        return closeValue;
    }
    /*public void setAdjCloseValue(double adjCloseValue){
        this.adjCloseValue = adjCloseValue;
    }
    public int getVolume(){
        return volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }*/
    @Override
    public String toString() {
        return "Name: " + name + "Date: "+ date + "Close: "+ closeValue + "Open: "+openValue + " High: "+highValue + " Low: "+ lowValue;
    }


}
