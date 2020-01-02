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

public abstract class ImagePanelViewFxml implements Initializable {

    protected Node root;
    
    @FXML
    protected Button removeButton;

    protected Observable<ActionEvent> removeRequestObservable;
    
    public ImagePanelViewFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/panels/ImagePanelView.fxml"));
        try {
            root = fxmlLoader.load();
            fxmlLoader.setController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removeRequestObservable = JavaFxObservable.actionEventsOf(removeButton);
    }
}