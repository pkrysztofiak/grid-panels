package pl.pkrysztofiak.gridpanels.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DemoApp extends Application {

    private final Model model = new Model();
    private final ViewModel viewModel = new ViewModel(model);
    private final View view = new View(viewModel);
    private final Scene scene = new Scene(view);
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.show();
    }
}