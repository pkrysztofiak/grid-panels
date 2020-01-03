package pl.pkrysztofiak.gridpanels.view.transfer;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class ImageTransfer {

    private static ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    public static Observable<Image> imageObservable = JavaFxObservable.valuesOf(imageProperty);
    public static PublishSubject<Optional<Void>> startedPublishable = PublishSubject.create();
    public static PublishSubject<Optional<Void>> finishedPublishable = PublishSubject.create();
    
    static {
        imageObservable.subscribe(image -> startedPublishable.onNext(Optional.empty()));
        finishedPublishable.subscribe(nothing -> imageProperty.setValue(null));
    }
    
    public static void setImage(Image image) {
        imageProperty.set(image);
    }
}