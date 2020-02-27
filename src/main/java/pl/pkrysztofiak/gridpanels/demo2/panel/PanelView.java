package pl.pkrysztofiak.gridpanels.demo2.panel;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pl.pkrysztofiak.gridpanels.demo2.point.Point;
import pl.pkrysztofiak.gridpanels.demo2.point.PointView;
import pl.pkrysztofiak.gridpanels.demo2.point.PointViewModel;

public class PanelView extends Pane {

    private final PanelViewModel panelViewModel;
    private final ObservableList<Node> children = FXCollections.observableArrayList();
    
    private final Observable<MouseEvent> mouseClickedObservable = JavaFxObservable.eventsOf(this, MouseEvent.MOUSE_CLICKED);
    
    {
        setPrefSize(400, 400);
        setStyle("-fx-background-color: purple, white; -fx-background-insets: 0, 1;");
        
        mouseClickedObservable.subscribe(this::onMouseClicked);
    }
    
    public PanelView(PanelViewModel panelViewModel) {
        this.panelViewModel = panelViewModel;
        Bindings.bindContent(getChildren(), children);
        
        panelViewModel.pointAddedObservable().observeOn(JavaFxScheduler.platform()).subscribe(this::onPointAdded);
    }
    
    private void onMouseClicked(MouseEvent event) {
        panelViewModel.addPoint(new Point2D(event.getX(), event.getY()));
    }
    
    private void onPointAdded(Point point) {
        children.add(new PointView(new PointViewModel(point)));
    }
}