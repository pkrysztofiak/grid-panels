package pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class VertiacalRemove implements RemoveBehaviour {

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        System.out.println("remove row constraints");
        gridPanelView.getChildren().remove(node);
        gridPanelView.getRowConstraints().remove(0);
    }
}