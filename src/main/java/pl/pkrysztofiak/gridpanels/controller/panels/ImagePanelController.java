package pl.pkrysztofiak.gridpanels.controller.panels;

import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelView;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ImagePanelModel imagePanel;
    public final ImagePanelView imagePanelView;
    
    public ImagePanelController(ImagePanelModel imagePanel, GridPanelController parentGridPanelController) {
        System.out.println("ImagePanelController()");
        this.imagePanel = imagePanel;
        this.imagePanelView = new ImagePanelView(imagePanel, parentGridPanelController.gridPanelView, parentGridPanelController.gridPanelModel);
        this.parentGridPanelController = parentGridPanelController;
        
//        parentGridPanelController.add(parentGridPanelController.gridPanelView, imagePanelView, parentGridPanelController.gridPanelModel.panels.indexOf(imagePanel));
        
        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelViewRequest);
//        parentGridPanelController.gridPanelModel.panelRemovedObservable.filter(imagePanel::equals).subscribe(this::onRemoveImagePanelView);
    }
    
    private void onRemoveImagePanelViewRequest(ActionEvent actionEvent) {
        parentGridPanelController.gridPanelModel.panels.remove(imagePanel);
    }
    
    private void onRemoveImagePanelView(PanelModel panel) {
        parentGridPanelController.remove(parentGridPanelController.gridPanelView, imagePanelView);
    }
}