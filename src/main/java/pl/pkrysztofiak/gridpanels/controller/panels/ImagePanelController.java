package pl.pkrysztofiak.gridpanels.controller.panels;

import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelView;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ImagePanelModel imagePanel;
    public final ImagePanelView imagePanelView;
    
    public ImagePanelController(ImagePanelModel imagePanel, GridPanelController parentGridPanelController) {
        this.imagePanel = imagePanel;
        this.imagePanelView = new ImagePanelView(imagePanel, parentGridPanelController.gridPanelView, parentGridPanelController.gridPanelModel);
        this.parentGridPanelController = parentGridPanelController;
        
        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelViewRequest);
    }
    
    private void onRemoveImagePanelViewRequest(ActionEvent actionEvent) {
        parentGridPanelController.gridPanelModel.panels.remove(imagePanel);
    }
}