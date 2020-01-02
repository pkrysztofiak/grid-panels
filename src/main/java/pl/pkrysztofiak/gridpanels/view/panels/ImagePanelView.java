package pl.pkrysztofiak.gridpanels.view.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class ImagePanelView extends StackPane {

    private final Button button = new Button("Remove");
    public final Observable<ActionEvent> removeObservable = JavaFxObservable.actionEventsOf(button);
    
    private final GridPaneView gridPanelView;
    private final GridPaneModel parentPanelModel;
    
    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    public ImagePanelView(ImagePanelModel imagePanelModel, GridPaneView parentPanelView, GridPaneModel parentPanelModel) {
        this.gridPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        
        
        setStyle("-fx-background-color: yellow;");
        getChildren().add(button);
        
        switch (parentPanelModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(imagePanelModel, parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(imagePanelModel, parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            break;
        }
        
        parentPanelModel.panelRemovedObservable.filter(imagePanelModel::equals).subscribe(panel -> {
            removeBehaviour.remove(this);
        });
        
        addBehaviour.add(this);
    }
}