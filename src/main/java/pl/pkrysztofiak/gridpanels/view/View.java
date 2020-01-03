package pl.pkrysztofiak.gridpanels.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public class View {

    public final StackPane root = new StackPane();
    private final Scene scene = new Scene(root, 400, 400);
    private final Stage stage = new Stage();
    
    public View() {
        stage.setScene(scene);
    }
    
    public void setMainPanel(GridPaneView gridPanelView) {
        root.getChildren().add(gridPanelView);
    }
    
    public void show() {
        stage.show();
    }
}