package pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class VertiacalAdd implements AddBehaviour {

    @Override
    public void add(GridPanelView gridPanelView, Node node, int index) {
        gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPanelView.add(node, 0, index);
    }
}