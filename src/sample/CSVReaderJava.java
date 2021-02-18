package sample;

import sample.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReaderJava {

    public ArrayList<Stock> readStockFromCSV(String fileName) {
        ArrayList<Stock> stocks = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        int counter = 0;
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();


            while (line != null) {

                if (counter == 0) { // 1.zeile mit beschriftung
                    counter++;
                    line = br.readLine();
                    continue;
                } else{
                    String[] attributes = line.split(",");
                    Stock stock = createStock(attributes);
                    //System.out.println(attributes[0]);// printet alle Datums in csv
                    stocks.add(stock);
                    //System.out.println(stocks);
                    line = br.readLine();
                    counter++;
                }



            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return stocks;
    }

    public static Stock createStock(String[] data) throws ParseException {
        String name = data[0]; // btc
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date =formatter.parse(data[1]);
        double closeValue = Double.parseDouble(data[2]);
        double openValue = Double.parseDouble(data[3]);
        double highValue = Double.parseDouble(data[4]);
        double lowValue = Double.parseDouble(data[5]);


       /* Date date =formatter.parse(data[0]);
        double openValue = Double.parseDouble(data[1]);
        double highValue = Double.parseDouble(data[2]);
        double lowValue = Double.parseDouble(data[3]);
        double closeValue = Double.parseDouble(data[4]);
        double adjCloseValue = Double.parseDouble(data[5]);
        int volume = Integer.parseInt(data[6]);*/

        return new Stock(name, date, closeValue, openValue, highValue, lowValue); // btc
        //return new Stock(date, openValue, highValue, lowValue, closeValue, adjCloseValue, volume); für test csv
    }
}
