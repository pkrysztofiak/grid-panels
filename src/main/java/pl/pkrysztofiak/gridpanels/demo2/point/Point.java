package pl.pkrysztofiak.gridpanels.demo2.point;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class Point {

    private final DoubleProperty xProperty = new SimpleDoubleProperty();
    private final Observable<Double> xObservable = JavaFxObservable.valuesOf(xProperty).map(Number::doubleValue);
    private final DoubleProperty yProperty = new SimpleDoubleProperty(); 
    private final Observable<Double> yObservable = JavaFxObservable.valuesOf(yProperty).map(Number::doubleValue);
    
    public final PublishSubject<Point2D> setPointRequest = PublishSubject.create();
    
    {
        setPointRequest.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onSetPonitRequest);
    }
    
    
    public Point(double x, double y) {
        xProperty.set(x);
        yProperty.set(y);
    }
    
    public Point(Point2D point) {
        xProperty.set(point.getX());
        yProperty.set(point.getY());
    }
    
    public Observable<Double> xObservable() {
        return xObservable;
    }
    
    public Observable<Double> yObservable() {
        return yObservable;
    }
    
    private void onSetPonitRequest(Point2D point) {
        System.out.println("Point.onSetPonitRequest");
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        xProperty.set(point.getX());
        yProperty.set(point.getY());
    }
}