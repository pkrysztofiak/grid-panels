package pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class HorizontalRemove implements RemoveBehaviour {

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        System.out.println("remove column constraints");
        gridPanelView.getChildren().remove(node);
        gridPanelView.getColumnConstraints().remove(0);
    }
}