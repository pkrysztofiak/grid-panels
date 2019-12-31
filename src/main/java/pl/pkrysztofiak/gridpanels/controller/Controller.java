package pl.pkrysztofiak.gridpanels.controller;

import pl.pkrysztofiak.gridpanels.controller.panels.grid.GridPanelController;
import pl.pkrysztofiak.gridpanels.model.Model;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.model.panels.Orientation;
import pl.pkrysztofiak.gridpanels.view.View;

public class Controller {

    private final Model model = new Model();
    private final View view = new View();
    
    public Controller() {
        model.gridPanelObservable.subscribe(this::onGridPanelChanged);
    }
    
    private void onGridPanelChanged(GridPanel gridPanel) {
        System.out.println("gridPanel changed");
        GridPanelController gridPanelController = new GridPanelController(gridPanel);
        view.setMainPanel(gridPanelController.gridPanelView);
    }
    
    public void loadLayout() {
        GridPanel gridPanel = new GridPanel();
        gridPanel.setOrientation(Orientation.HORIZONTAL);
        
        ImagePanel imagePanel = new ImagePanel();
        GridPanel gridPanel2 = new GridPanel();
        gridPanel2.setOrientation(Orientation.VERTICAL);
        
        gridPanel.panels.addAll(imagePanel, gridPanel2);
        
        ImagePanel imagePanel2 = new ImagePanel();
        ImagePanel imagePanel3 = new ImagePanel();
        
        gridPanel2.panels.addAll(imagePanel2, imagePanel3);
        
        model.setGridPanel(gridPanel);
    }
    
    public void show() {
        view.show();
    }
}
