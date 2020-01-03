package pl.pkrysztofiak.gridpanels.controller;

import pl.pkrysztofiak.gridpanels.controller.panels.PanelsController;
import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPaneController;
import pl.pkrysztofiak.gridpanels.controller.sidebar.left.LeftSidebarController;
import pl.pkrysztofiak.gridpanels.model.Model;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.Orientation;
import pl.pkrysztofiak.gridpanels.view.View;

public class Controller {

    private final Model model = new Model();
    private final View view = new View();
    
    public Controller() {
        model.gridPanelObservable.subscribe(this::onGridPanelChanged);
        
    }
    
    private void onGridPanelChanged(GridPaneModel gridPanel) {
        PanelsController panelsController = new PanelsController();
        GridPaneController gridPanelController = new GridPaneController(gridPanel, panelsController);
        
        LeftSidebarController leftSidebarController = new LeftSidebarController();
        view.setLeft(leftSidebarController.leftSideBarView);
        view.root.getChildren().addAll(panelsController.panelsView, gridPanelController.gridPanelView);
    }
    
    public void loadLayout() {
        GridPaneModel gridPanelModel = new GridPaneModel(Orientation.HORIZONTAL);
//        gridPanelModel.setOrientation(Orientation.HORIZONTAL);
        
        ImagePanelModel imagePanel = new ImagePanelModel();
        GridPaneModel gridPanel2 = new GridPaneModel(Orientation.VERTICAL);
//        gridPanel2.setOrientation(Orientation.VERTICAL);
        
        gridPanelModel.panels.addAll(imagePanel, gridPanel2);
        
        ImagePanelModel imagePanel2 = new ImagePanelModel();
        ImagePanelModel imagePanel3 = new ImagePanelModel();
        
        gridPanel2.panels.addAll(imagePanel2, imagePanel3);
        
//        GridPanelModel gridPanelModel = new GridPanelModel();
//        gridPanelModel.setOrientation(Orientation.HORIZONTAL);
//        ImagePanelModel imagePanelModel1 = new ImagePanelModel();
//        ImagePanelModel imagePanelModel2 = new ImagePanelModel();
//        gridPanelModel.panels.setAll(imagePanelModel1, imagePanelModel2);
        
        model.setGridPanel(gridPanelModel);
    }
    
    public void show() {
        view.show();
    }
}
