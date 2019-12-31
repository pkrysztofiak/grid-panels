package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class HorizontalLayoutBehaviour implements LayoutBehaviour {

    @Override
    public void add(GridPanelView gridPanelView, Node node, int index) {
        gridPanelView.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
        gridPanelView.add(node, index, 0);
    }

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getColumnConstraints().remove(0);
    }
}