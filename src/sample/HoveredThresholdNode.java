package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HoveredThresholdNode extends StackPane {
    public HoveredThresholdNode(double priorValue, double value) {
        setPrefSize(5, 5);
        final Label label = createDataThresholdLabel(priorValue, value);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().setAll(label);
                setCursor(Cursor.NONE);
                toFront();
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
                setCursor(Cursor.CROSSHAIR);
            }
        });
    }

    private Label createDataThresholdLabel(double priorValue, double value) {
        final Label label = new Label(value + "");

        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        label.setTextFill(Color.BLUE);

        if (priorValue == 0) {
            label.setTextFill(Color.DARKGRAY);
        } else if (value > priorValue) {
            label.setTextFill(Color.FORESTGREEN);
        } else {
            label.setTextFill(Color.FIREBRICK);
        }
        if (value > 35000){   // Border bei 35000 - Deepskyblue
            label.setTextFill(Color.DEEPSKYBLUE);
            label.setStyle("-fx-background-color: black;");

        }
        label.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        return label;
    }
}
