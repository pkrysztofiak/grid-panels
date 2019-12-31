package pl.pkrysztofiak.gridpanels.model.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GridPanel extends Panel {

    public final ObservableList<Panel> panels = FXCollections.observableArrayList();
    public final Observable<Panel> panelAddedObservable = JavaFxObservable.additionsOf(panels);
    public final Observable<Panel> panelRemovedObservable = JavaFxObservable.removalsOf(panels);
    
    private final ObjectProperty<Orientation> orientationProperty = new SimpleObjectProperty<>();
    public final Observable<Orientation> orientationObservable = JavaFxObservable.valuesOf(orientationProperty);
    
    public GridPanel() {
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
}