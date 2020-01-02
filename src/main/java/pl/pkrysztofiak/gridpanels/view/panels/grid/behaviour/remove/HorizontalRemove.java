package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class HorizontalRemove extends RemoveBehaviourAbstract {

    public HorizontalRemove(GridPanelView gridPanelView) {
        super(gridPanelView);
    }

    @Override
    public void remove(Node node) {
        gridPanelView.getChildren().remove(node);
        gridPanelView.getColumnConstraints().remove(0);
    }
}