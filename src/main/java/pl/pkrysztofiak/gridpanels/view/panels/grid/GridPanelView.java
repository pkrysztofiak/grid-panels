package pl.pkrysztofiak.gridpanels.view.panels.grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;
import pl.pkrysztofiak.gridpanels.view.transfer.ImageTransfer;

public class GridPanelView extends GridPanelViewFxml {

    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    private final PanelModel model;
    private final GridPaneModel parentModel;
    
    public GridPanelView(PanelModel panelModel, GridPaneView parentPanelView, GridPaneModel parentModel) {
        this.model = panelModel;
        this.parentModel = parentModel;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/panels/GridPanelView.fxml"));
        try {
            fxmlLoader.setController(this);
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        switch (parentModel.getOrientation()) {
        case HORIZONTAL:
            addBehaviour = new HorizontalAdd(panelModel, parentPanelView);
            removeBehaviour = new HorizontalRemove(parentPanelView);
            break;
        case VERTICAL:
            addBehaviour = new VerticalAdd(panelModel, parentPanelView);
            removeBehaviour = new VerticalRemove(parentPanelView);
            break;
        default:
            break;
        }

        parentModel.panelRemovedObservable.filter(panelModel::equals).subscribe(panel -> {
            removeBehaviour.remove(root);
        });
        
        root.setPickOnBounds(false);
        parentPanelView.setPickOnBounds(false);
        
        addBehaviour.add(root);
        
        root.localToSceneTransformProperty();
        
        ImageTransfer.startedPublishable.subscribe(nothing -> {
            System.out.println("root.setPickOnBounds(true)");
            root.setPickOnBounds(true);
        });
        
        ImageTransfer.finishedPublishable.subscribe(nothing -> {
            System.out.println("root.setPickOnBounds(false)");
            root.setPickOnBounds(false);
        });
    }
    
    public StackPane getRoot() {
        return root;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
//        super.initialize(location, resources);
//        removeRequestObservable.subscribe(this::onRemoveRequest);
    }
    
    private void onRemoveRequest(ActionEvent event) {
        System.out.println("onRemoveRequest");
        parentModel.remove(model);
    }
}