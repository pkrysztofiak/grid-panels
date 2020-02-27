package pl.pkrysztofiak.gridpanels.demo2.panel;

import io.reactivex.Observable;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.gridpanels.demo2.Model;
import pl.pkrysztofiak.gridpanels.demo2.point.Point;

public class PanelViewModel {

    private final Model model;
    
    public PanelViewModel(Model model) {
        this.model = model;
    }
    
    public void addPoint(Point2D point) {
        model.addPointRequest.onNext(point);
    }

    public Observable<Point> pointAddedObservable() {
        return model.pointAddedObservable();
    }
}