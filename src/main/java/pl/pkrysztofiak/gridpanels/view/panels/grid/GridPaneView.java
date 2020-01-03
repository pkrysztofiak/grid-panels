package pl.pkrysztofiak.gridpanels.view.panels.grid;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class GridPaneView extends GridPane {
    
    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    private final GridPaneModel gridPanelModel;
    private GridPaneModel parentPanelModel;
    private GridPaneView parentPanelView;

    {
        setVgap(16);
        setHgap(16);
        setPickOnBounds(false);
    }
    
    public GridPaneView(GridPaneModel gridPanelModel) {
        this.gridPanelModel = gridPanelModel;
        init();
    }
    
    public GridPaneView(GridPaneModel gridPanelModel, GridPaneView parentPanelView, GridPaneModel parentPanelModel) {
        this.gridPanelModel = gridPanelModel;
        this.parentPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        
        init();

        switch (parentPanelModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(gridPanelModel, parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(gridPanelModel, parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            break;
        }
        
        addBehaviour.add(this);

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

    public GridPaneModel getGridPanelModel() {
        return gridPanelModel;
    }
}