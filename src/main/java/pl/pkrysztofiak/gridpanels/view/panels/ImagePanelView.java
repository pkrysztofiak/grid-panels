package pl.pkrysztofiak.gridpanels.view.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class ImagePanelView extends StackPane {

    private final Button button = new Button("Remove");
    public final Observable<ActionEvent> removeObservable = JavaFxObservable.actionEventsOf(button);
    
    private final GridPanelView gridPanelView;
    private final GridPanelModel parentPanelModel;
    
    public ImagePanelView(ImagePanelModel imagePanel, GridPanelView parentPanelView, GridPanelModel parentPanelModel) {
        this.gridPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        
        parentPanelModel.panelRemovedObservable.filter(imagePanel::equals).subscribe(panel -> {
            gridPanelView.removePanel(this);
        });
        
        setStyle("-fx-background-color: yellow;");
        getChildren().add(button);
        
        parentPanelView.addPanelView(this, parentPanelModel.indexOf(imagePanel));
    }
}