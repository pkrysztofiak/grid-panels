package pl.pkrysztofiak.gridpanels.view.panels.content.behaviour.add;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.view.panels.PanelsView;

public class AddBehaviour {

    private final PanelsView panelsView;
    
    public AddBehaviour(PanelsView panelsView) {
        this.panelsView = panelsView;
    }
    
    public void add(Node node) {
        panelsView.getChildren().add(node);
    }
}
