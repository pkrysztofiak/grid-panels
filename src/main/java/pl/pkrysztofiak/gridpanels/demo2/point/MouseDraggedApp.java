package pl.pkrysztofiak.gridpanels.demo2.point;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MouseDraggedApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Circle circle = new Circle(8, Color.PURPLE);
        circle.setCenterX(200);
        circle.setCenterY(200);
        Pane pane = new Pane(circle);
        pane.setPrefSize(400, 400);
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        
        Observable<Point2D> mousePressed = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_PRESSED).map(event -> new Point2D(event.getX(), event.getY()));
        Observable<Point2D> mouseDragged = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_DRAGGED).map(event -> new Point2D(event.getX(), event.getY()));
        Observable<Point2D> mouseReleased = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_RELEASED).map(event -> new Point2D(event.getX(), event.getY()));

        mouseDragged.subscribe(dragged -> {
            circle.setCenterX(dragged.getX());
            circle.setCenterY(dragged.getY());
        });
        
    }
}