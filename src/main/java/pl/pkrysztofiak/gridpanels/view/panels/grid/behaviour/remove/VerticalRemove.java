package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public class VerticalRemove extends RemoveBehaviourAbstract {

    public VerticalRemove(GridPaneView gridPanelView) {
        super(gridPanelView);
    }

    @Override
    public void remove(Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getRowConstraints().remove(0);
    }
}