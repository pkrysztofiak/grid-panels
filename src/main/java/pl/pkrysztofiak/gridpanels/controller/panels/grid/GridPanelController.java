package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;

public class GridPanelController {

    private GridPanelController parentController;
    
    public final GridPanelModel gridPanelModel;
    public final GridPanelView gridPanelView;
    
    public GridPanelController(GridPanelModel gridPanelModel) {
        this.gridPanelModel = gridPanelModel;
        this.gridPanelView = new GridPanelView(gridPanelModel);
        
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    public GridPanelController(GridPanelModel gridPanelModel, GridPanelController parentController) {
        this.gridPanelModel = gridPanelModel;
        this.parentController = parentController;
        this.gridPanelView = new GridPanelView(gridPanelModel, parentController.gridPanelView, parentController.gridPanelModel);
//        this.gridPanelView.setAddBehaviour(parentController.gridPanelView);
        
        
        
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    private AddBehaviour createAddBehaviour(GridPanelModel gridPanelModel) {
        switch (gridPanelModel.getOrientation()) {
        case HORIZONTAL:
            return new HorizontalAdd(parentController.gridPanelView);
        case VERTICAL:
            return new VerticalAdd(parentController.gridPanelView);
        default:
            break;
        }
        return null;
    }
    
    private void onPanelModelAdded(PanelModel panel) {
        switch (panel.getType()) {
        case GRID:
            new GridPanelController((GridPanelModel) panel, this);
            break;
        case IMAGE:
            new ImagePanelController((ImagePanelModel) panel, this);
            break;
        default:
            break;
        }
    }
}