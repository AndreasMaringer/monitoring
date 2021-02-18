package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.shape.Path;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LineChartController {


    private XYChart.Series series0 = new XYChart.Series(); // Close
    private XYChart.Series series1 = new XYChart.Series();// Open
    private XYChart.Series series2 = new XYChart.Series();// High
    private XYChart.Series series3 = new XYChart.Series();// Low
    private XYChart.Series series4 = new XYChart.Series();// adjClose
    private XYChart.Series series5 = new XYChart.Series();// Volume
    private Date dateStart = null;
    private Date dateEnd = null;
    private File selectedFile;
    @FXML
    public MenuItem chooseFile;
    @FXML
    public Button buttonStart;
    @FXML
    public LineChart lineChart1;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public ToggleButton btnOpen;
    @FXML
    public ToggleButton btnLow;
    @FXML
    public ToggleButton btnHigh;
    @FXML
    public ToggleButton btnClose;
    @FXML
    public ToggleButton btnAdjClose;
    @FXML
    public ToggleButton btnVolume;
    @FXML
    public Button btnClear;
    @FXML
    public void setChooseFile(){

        Stage directoryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(directoryStage);
        if(selectedFile != null){
            System.out.println(selectedFile.getPath());
        }

    }

    @FXML
    public void showOpenValue(){
        if(btnOpen.isSelected() == false){
            lineChart1.getData().remove(series1);
            System.out.println("series1 remove:");

        }else{
            lineChart1.getData().add(series1);
            System.out.println("add series1:");
        }
    }

    @FXML
    public void showHighValue(){
        if(btnHigh.isSelected() == false){
            lineChart1.getData().remove(series2);
            System.out.println("remove series2");
        }else {
            lineChart1.getData().add(series2);
            System.out.println("showHighValue");
        }
    }
    @FXML
    public void showLowValue(){
        if(btnLow.isSelected() == false){
            lineChart1.getData().remove(series3);
            System.out.println("remove series3");
        }else {
            lineChart1.getData().add(series3);
            System.out.println("showLowValue");
        }
    }
    @FXML
    public void showCloseValue(){
        if(btnClose.isSelected() == false){
            lineChart1.getData().remove(series0);
            System.out.println("remove series0");
        }else{
            lineChart1.getData().add(series0);
            System.out.println("showCloseValue");
        }
    }
    @FXML
    public void showAdjCloseValue(){
        if(btnAdjClose.isSelected() == false){
            /*lineChart1.getData().remove(series4);*/
            System.out.println("remove series4");
        }else {
            /*lineChart1.getData().add(series4);*/
            System.out.println("showAdjCloseValue");
        }
    }
    @FXML
    public void showVolumeValue(){
        if(btnVolume.isSelected() == true){
//            lineChart1.getData().add(series5);
            System.out.println("add series 5 showVolumeValue");
        }else {
//            lineChart1.getData().remove(series5);
            System.out.println("remove series 5");
        }
    }
    @FXML
    private void setStartDate() throws ParseException {
        System.out.println("Start Datum: "+ startDate.getValue());
        System.out.println(startDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        dateStart =format.parse(startDate.getValue().toString());
        if(dateEnd != null){
            initialize();
        }

    }
    @FXML
    private void setEndDate() throws ParseException {
        System.out.println("END Datum: "+ endDate.getValue());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        /*DateTimeFormatter dateTimeFormatter = new DateTimeFormatter() ansatz für datum auf kurz format*/

        dateEnd = format.parse(endDate.getValue().toString());
        System.out.println("format.parse(endDate.getValue().toString() von:"+ dateEnd);

        if(dateStart != null) {
            initialize();
        }
    }
    @FXML
    private void clear(){
        btnClose.setSelected(false);
        btnOpen.setSelected(false);
        btnHigh.setSelected(false);
        btnLow.setSelected(false);

        lineChart1.getData().clear();
        System.out.println("Clear Button gedrückt!");
    }
    @FXML
     private void initialize() {
        System.out.println("initialize aufgerufen!");
        lineChart1.getData().clear();
        
        System.out.println("LineChart1.getData().clear() ausgeführt!!!");
        series0.setName("1 Close");
        series1.setName("2 Open");
        series2.setName("3 High");
        series3.setName("4 Low");
        /*series4.setName("5 adjClose");
        series5.setName("6 Volume");*/
        CSVReaderJava csv1 = new CSVReaderJava();

        lineChart1.setCursor(Cursor.CROSSHAIR); // Cursor Fadenkreuz!
        if(selectedFile != null){
        ArrayList<Stock> lineArray = csv1.readStockFromCSV(selectedFile.getPath()/*"c:/temp/BTC_USD_2013-10-01_2021-02-12.csv"*/); //c:/temp/stock03.csv
            int counter = 0;
         if(dateStart != null && dateEnd != null) {
             for (Stock stock : lineArray) { // lineArray ist ArrayList<Stock>
                 if (stock.getDate().after(dateStart) && stock.getDate().before(dateEnd)) {
                     String x = stock.getDate().toString();// von martin ???
                     System.out.print("Zeile 204 Stock.getDate().toString(): "+x);
                     try {

                         XYChart.Data data = new XYChart.Data(stock.getFormatDate(), stock.getOpenValue());
                         data.setNode(new HoveredThresholdNode(stock.getOpenValue()));//((i == 0) ? 0 : y[i-1], y[i]));
                         series0.getData().add(data);

                         XYChart.Data data1 = new XYChart.Data(stock.getFormatDate(), stock.getCloseValue());
                         data1.setNode(new HoveredThresholdNode(stock.getCloseValue()));
                         series1.getData().add(data1);

                         series2.getData().add(new XYChart.Data(stock.getFormatDate(), stock.getHighValue()));
                         series3.getData().add(new XYChart.Data(stock.getFormatDate(), stock.getLowValue()));
                         counter++;
                     } catch (Exception e){
                         System.out.println("Daten nicht gelesen!");
                     }

                 }
             }
         }

        /*lineChart1.getData().addAll(series0,series1, series2, series3, series4);*/
    }
    }
@FXML
    private void callButton() throws ParseException { // neuer zeitraum soll angezeicht werden!!!????
        lineChart1.getData().clear();
        btnClose.setSelected(true);

        setStartDate();
        setEndDate();
        showCloseValue();

        System.out.println("Button Start");

    }

}
