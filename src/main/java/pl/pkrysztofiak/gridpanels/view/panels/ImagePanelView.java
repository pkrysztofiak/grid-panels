package pl.pkrysztofiak.gridpanels.view.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class ImagePanelView extends StackPane {

    private final Button button = new Button("Remove");
    public final Observable<ActionEvent> removeObservable = JavaFxObservable.actionEventsOf(button);
    
    private final GridPanelView gridPanelView;
    private final GridPanelModel parentPanelModel;
    
    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    public ImagePanelView(ImagePanelModel imagePanel, GridPanelView parentPanelView, GridPanelModel parentPanelModel) {
        this.gridPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        
        parentPanelModel.panelRemovedObservable.filter(imagePanel::equals).subscribe(panel -> {
            removeBehaviour.remove(this);
        });
        
        setStyle("-fx-background-color: yellow;");
        getChildren().add(button);
        
        switch (parentPanelModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            break;
        }
        
        addBehaviour.add(this, parentPanelModel.indexOf(imagePanel));
    }
}