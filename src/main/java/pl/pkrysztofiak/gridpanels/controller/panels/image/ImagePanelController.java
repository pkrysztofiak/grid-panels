package pl.pkrysztofiak.gridpanels.controller.panels.image;

import pl.pkrysztofiak.gridpanels.controller.panels.PanelsController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;
import pl.pkrysztofiak.gridpanels.view.panels.image.ImagePanelView;

public class ImagePanelController {
    
    public ImagePanelController(ImagePanelModel imagePanelModel, PanelsController panelsController, GridPanelView gridPanelView) {
        ImagePanelView imagePanelView = new ImagePanelView(imagePanelModel, panelsController.panelsView, gridPanelView);
        imagePanelView.init();
    }
}
