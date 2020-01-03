package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.controller.panels.PanelsController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

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
            new ImagePanelController((ImagePanelModel) panelModel, panelsController, gridPanelView);
            break;
        default:
            break;
        }
    }
}