package pl.pkrysztofiak.gridpanels.model;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.gridpanels.model.panels.GridPanel;

public class Model {

    private final ObjectProperty<GridPanel> gridPanelProperty = new SimpleObjectProperty<>();
    public final Observable<GridPanel> gridPanelObservable = JavaFxObservable.valuesOf(gridPanelProperty);
    
    public void setGridPanel(GridPanel gridPanel) {
        gridPanelProperty.set(gridPanel);
    }
}
