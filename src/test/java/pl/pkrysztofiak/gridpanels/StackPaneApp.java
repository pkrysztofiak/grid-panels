package pl.pkrysztofiak.gridpanels;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane mainStackPane = new StackPane();
        
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        
        mainStackPane.setOnMouseClicked(event -> {
            System.out.println("main");
        });
        
        stackPane1.setOnMouseClicked(event -> {
            System.out.println("1");
        });
        
        stackPane2.setOnMouseClicked(event -> {
            System.out.println("2");
        });
        stackPane2.setPickOnBounds(false);
        
        mainStackPane.getChildren().addAll(stackPane1, stackPane2);
        
        Scene scene = new Scene(mainStackPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}