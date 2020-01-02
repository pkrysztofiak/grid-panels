package pl.pkrysztofiak.gridpanels.controller.panels;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPaneController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class GridPanelController {

    private final GridPaneController parentGridPanelController;
    
    public final ImagePanelModel imagePanelModel;
    public GridPanelView imagePanelViewController;
    
    public GridPanelController(ImagePanelModel imagePanelModel, GridPaneController parentGridPaneController) {
        this.imagePanelModel = imagePanelModel;
        imagePanelViewController = new GridPanelView(imagePanelModel, parentGridPaneController.gridPanelView, parentGridPaneController.gridPanelModel);
        
        this.parentGridPanelController = parentGridPaneController;
    }
}