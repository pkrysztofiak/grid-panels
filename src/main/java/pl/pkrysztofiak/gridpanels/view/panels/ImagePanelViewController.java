package pl.pkrysztofiak.gridpanels.view.panels;

import java.net.URL;
import java.util.ResourceBundle;

import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove.VerticalRemove;

public class ImagePanelViewController extends ImagePanelViewFxml {

    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    public ImagePanelViewController(ImagePanelModel imagePanelModel, GridPanelView parentPanelView, GridPanelModel parentPanelModel) {
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
            removeBehaviour.remove(root);
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBehaviour.add(root);
    }
}