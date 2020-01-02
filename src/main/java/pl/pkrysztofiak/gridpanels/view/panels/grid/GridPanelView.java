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
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class GridPanelView extends GridPane {
    
    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
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

        switch (parentPanelModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            System.out.println("default");
            break;
        }
        
        addBehaviour.add(this, parentPanelModel.indexOf(gridPanelModel));

        parentPanelModel.panelRemovedObservable.filter(gridPanelModel::equals).subscribe(panelModel -> {
            removeBehaviour.remove(this);
        });
    }

    private void init() {
        switch (gridPanelModel.getOrientation()) {
        case HORIZONTAL:
            getRowConstraints().setAll(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
            break;
        case VERTICAL:
            getColumnConstraints().setAll(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
            break;
        default:
            break;
        }
    }
    
    public void removePanel(Node node) {
        removeBehaviour.remove(node);
    }

    public void setAddBehaviour(AddBehaviour addBehaviour) {
        this.addBehaviour = addBehaviour;
    }

    public GridPanelModel getGridPanelModel() {
        return gridPanelModel;
    }
}