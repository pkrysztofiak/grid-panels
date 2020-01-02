package pl.pkrysztofiak.gridpanels.controller;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.Model;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ContentPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.Orientation;
import pl.pkrysztofiak.gridpanels.view.View;

public class Controller {

    private final Model model = new Model();
    private final View view = new View();
    
    public Controller() {
        model.gridPanelObservable.subscribe(this::onGridPanelChanged);
    }
    
    private void onGridPanelChanged(GridPanelModel gridPanel) {
        System.out.println("gridPanel changed");
        GridPanelController gridPanelController = new GridPanelController(gridPanel);
        view.setMainPanel(gridPanelController.gridPanelView);
    }
    
    public void loadLayout() {
        GridPanelModel gridPanelModel = new GridPanelModel(Orientation.HORIZONTAL);
//        gridPanelModel.setOrientation(Orientation.HORIZONTAL);
        
        ContentPanelModel imagePanel = new ContentPanelModel();
        GridPanelModel gridPanel2 = new GridPanelModel(Orientation.VERTICAL);
//        gridPanel2.setOrientation(Orientation.VERTICAL);
        
        gridPanelModel.panels.addAll(imagePanel, gridPanel2);
        
        ContentPanelModel imagePanel2 = new ContentPanelModel();
        ContentPanelModel imagePanel3 = new ContentPanelModel();
        
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
