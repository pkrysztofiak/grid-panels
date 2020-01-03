package pl.pkrysztofiak.gridpanels;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GridPanePickOnBoundsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane root = new StackPane();
        
        StackPane stackPane = new StackPane();
        stackPane.setOnMouseClicked(event -> {
            System.out.println("stackPane mouseClicked");
        });
        
        GridPane gridPane = new GridPane();
//        gridPane.setPickOnBounds(false);
        gridPane.setOnMouseClicked(event -> {
            System.out.println("gridPane mouseClicked");
        });
        
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
        StackPane gpStackPane = new StackPane();
        gpStackPane.setPickOnBounds(false);
        gridPane.add(gpStackPane, 0, 0);
        
        root.getChildren().addAll(stackPane, gridPane);
        
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}