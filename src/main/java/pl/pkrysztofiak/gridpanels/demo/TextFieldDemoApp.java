package pl.pkrysztofiak.gridpanels.demo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldDemoApp extends Application {
   
    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage stage) throws Exception {

        Employee kowalski = new Employee("Kowalski");
       
        TextField textField = new TextField();
        Observable<Boolean> focusedObservable = JavaFxObservable.valuesOf(textField.focusedProperty());
        Observable<String> textObservable = JavaFxObservable.valuesOf(textField.textProperty());
       
        TextField textField2 = new TextField();
        Observable<Boolean> focusedObservable2 = JavaFxObservable.valuesOf(textField2.focusedProperty());
        Observable<String> textObservable2 = JavaFxObservable.valuesOf(textField2.textProperty());
       
//        focusedObservable.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(activeProperty::set);
//        focusedObservable2.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(activeProperty2::set);
       
        focusedObservable
        .filter(Boolean.TRUE::equals)
        .switchMap(focused -> textObservable.skip(1).takeUntil(focusedObservable.filter(Boolean.FALSE::equals)))
        .subscribe(kowalski.setNameRequest::onNext);
       
//        activeObservable
        focusedObservable
        .delay(0, TimeUnit.SECONDS, Schedulers.single())
        .filter(Boolean.FALSE::equals)
        .switchMap(inactive -> kowalski.nameObservable.takeUntil(focusedObservable.filter(Boolean.TRUE::equals)))
        .observeOn(JavaFxScheduler.platform())
        .subscribe(textField::setText);
       
        focusedObservable2
        .filter(Boolean.TRUE::equals)
        .switchMap(focused -> textObservable2.skip(1).takeUntil(focusedObservable2.filter(Boolean.FALSE::equals)))
        .subscribe(kowalski.setNameRequest::onNext);
       
//        activeObservable2
        focusedObservable2
        .delay(0, TimeUnit.SECONDS, Schedulers.single())
        .filter(Boolean.FALSE::equals)
        .switchMap(inactive -> kowalski.nameObservable.takeUntil(focusedObservable2.filter(Boolean.TRUE::equals)))
        .observeOn(JavaFxScheduler.platform())
        .subscribe(textField2::setText);
       
        VBox vBox = new VBox(textField, textField2, new TextField());
       
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}

class Employee {
   
    public final StringProperty nameProperty = new SimpleStringProperty();
    public final Observable<String> nameObservable = JavaFxObservable.valuesOf(nameProperty);
   
    public final PublishSubject<String> setNameRequest = PublishSubject.create();
   
    {
        setNameRequest.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onSetName);
    }
   
    private void onSetName(String name) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " setName(" + name + ")");
        nameProperty.set(name);
    }
   
    public Employee(String name) {
        nameProperty.set(name);
    }
   
    public void setName(String name) {
        nameProperty.set(name);
    }
}