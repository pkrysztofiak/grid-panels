package pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public interface RemoveBehaviour {

    void remove(GridPanelView gridPanelView, Node node);
}