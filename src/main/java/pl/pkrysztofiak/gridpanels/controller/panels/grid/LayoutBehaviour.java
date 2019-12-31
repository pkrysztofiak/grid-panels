package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public interface LayoutBehaviour {

    void add(GridPanelView gridPanelView, Node node, int index);
    
    void remove(GridPanelView gridPanelView, Node node);
}