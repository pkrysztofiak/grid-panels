package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class HorizontalPanelRemove implements RemovePanelBehaviour {

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getColumnConstraints().remove(0);
    }
}