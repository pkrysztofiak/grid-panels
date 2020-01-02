package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ContentPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

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
        
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    private void onPanelModelAdded(PanelModel panel) {
        switch (panel.getType()) {
        case GRID:
            new GridPanelController((GridPanelModel) panel, this);
            break;
        case IMAGE:
            new ImagePanelController((ContentPanelModel) panel, this);
            break;
        default:
            break;
        }
    }
}