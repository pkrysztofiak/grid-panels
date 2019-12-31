package pl.pkrysztofiak.gridpanels.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class View {

    private final StackPane root = new StackPane();
    private final Scene scene = new Scene(root, 400, 400);
    private final Stage stage = new Stage();
    
    
    public View() {
        stage.setScene(scene);
    }
    
    public void setMainPanel(GridPanelView gridPanelView) {
        root.getChildren().add(gridPanelView);
    }
    
    public void show() {
        stage.show();
    }
}
