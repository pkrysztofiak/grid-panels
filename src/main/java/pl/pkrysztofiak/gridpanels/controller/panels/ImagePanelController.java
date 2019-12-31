package pl.pkrysztofiak.gridpanels.controller.panels;

import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.model.panels.Panel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelView;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ImagePanel imagePanel;
    public final ImagePanelView imagePanelView;
    
    public ImagePanelController(ImagePanel imagePanel, GridPanelController parentGridPanelController) {
        System.out.println("ImagePanelController()");
        this.imagePanel = imagePanel;
        this.imagePanelView = new ImagePanelView(imagePanel);
        this.parentGridPanelController = parentGridPanelController;
        
        parentGridPanelController.add(parentGridPanelController.gridPanelView, imagePanelView, parentGridPanelController.gridPanel.panels.indexOf(imagePanel));
        
        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelViewRequest);
        
        parentGridPanelController.gridPanel.panelRemovedObservable.filter(imagePanel::equals).subscribe(this::onRemoveImagePanelView);
    }
    
    private void onRemoveImagePanelViewRequest(ActionEvent actionEvent) {
        parentGridPanelController.gridPanel.panels.remove(imagePanel);
    }
    
    private void onRemoveImagePanelView(Panel panel) {
        parentGridPanelController.remove(parentGridPanelController.gridPanelView, imagePanelView);
    }
}