package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class VerticalAdd extends AddBehaviourAbstract {

    public VerticalAdd(PanelModel panelModel, GridPanelView prarentPanelView) {
        super(panelModel, prarentPanelView);
    }

    @Override
    public void add(Node node) {
        gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        int index = gridPanelView.getGridPanelModel().indexOf(panelModel);
        gridPanelView.add(node, 0, index);
    }
}