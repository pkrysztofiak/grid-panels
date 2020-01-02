package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public class HorizontalAdd extends AddBehaviourAbstract {

    public HorizontalAdd(PanelModel panelModel, GridPaneView prarentPanelView) {
        super(panelModel, prarentPanelView);
    }

    @Override
    public void add(Node node) {
        gridPanelView.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
        int index = gridPanelView.getGridPanelModel().indexOf(panelModel);
        gridPanelView.add(node, index, 0);
    }
}