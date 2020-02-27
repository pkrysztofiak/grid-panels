package pl.pkrysztofiak.gridpanels;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DragAndDropApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane source = new StackPane();
        source.setStyle("-fx-background-color: yellow;");
        StackPane target = new StackPane();
        target.setStyle("-fx-background-color: blue;");
        
        HBox hBox = new HBox(source, target);
        HBox.setHgrow(source, Priority.ALWAYS);
        HBox.setHgrow(target, Priority.ALWAYS);
        
        Scene scene = new Scene(hBox, 400, 400);
        stage.setScene(scene);
        stage.show();
        
        source.setOnDragDetected(event -> {
            System.out.println("source DragDetected");
            Dragboard dragboard = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString("one");
            dragboard.setContent(clipboardContent);
            event.consume();
        });
        
        source.setOnDragDone(event -> {
            System.out.println("source DragDone completed=" + event.isDropCompleted() + ", accepted=" + event.isAccepted());
        });
        
        
        target.setOnDragOver(event -> {
            System.out.println("target DragOver");
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });
        
        target.setOnDragDropped(event -> {
            System.out.println("target DragDropped");
            event.setDropCompleted(true);
            event.consume();
        });
    }
}