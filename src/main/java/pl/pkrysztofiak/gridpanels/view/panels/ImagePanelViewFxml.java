package pl.pkrysztofiak.gridpanels.view.panels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class ImagePanelViewFxml implements Initializable {

    @FXML
    protected Node root;
    
    @FXML
    protected Button removeButton;

}