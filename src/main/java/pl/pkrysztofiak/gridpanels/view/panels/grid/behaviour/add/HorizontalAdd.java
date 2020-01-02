package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class HorizontalAdd extends AddBehaviourAbstract {

    public HorizontalAdd(GridPanelView gridPanelView) {
        super(gridPanelView);
    }

    @Override
    public void add(Node node, int index) {
        gridPanelView.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
        gridPanelView.add(node, index, 0);
    }
}