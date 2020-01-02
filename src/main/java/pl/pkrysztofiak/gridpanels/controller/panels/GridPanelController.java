package pl.pkrysztofiak.gridpanels.controller.panels;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPaneController;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class GridPanelController {

    private final GridPaneController parentGridPanelController;
    
    public final PanelModel panelModel;
    public GridPanelView gridPanelView;
    
    public GridPanelController(PanelModel panelModel, GridPaneController parentGridPaneController, PanelsController panelsController) {
        this.panelModel = panelModel;
        gridPanelView = new GridPanelView(panelModel, parentGridPaneController.gridPanelView, parentGridPaneController.gridPanelModel);
        
        this.parentGridPanelController = parentGridPaneController;
        
        switch (panelModel.getType()) {
        case IMAGE:
            
            break;
        default:
            break;
        }
    }
}