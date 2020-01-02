package pl.pkrysztofiak.gridpanels.view.panels;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class GridPanelViewFxml implements Initializable {

    protected Node root;
    
    @FXML
    protected Button removeButton;

    protected Observable<ActionEvent> removeRequestObservable;
    
    public GridPanelViewFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/panels/ContentPanelView.fxml"));
        try {
            fxmlLoader.setController(this);
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("removeButton=" + removeButton);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removeRequestObservable = JavaFxObservable.actionEventsOf(removeButton);
        System.out.println("initialize()");
    }
}