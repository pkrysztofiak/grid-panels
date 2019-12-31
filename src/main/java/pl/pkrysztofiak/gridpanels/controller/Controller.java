package pl.pkrysztofiak.gridpanels.controller;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.Model;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
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
        GridPanelModel gridPanel = new GridPanelModel();
        gridPanel.setOrientation(Orientation.HORIZONTAL);
        
        ImagePanelModel imagePanel = new ImagePanelModel();
        GridPanelModel gridPanel2 = new GridPanelModel();
        gridPanel2.setOrientation(Orientation.VERTICAL);
        
        gridPanel.panels.addAll(imagePanel, gridPanel2);
        
        ImagePanelModel imagePanel2 = new ImagePanelModel();
        ImagePanelModel imagePanel3 = new ImagePanelModel();
        
        gridPanel2.panels.addAll(imagePanel2, imagePanel3);
        
        model.setGridPanel(gridPanel);
    }
    
    public void show() {
        view.show();
    }
}
