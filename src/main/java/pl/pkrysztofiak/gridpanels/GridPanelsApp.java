package pl.pkrysztofiak.gridpanels;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.pkrysztofiak.gridpanels.controller.Controller;

public class GridPanelsApp extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();
        controller.loadLayout();
        controller.show();
    }
}
