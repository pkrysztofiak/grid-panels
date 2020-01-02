package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import pl.pkrysztofiak.gridpanels.controller.panels.GridPanelController;
import pl.pkrysztofiak.gridpanels.controller.panels.PanelsController;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public class GridPaneController {

    private final PanelsController panelsController;
    private GridPaneController parentController;
    
    public final GridPaneModel gridPanelModel;
    public final GridPaneView gridPanelView;
    
    public GridPaneController(GridPaneModel gridPaneModel, PanelsController panelsController) {
        this.gridPanelModel = gridPaneModel;
        this.gridPanelView = new GridPaneView(gridPaneModel);
        this.panelsController = panelsController;
        
        gridPaneModel.panels.forEach(this::onPanelModelAdded);
        gridPaneModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    public GridPaneController(GridPaneModel gridPanelModel, GridPaneController parentController, PanelsController panelsController) {
        this.gridPanelModel = gridPanelModel;
        this.parentController = parentController;
        this.gridPanelView = new GridPaneView(gridPanelModel, parentController.gridPanelView, parentController.gridPanelModel);
        this.panelsController = panelsController;
        
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    private void onPanelModelAdded(PanelModel panel) {
        switch (panel.getType()) {
        case GRID:
            new GridPaneController((GridPaneModel) panel, this, panelsController);
            break;
        default:
            new GridPanelController((ImagePanelModel) panel, this);
            break;
        }
    }
}