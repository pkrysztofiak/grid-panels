package pl.pkrysztofiak.gridpanels.view.panels.image;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.gridpanels.model.panels.ImagePanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.PanelsView;
import pl.pkrysztofiak.gridpanels.view.panels.behaviour.add.AddBehaviour;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public class ImagePanelView extends StackPane {

    private final AddBehaviour addBehaviour;
    private final GridPanelView gridPanelView;
    
    {
        setStyle("-fx-background-color: orange;");
    }
    
    public ImagePanelView(ImagePanelModel imagePanelModel, PanelsView panelsView, GridPanelView gridPanelView) {
        this.gridPanelView = gridPanelView;
        addBehaviour = new AddBehaviour(panelsView);
        addBehaviour.add(this);
        
        setOnMouseClicked(event -> {
            System.out.println("ImagePanelView mouseClicked");
        });
        
        setOnMouseMoved(event -> {
            System.out.println("mouse move");
        });
        
        JavaFxObservable.valuesOf(gridPanelView.getRoot().localToSceneTransformProperty()).subscribe(transform -> {
            double sceneX = transform.getTx();
            double sceneY = transform.getTy();
            System.out.println("sceneX=" + sceneX + ", sceneY=" + sceneY);
            
            Point2D sceneToLocal = panelsView.sceneToLocal(sceneX, sceneY);
            
            double localX = sceneToLocal.getX();
            double localY = sceneToLocal.getY();
            setLayoutX(localX);
            setLayoutY(localY);

            //TODO to jest hack
            panelsView.layout();
        });
        
        JavaFxObservable.valuesOf(gridPanelView.getRoot().widthProperty()).map(Number::doubleValue).subscribe(width -> {
            System.out.println("width=" + width);
            setPrefWidth(width);
        });
        
        JavaFxObservable.valuesOf(gridPanelView.getRoot().heightProperty()).map(Number::doubleValue).subscribe(height -> {
            System.out.println("height=" + height);
            setPrefHeight(height);
        });
    }
}