package pl.pkrysztofiak.gridpanels.view.panels;

import javafx.scene.layout.GridPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanel;

public class GridPanelView extends GridPane {
    
    private final GridPanel gridPanel;
    
    public GridPanelView(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        
        setVgap(16);
        setHgap(16);
        
        gridPanel.panelRemovedObservable.subscribe(panel -> {
            
        });
    }
}
