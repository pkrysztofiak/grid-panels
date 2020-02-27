package pl.pkrysztofiak.gridpanels.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View {
    
    public final StackPane root = new StackPane();
    private final BorderPane borderPane = new BorderPane(root);
    private final Scene scene = new Scene(borderPane, 400, 400);
    private final Stage stage = new Stage();
    
    public View() {
        stage.setScene(scene);
    }
    
    public void show() {
        stage.show();
    }
    
    public void setLeft(Node node) {
        borderPane.setLeft(node);
    }
}