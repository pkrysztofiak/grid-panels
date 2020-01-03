package pl.pkrysztofiak.gridpanels.view.panels.grid;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

public abstract class GridPanelViewFxml implements Initializable {

    protected StackPane root;
    
    @FXML
    protected StackPane topPane;
    
    @FXML
    protected StackPane rightPane;
    
    @FXML
    protected StackPane bottomPane;
    
    @FXML
    protected StackPane leftPane;
    
    @FXML
    protected StackPane centerPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("GridPanelViewFxml.initialize()");
        
        centerPane.setOnDragEntered(event -> {
            event.acceptTransferModes(TransferMode.ANY);
            System.out.println("centerPane MouseDragEntered");
            centerPane.setStyle("-fx-background-color: red;");
            event.consume();
        });
        
        centerPane.setOnDragOver(event -> {
            System.out.println("centerPane DragOver");
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });
        
        centerPane.setOnDragExited(event -> {
            System.out.println("centerPane MouseDragExited");
            centerPane.setStyle("");
            event.consume();
        });
        
        centerPane.setOnMouseClicked(event -> {
            System.out.println("centerPane mouseClicked");
        });
    }
}