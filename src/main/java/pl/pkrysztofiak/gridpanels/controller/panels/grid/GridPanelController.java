package pl.pkrysztofiak.gridpanels.controller.panels.grid;

import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.controller.panels.ImagePanelController;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanel;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanel;
import pl.pkrysztofiak.gridpanels.model.panels.Panel;
import pl.pkrysztofiak.gridpanels.view.panels.GridPanelView;

public class GridPanelController {

    public final GridPanel gridPanel;
    public final GridPanelView gridPanelView = new GridPanelView();
    
//    private static final Map<GridPanel, GridPanelController> gridPanelToController = new HashMap<>();
//    private static final Map<ImagePanel, ImagePanelController> imagePanelToController = new HashMap<>();
    
    private LayoutBehaviour layoutBehaviour;
    
    public GridPanelController(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        
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
                layoutBehaviour = new HorizontalLayoutBehaviour();
                break;
            case VERTICAL:
                layoutBehaviour = new VerticalLayoutBehaviour();
                break;
            default:
                break;
            }
        });
        
        gridPanel.panelAddedObservable.subscribe(this::onPanelAdded);
        gridPanel.panelRemovedObservable.subscribe(this::onPanelRemoved);
        
        initPanels(gridPanel.panels);
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
    
    //WNIOSKI Panel powinien odrysowywać się od początku. To jest cecha widoku, że się przerysowuje
    // gridpane powinien być obserwatorem czy ktoś przypadkiem nie ma jednego dziecka i jeżeli tak to samo powinien reagować
    
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
//        if (!gridPanelToController.containsKey(gridPanel)) {
//            gridPanelToController.put(gridPanel, new GridPanelController(gridPanel));
//        }
//        GridPanelController gridPanelController = gridPanelToController.get(gridPanel);
        GridPanelController gridPanelController = new GridPanelController(gridPanel);
        layoutBehaviour.add(gridPanelView, gridPanelController.gridPanelView, this.gridPanel.panels.indexOf(gridPanel));
    }
    
    private void onImagePanelAdded(ImagePanel imagePanel) {
//        if (!imagePanelToController.containsKey(imagePanel)) {
//            imagePanelToController.put(imagePanel, new ImagePanelController(imagePanel));
//        }
//        ImagePanelController imagePanelController = imagePanelToController.get(imagePanel);
        ImagePanelController imagePanelController = new ImagePanelController(imagePanel);
        imagePanelController.removeObservable.subscribe(gridPanel.panels::remove);
        layoutBehaviour.add(gridPanelView, imagePanelController.imagePanelView, this.gridPanel.panels.indexOf(imagePanelController.imagePanel));
    }
    
    private void onGridPanelRemoved(GridPanel gridPanel) {
//        GridPanelController gridPanelController = gridPanelToController.remove(gridPanel);
//        layoutBehaviour.remove(gridPanelView, gridPanelController.gridPanelView);
    }
    
    private void onImagePanelRemoved(ImagePanel imagePanel) {
//        ImagePanelController imagePanelController = imagePanelToController.remove(imagePanel);
//        layoutBehaviour.remove(gridPanelView, imagePanelController.imagePanelView);
    }
}