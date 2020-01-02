package pl.pkrysztofiak.gridpanels.view.panels.grid;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.AddPanelBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.HorizontalPanelAdd;
import pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add.VerticalPanelAdd;

public class GridPanelView extends GridPane {
    
    private AddPanelBehaviour addBehaviour;
    
    private final GridPanelModel gridPanelModel;
    private GridPanelModel parentPanelModel;
    private GridPanelView parentPanelView;

    {
        setVgap(16);
        setHgap(16);
    }
    
    public GridPanelView(GridPanelModel gridPanelModel) {
        this.gridPanelModel = gridPanelModel;
        init();
    }
    
    public GridPanelView(GridPanelModel gridPanelModel, GridPanelView parentPanelView, GridPanelModel parentPanelModel) {
        this.gridPanelModel = gridPanelModel;
        this.parentPanelView = parentPanelView;
        this.parentPanelModel = parentPanelModel;
        init();
        
        System.out.println("GridPanelView()");
        
        parentPanelView.addPanelView(this, parentPanelModel.indexOf(gridPanelModel));
        
//        addPanelView(this, parentPanelModel.indexOf(gridPanelModel));
    }
    
    private void init() {
        gridPanelModel.orientationObservable.subscribe(orientation -> {
            switch (orientation) {
            case HORIZONTAL:
                addBehaviour = new HorizontalPanelAdd();
                getRowConstraints().setAll(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
                break;
            case VERTICAL:
                addBehaviour = new VerticalPanelAdd();
                getColumnConstraints().setAll(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));
                break;
            default:
                break;
            }
        });
    }
    
    public void removePanel(Node node) {
        System.out.println("GridPanelView.removePanel()");
    }
    
    public void addPanelView(Node node, int index) {
        addBehaviour.addPanelView(this, node, index);
    }

    public AddPanelBehaviour getAddBehaviour() {
        return addBehaviour;
    }

    public void setAddBehaviour(AddPanelBehaviour addBehaviour) {
        this.addBehaviour = addBehaviour;
    }
}