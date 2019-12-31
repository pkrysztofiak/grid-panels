package pl.pkrysztofiak.gridpanels.model;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanelModel;

public class Model {

    private final ObjectProperty<GridPanelModel> gridPanelProperty = new SimpleObjectProperty<>();
    public final Observable<GridPanelModel> gridPanelObservable = JavaFxObservable.valuesOf(gridPanelProperty);
    
    public void setGridPanel(GridPanelModel gridPanel) {
        gridPanelProperty.set(gridPanel);
    }
}
