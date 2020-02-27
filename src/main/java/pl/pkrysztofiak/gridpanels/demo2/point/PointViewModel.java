package pl.pkrysztofiak.gridpanels.demo2.point;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class PointViewModel {

    private final BooleanProperty activeProperty = new SimpleBooleanProperty();
    private final Observable<Boolean> activeObservable = JavaFxObservable.valuesOf(activeProperty);
    
    private final BooleanProperty focusedProperty = new SimpleBooleanProperty();
    private final Observable<Boolean> focusedObservable = JavaFxObservable.valuesOf(focusedProperty);
    
//    public final PublishSubject<Point2D> mousePressed = PublishSubject.create();
    public final PublishSubject<Point2D> mouseDragged = PublishSubject.create();
    public final PublishSubject<Point2D> mouseReleased = PublishSubject.create();
    
    private final DoubleProperty xProperty = new SimpleDoubleProperty(); 
    private final DoubleProperty yProperty = new SimpleDoubleProperty(); 
    
    public PointViewModel(Point point) {
    
        
        focusedObservable.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(activeProperty::set);
        
        activeObservable
        .filter(Boolean.FALSE::equals)
        .switchMap(inactive -> point.xObservable().takeUntil(focusedObservable.filter(Boolean.TRUE::equals)))
        .observeOn(JavaFxScheduler.platform())
        .subscribe(this::onXChanged);
        
        activeObservable
        .filter(Boolean.FALSE::equals)
        .switchMap(inactive -> point.yObservable().takeUntil(focusedObservable.filter(Boolean.TRUE::equals)))
        .observeOn(JavaFxScheduler.platform())
        .subscribe(this::onYChanged);

        focusedObservable
        .filter(Boolean.TRUE::equals)
        .switchMap(focused -> mouseDragged.takeUntil(focusedObservable.filter(Boolean.FALSE::equals)))
        .subscribe(dragged -> {
            xProperty.set(dragged.getX());
            yProperty.set(dragged.getY());
            point.setPointRequest.onNext(dragged);
        });
    }
    
    private final void onXChanged(double x) {
        xProperty.set(x);
    }
    
    private final void onYChanged(double y) {
        yProperty.set(y);
    }
    
    public DoubleProperty xProperty() {
        return xProperty;
    }
    
    public DoubleProperty yProperty() {
        return yProperty;
    }
    
    public BooleanProperty focusedProperty() {
        return focusedProperty;
    }
}