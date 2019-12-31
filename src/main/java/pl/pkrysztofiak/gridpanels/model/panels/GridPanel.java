package pl.pkrysztofiak.gridpanels.model.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GridPanel extends Panel {

    private GridPanel parentGridPanel;
    
    public final ObservableList<Panel> panels = FXCollections.observableArrayList();
    public final Observable<Panel> panelAddedObservable = JavaFxObservable.additionsOf(panels);
    public final Observable<Panel> panelRemovedObservable = JavaFxObservable.removalsOf(panels);
    
    private final ObjectProperty<Orientation> orientationProperty = new SimpleObjectProperty<>();
    public final Observable<Orientation> orientationObservable = JavaFxObservable.valuesOf(orientationProperty);
    
    public GridPanel() {
        panels.forEach(this::onPanelAdded);
        panelAddedObservable.subscribe(this::onPanelAdded);
        panelRemovedObservable.subscribe(this::onPanelRemoved);
    }
    
    @Override
    public PanelType getType() {
        return PanelType.GRID;
    }
    
    public void setOrientation(Orientation orientation) {
        orientationProperty.set(orientation);
    }
    
    public Orientation getOrientation() {
        return orientationProperty.get();
    }
    
    public void setParent(GridPanel parentGridPanel) {
        this.parentGridPanel = parentGridPanel;
    }
    
    private void onPanelAdded(Panel panel) {
        switch (panel.getType()) {
        case GRID:
            GridPanel gridPanel = (GridPanel) panel;
            gridPanel.setParent(this);
            break;
        default:
            break;
        }
    }
    
    private void onPanelRemoved(Panel panel) {
        System.out.println("size=" + panels.size());
        switch (panels.size()) {
        case 1:
            panels.forEach(imagePanel -> {
                ObservableList<Panel> parentPanels = parentGridPanel.panels;
                int index = parentPanels.indexOf(this);
                System.out.println("index=" + index);
                parentPanels.set(index, imagePanel);
            });
            break;
        default:
            break;
        }
    }
}