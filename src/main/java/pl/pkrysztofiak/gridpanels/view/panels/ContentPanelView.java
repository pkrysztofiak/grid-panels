package pl.pkrysztofiak.gridpanels.view.panels;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class ContentPanelView extends ContentPanelViewFxml {

    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    private final ImagePanelModel model;
    private final GridPaneModel parentModel;
    
    public ContentPanelView(ImagePanelModel model, GridPaneView parentPanelView, GridPaneModel parentModel) {
        this.model = model;
        this.parentModel = parentModel;
        
        switch (parentModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(model, parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(model, parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            break;
        }

        parentModel.panelRemovedObservable.filter(model::equals).subscribe(panel -> {
            removeBehaviour.remove(root);
        });
        
        addBehaviour.add(root);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        removeRequestObservable.subscribe(this::onRemoveRequest);
    }
    
    private void onRemoveRequest(ActionEvent event) {
        System.out.println("onRemoveRequest");
        parentModel.remove(model);
    }
}