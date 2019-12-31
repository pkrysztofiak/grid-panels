package pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public interface AddBehaviour {

    void add(GridPanelView gridPanelView, Node node, int index);
}
