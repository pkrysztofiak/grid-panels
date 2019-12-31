package pl.pkrysztofiak.gridpanels.view.panels.grid;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddPanelBehaviour;

public class GridPanelView extends GridPane {
    
    private AddPanelBehaviour addBehaviour;
    
    private final GridPanelModel gridPanel;
    
    public GridPanelView(GridPanelModel gridPanel) {
        this.gridPanel = gridPanel;
        
        setVgap(16);
        setHgap(16);
        
        gridPanel.panelRemovedObservable.subscribe(panel -> {
            
        });
    }
    
    public void addPanelView(Node node, int index) {
        addBehaviour.addPanelView(this, node, index);
    }

    public AddPanelBehaviour getAddBehaviour() {
        return addBehaviour;
    }

    public void setAddBehaviour(AddPanelBehaviour addBehaviour) {
        this.addBehaviour = addBehaviour;
    }
}