package pl.pkrysztofiak.gridpanels.view.panels.grid;

import java.net.URL;
import java.util.ResourceBundle;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public abstract class GridPanelViewFxml implements Initializable {

    protected StackPane root;
    
    @FXML
    protected Button removeButton;

    protected Observable<ActionEvent> removeRequestObservable;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removeRequestObservable = JavaFxObservable.actionEventsOf(removeButton);
    }
}