package pl.pkrysztofiak.gridpanels.model.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GridPaneModel extends PanelModel {

    private GridPaneModel parentGridPanelModel;

    public final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    public final Observable<PanelModel> panelAddedObservable = JavaFxObservable.additionsOf(panels);
    public final Observable<PanelModel> panelRemovedObservable = JavaFxObservable.removalsOf(panels);
    
    private final ObjectProperty<Orientation> orientationProperty = new SimpleObjectProperty<>();
    public final Observable<Orientation> orientationObservable = JavaFxObservable.valuesOf(orientationProperty);
    
    public GridPaneModel(Orientation orientation) {
        orientationProperty.set(orientation);
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
    
    public void setParent(GridPaneModel parentGridPanel) {
        this.parentGridPanelModel = parentGridPanel;
    }
    
    public int indexOf(PanelModel panelModel) {
        return panels.indexOf(panelModel);
    }
    
    public void remove(PanelModel panelModel) {
        panels.remove(panelModel);
    }
    
    private void onPanelAdded(PanelModel panel) {
        switch (panel.getType()) {
        case GRID:
            GridPaneModel gridPanel = (GridPaneModel) panel;
            gridPanel.setParent(this);
            break;
        default:
            break;
        }
    }
    
    private void onPanelRemoved(PanelModel panel) {
        switch (panels.size()) {
        case 1:
            panels.forEach(imagePanel -> {
                ObservableList<PanelModel> parentPanels = parentGridPanelModel.panels;
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