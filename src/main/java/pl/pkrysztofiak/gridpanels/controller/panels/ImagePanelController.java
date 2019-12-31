package pl.pkrysztofiak.gridpanels.controller.panels;

import io.reactivex.subjects.PublishSubject;
import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.view.panels.ImagePanelView;

public class ImagePanelController {

    public final ImagePanel imagePanel;
    public final ImagePanelView imagePanelView = new ImagePanelView();
    
    public final PublishSubject<ImagePanel> removeObservable = PublishSubject.create();
    
    public ImagePanelController(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
        
        imagePanelView.removeObservable.subscribe(this::onRemoveImagePanelView);
    }
    
    private void onRemoveImagePanelView(ActionEvent actionEvent) {
        removeObservable.onNext(imagePanel);
    }
}