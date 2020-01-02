package pl.pkrysztofiak.gridpanels.controller.panels;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ContentPanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.ContentPanelView;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ContentPanelModel imagePanelModel;
    public ContentPanelView imagePanelViewController;
    
    public ImagePanelController(ContentPanelModel imagePanelModel, GridPanelController parentGridPanelController) {
        this.imagePanelModel = imagePanelModel;
        imagePanelViewController = new ContentPanelView(imagePanelModel, parentGridPanelController.gridPanelView, parentGridPanelController.gridPanelModel);
        
        this.parentGridPanelController = parentGridPanelController;
    }
}