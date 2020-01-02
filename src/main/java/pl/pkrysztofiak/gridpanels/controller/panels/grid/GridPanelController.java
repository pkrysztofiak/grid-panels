package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import javafx.scene.Node;
import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class GridPanelController implements AddBehaviour, RemoveBehaviour {

    private GridPanelController parentController;
    
    public final GridPanelModel gridPanelModel;
    public final GridPanelView gridPanelView;
    
//    private AddBehaviour addBehaviour;
//    private RemoveBehaviour removeBehaviour;
    
    public GridPanelController(GridPanelModel gridPanelModel) {
        this.gridPanelModel = gridPanelModel;
        this.gridPanelView = new GridPanelView(gridPanelModel);
        
//        gridPanelModel.orientationObservable.subscribe(orientation -> {
//            switch (orientation) {
//            case HORIZONTAL:
//                gridPanelView.setAddBehaviour(new HorizontalPanelAdd());
//                break;
//            case VERTICAL:
//                gridPanelView.setAddBehaviour(new VerticalPanelAdd());
//                break;
//            default:
//                break;
//            }
//        });
        
//        switch (gridPanelModel.getOrientation()) {
//        case HORIZONTAL:
//            gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
//            break;
//        case VERTICAL:
//            gridPanelView.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
//            break;
//        default:
//            break;
//        }
        
//        gridPanelModel.orientationObservable.subscribe(orientation -> {
//            switch (orientation) {
//            case HORIZONTAL:
//                addBehaviour = new HorizontalAdd();
//                removeBehaviour = new HorizontalRemove();
//                
//                break;
//            case VERTICAL:
//                addBehaviour = new VertiacalAdd();
//                removeBehaviour = new VertiacalRemove();
//                break;
//            default:
//                break;
//            }
//        });
        
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    public GridPanelController(GridPanelModel gridPanelModel, GridPanelController parentController) {
//        this(gridPanelModel);
        System.out.println("GridPanelController()");
        this.gridPanelModel = gridPanelModel;
        this.parentController = parentController;
        this.gridPanelView = new GridPanelView(gridPanelModel, parentController.gridPanelView, parentController.gridPanelModel);
        
//        this.gridPanelView.setParentPanelModel(parentController.gridPanelModel);
//        this.gridPanelView.setParentPanelView(parentController.gridPanelView);
        
//        parentController.addPanelView(gridPanelView, gridPanelModel);
//        parentController.gridPanelModel.panelRemovedObservable.filter(gridPanelModel::equals).subscribe(this::onRemoved);
        gridPanelModel.panels.forEach(this::onPanelModelAdded);
        gridPanelModel.panelAddedObservable.subscribe(this::onPanelModelAdded);
    }
    
    public void addPanelView(Node node, PanelModel panelModel) {
//        gridPanelView.addPanelView(node, gridPanelModel.indexOf(panelModel));
//        addBehaviour.add(gridPanelView, node, gridPanelModel.indexOf(panelModel));
    }
    
    private void onPanelModelAdded(PanelModel panel) {
        switch (panel.getType()) {
        case GRID:
            new GridPanelController((GridPanelModel) panel, this);
            break;
        case IMAGE:
            new ImagePanelController((ImagePanelModel) panel, this);
            break;
        default:
            break;
        }
    }
    
    private void onRemoved(PanelModel panel) {
        parentController.remove(parentController.gridPanelView, gridPanelView);
    }
    
    @Override
    public void add(GridPanelView gridPanelView, Node node, int index) {
//        addBehaviour.add(gridPanelView, node, index);
    }

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
//        removeBehaviour.remove(gridPanelView, node);
    }
}