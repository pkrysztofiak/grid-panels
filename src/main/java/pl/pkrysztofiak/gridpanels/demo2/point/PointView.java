package pl.pkrysztofiak.gridpanels.demo2.point;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointView extends Circle {

    private final Observable<Point2D> mouseDraggedObservable = JavaFxObservable.eventsOf(this, MouseEvent.MOUSE_DRAGGED).map(event -> new Point2D(event.getX(), event.getY()));
    private final Observable<Point2D> mouseReleasedObservable = JavaFxObservable.eventsOf(this, MouseEvent.MOUSE_RELEASED).map(event -> new Point2D(event.getX(), event.getY()));
    
    private final BooleanProperty focusedProperty = new SimpleBooleanProperty();
    
    public PointView(PointViewModel pointViewModel) {
        setFill(Color.PURPLE);
        setRadius(8);
        
        focusedProperty.bindBidirectional(pointViewModel.focusedProperty());
        
        centerXProperty().bindBidirectional(pointViewModel.xProperty());
        centerYProperty().bindBidirectional(pointViewModel.yProperty());
        
        JavaFxObservable.eventsOf(this, MouseEvent.ANY).subscribe(MouseEvent::consume);
        
        mouseDraggedObservable.subscribe(dragged -> focusedProperty.set(true));
        mouseReleasedObservable.subscribe(released -> focusedProperty.set(false));
        
        mouseDraggedObservable.subscribe(pointViewModel.mouseDragged::onNext);
        mouseReleasedObservable.subscribe(pointViewModel.mouseReleased::onNext);
    }
}