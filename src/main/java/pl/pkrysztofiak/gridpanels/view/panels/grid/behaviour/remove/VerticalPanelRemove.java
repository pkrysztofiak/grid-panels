package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class VerticalPanelRemove implements RemovePanelBehaviour {

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getRowConstraints().remove(0);
    }
}