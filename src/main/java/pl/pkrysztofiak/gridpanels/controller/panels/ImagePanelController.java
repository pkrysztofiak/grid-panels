package pl.pkrysztofiak.gridpanels.controller.panels;

import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.content.ImagePanelView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class ImagePanelController {
    
    public ImagePanelController(ImagePanelModel imagePanelModel, PanelsController panelsController, GridPanelView gridPanelView) {
        ImagePanelView imagePanelView = new ImagePanelView(imagePanelModel, panelsController.panelsView, gridPanelView);
        imagePanelView.init();
    }
}
