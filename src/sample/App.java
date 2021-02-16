package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;


public class App extends Application {
    final int WINDOW_SIZE = 10;
    private ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Java Realtime Chart");
        // defining the axes
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot agaist time
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axix animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axix animations are removed
        //creating the line chart with two axis created above
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Realtime JavaFX Charts");
        lineChart.setAnimated(false); // disable animations

        // defining a series to display data
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Data Series");

        // add series to chart
        lineChart.getData().add(series3);

        // setup scene
        Scene scene = new Scene(lineChart, 800, 600);
        primaryStage.setScene(scene);

        // show the stage
        primaryStage.show();

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into th chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // put dummy data onto graph per secound
        scheduledExecutorService.scheduleAtFixedRate(() ->{
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);
            // update the chart
            Platform.runLater(()-> {
                // get current time
                Date now = new Date();
                // put random with current time
                series3.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));

                if (series3.getData().size() > WINDOW_SIZE)
                    series3.getData().remove(0);
            });
        }, 0, 1, TimeUnit.SECONDS);
    }
    @Override
    public void stop() throws Exception{
        super.stop();
        scheduledExecutorService.shutdownNow();
    }
}
