package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class VerticalPanelAdd implements AddPanelBehaviour {

    @Override
    public void addPanelView(GridPanelView gridPanelView, Node node, int index) {
        gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPanelView.add(node, 0, index);
    }
}