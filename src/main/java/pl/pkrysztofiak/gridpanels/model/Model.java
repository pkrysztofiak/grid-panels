package pl.pkrysztofiak.gridpanels.model;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.gridpanels.model.panels.GridPaneModel;

public class Model {

    private final ObjectProperty<GridPaneModel> gridPanelProperty = new SimpleObjectProperty<>();
    public final Observable<GridPaneModel> gridPanelObservable = JavaFxObservable.valuesOf(gridPanelProperty);
    
    public void setGridPanel(GridPaneModel gridPanel) {
        gridPanelProperty.set(gridPanel);
    }
}
