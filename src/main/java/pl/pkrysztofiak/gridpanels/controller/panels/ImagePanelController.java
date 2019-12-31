package pl.pkrysztofiak.gridpanels.controller.panels;

import io.reactivex.subjects.PublishSubject;
import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.model.panels.Panel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelView;

public class ImagePanelController {

    private final GridPanelController parentGridPanelController;
    
    public final ImagePanel imagePanel;
    public final ImagePanelView imagePanelView;
    
    public final PublishSubject<ImagePanel> removeRequestObservable = PublishSubject.create();
    
    public ImagePanelController(ImagePanel imagePanel, GridPanelController parentGridPanelController) {
        this.imagePanel = imagePanel;
        this.imagePanelView = new ImagePanelView(imagePanel);
        this.parentGridPanelController = parentGridPanelController;
        
        parentGridPanelController.add(parentGridPanelController.gridPanelView, imagePanelView, parentGridPanelController.gridPanel.panels.indexOf(imagePanel));
        
        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelViewRequest);
        
        parentGridPanelController.gridPanel.panelRemovedObservable.filter(imagePanel::equals).subscribe(this::onRemoveImagePanelView);
    }
    
    private void onRemoveImagePanelViewRequest(ActionEvent actionEvent) {
        removeRequestObservable.onNext(imagePanel);
    }
    
    private void onRemoveImagePanelView(Panel panel) {
        
    }
}