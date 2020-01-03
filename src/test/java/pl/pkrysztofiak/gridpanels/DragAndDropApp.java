package pl.pkrysztofiak.gridpanels;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DragAndDropApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane sourceStackPane = new StackPane();
        StackPane targetStackPane = new StackPane();
        
        HBox hBox = new HBox(sourceStackPane, targetStackPane);

        
        
    }
}