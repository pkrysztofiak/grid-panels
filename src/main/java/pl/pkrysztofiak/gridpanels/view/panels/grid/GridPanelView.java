package pl.pkrysztofiak.gridpanels.view.panels.grid;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddPanelBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalPanelAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalPanelAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalPanelRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemovePanelBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalPanelRemove;

public class GridPanelView extends GridPane implements AddPanelBehaviour {
    
    private AddPanelBehaviour addBehaviour;
    private RemovePanelBehaviour removeBehaviour;
    
    private final GridPanelModel gridPanelModel;
    private GridPanelModel parentPanelModel;
    private GridPanelView parentPanelView;

    {
        setVgap(16);
        setHgap(16);
    }
    
    public GridPanelView(GridPanelModel gridPanelModel) {
        this.gridPanelModel = gridPanelModel;
        init();
    }
    
    public GridPanelView(GridPanelModel gridPanelModel, GridPanelView parentPanelView, GridPanelModel parentPanelModel) {
        this.gridPanelModel = gridPanelModel;
        this.parentPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        init();
        
        addBehaviour = parentPanelView;
        
        parentPanelView.addPanelView(this, parentPanelModel.indexOf(gridPanelModel));

        parentPanelModel.panelRemovedObservable.filter(gridPanelModel::equals).subscribe(panelModel -> {
            parentPanelView.removePanel(this);
        });
    }
    
    private void init() {
        gridPanelModel.orientationObservable.subscribe(orientation -> {
            switch (orientation) {
            case HORIZONTAL:
                addBehaviour = new HorizontalPanelAdd();
                removeBehaviour = new HorizontalPanelRemove();
                getRowConstraints().setAll(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
                break;
            case VERTICAL:
                addBehaviour = new VerticalPanelAdd();
                removeBehaviour = new VerticalPanelRemove();
                getColumnConstraints().setAll(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
                break;
            default:
                break;
            }
        });
    }
    
    public void removePanel(Node node) {
        removeBehaviour.remove(this, node);
    }
    
    public void addPanelView(Node node, int index) {
        addBehaviour.addPanelView(this, node, index);
    }

    @Override
    public void addPanelView(GridPanelView gridPanelView, Node node, int index) {
        addBehaviour.addPanelView(gridPanelView, node, index);
    }
}