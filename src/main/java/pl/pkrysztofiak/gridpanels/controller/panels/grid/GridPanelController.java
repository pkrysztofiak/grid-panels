package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add.HorizontalAdd;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.add.VertiacalAdd;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove.HorizontalRemove;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove.RemoveBehaviour;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.behaviour.remove.VertiacalRemove;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.model.panels.Panel;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class GridPanelController implements AddBehaviour, RemoveBehaviour{

    public final GridPanel gridPanel;
    public final GridPanelView gridPanelView;
    
    private AddBehaviour addBehaviour;
    private RemoveBehaviour removeBehaviour;
    
    
    public GridPanelController(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        this.gridPanelView = new GridPanelView(gridPanel);
        
        switch (gridPanel.getOrientation()) {
        case HORIZONTAL:
            gridPanelView.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
            break;
        case VERTICAL:
            gridPanelView.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
            break;
        default:
            break;
        }
        
        gridPanel.orientationObservable.subscribe(orientation -> {
            switch (orientation) {
            case HORIZONTAL:
                addBehaviour = new HorizontalAdd();
                removeBehaviour = new HorizontalRemove();
                
                break;
            case VERTICAL:
                addBehaviour = new VertiacalAdd();
                removeBehaviour = new VertiacalRemove();
                break;
            default:
                break;
            }
        });
        
        gridPanel.panelAddedObservable.subscribe(this::onPanelAdded);
        gridPanel.panelRemovedObservable.subscribe(this::onPanelRemoved);
        
        initPanels(gridPanel.panels);
    }
    
    public GridPanelController(GridPanel gridPanel, GridPanelController parentGridPanelController) {
        this(gridPanel);
        parentGridPanelController.add(parentGridPanelController.gridPanelView, gridPanelView, parentGridPanelController.gridPanel.panels.indexOf(gridPanel));
    }

    private void initPanels(List<Panel> panels) {
        for (Panel panel : panels) {
            onPanelAdded(panel);
        }
    }
    
    private void onPanelAdded(Panel panel) {
        switch (panel.getType()) {
        case GRID:
            onGridPanelAdded((GridPanel) panel);
            break;
        case IMAGE:
            onImagePanelAdded((ImagePanel) panel);
            break;
        default:
            break;
        }
    }
    
    private void onPanelRemoved(Panel panel) {
        switch (panel.getType()) {
        case GRID:
            onGridPanelRemoved((GridPanel) panel);
            break;
        case IMAGE:
            onImagePanelRemoved((ImagePanel) panel);
            break;
        default:
            break;
        }
    }
    
    private void onGridPanelAdded(GridPanel gridPanel) {
        GridPanelController gridPanelController = new GridPanelController(gridPanel, this);
//        layoutBehaviour.add(gridPanelView, gridPanelController.gridPanelView, this.gridPanel.panels.indexOf(gridPanel));
    }
    
    private void onImagePanelAdded(ImagePanel imagePanel) {
        ImagePanelController imagePanelController = new ImagePanelController(imagePanel, this);
    }
    
    private void onGridPanelRemoved(GridPanel gridPanel) {
//        GridPanelController gridPanelController = gridPanelToController.remove(gridPanel);
//        layoutBehaviour.remove(gridPanelView, gridPanelController.gridPanelView);
    }
    
    private void onImagePanelRemoved(ImagePanel imagePanel) {
//        ImagePanelController imagePanelController = imagePanelToController.remove(imagePanel);
//        layoutBehaviour.remove(gridPanelView, imagePanelController.imagePanelView);
    }
    
    @Override
    public void add(GridPanelView gridPanelView, Node node, int index) {
        addBehaviour.add(gridPanelView, node, index);
    }

    @Override
    public void remove(GridPanelView gridPanelView, Node node) {
        removeBehaviour.remove(gridPanelView, node);
    }
}