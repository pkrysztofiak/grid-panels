package pl.pkrysztofiak.gridpanels.demo2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.gridpanels.demo2.point.Point;

public class Model {

    private final ObservableList<Point> points = FXCollections.observableArrayList();
    private final Observable<Point> pointAddedObservable = JavaFxObservable.additionsOf(points);
    
    public final PublishSubject<Point2D> addPointRequest = PublishSubject.create();
    
    {
        addPointRequest.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onAddPointRequest);
    }
    
    public Observable<Point> pointAddedObservable() {
        return pointAddedObservable;
    }
    
    private void onAddPointRequest(Point2D point) {
        points.add(new Point(point));
    }
}