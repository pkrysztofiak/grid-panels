package pl.pkrysztofiak.gridpanels.controller.panels;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelViewController;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ImagePanelModel imagePanelModel;
//    public final ImagePanelView imagePanelView;
    public ImagePanelViewController imagePanelViewController;
    
    public ImagePanelController(ImagePanelModel imagePanelModel, GridPanelController parentGridPanelController) {
        this.imagePanelModel = imagePanelModel;
//        this.imagePanelView = new ImagePanelView(imagePanelModel, parentGridPanelController.gridPanelView, parentGridPanelController.gridPanelModel);
   
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/panels/ImagePanelView.fxml"));
        fxmlLoader.setControllerFactory(param -> new ImagePanelViewController(imagePanelModel, parentGridPanelController.gridPanelView, parentGridPanelController.gridPanelModel));
        
        try {
            fxmlLoader.load();
            imagePanelViewController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.parentGridPanelController = parentGridPanelController;
        
        //TODO rewrite remove
//        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelViewRequest);
    }
    
    private void onRemoveImagePanelViewRequest(ActionEvent actionEvent) {
        parentGridPanelController.gridPanelModel.panels.remove(imagePanelModel);
    }
}