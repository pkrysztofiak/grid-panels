package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class VerticalLayoutBehaviour implements LayoutBehaviour {

    @Override
    public void add(GridPanelView gridPanelView, Node node, int index) {
        gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPanelView.add(node, 0, index);
    }

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getRowConstraints().remove(0);
    }
}