package pl.pkrysztofiak.gridpanels.view.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;

public class ImagePanelView extends StackPane {

    private final Button button = new Button("Remove");
    public final Observable<ActionEvent> removeObservable = JavaFxObservable.actionEventsOf(button); 
    
    public ImagePanelView(ImagePanelModel imagePanel) {
        setStyle("-fx-background-color: yellow;");
        getChildren().add(button);
    }
}
