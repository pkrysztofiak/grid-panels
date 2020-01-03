package pl.pkrysztofiak.gridpanels.view.sidebar.left;

import java.util.Optional;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import pl.pkrysztofiak.gridpanels.view.transfer.ImageTransfer;

public class LeftSidebarView extends VBox {

    private final Image image = new Image(getClass().getResourceAsStream("/images/BrainCT.png"), 100, 100, true, false);
    private final ImageView imageView = new ImageView(image);
    
    public LeftSidebarView() {
        getChildren().add(imageView);
        
        imageView.setOnDragDetected(event -> {
            System.out.println("drag detected");
            ImageTransfer.setImage(image);
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("text");
            db.setContent(content);
            event.consume();
        });
        
        imageView.setOnDragDone(event -> {
            ImageTransfer.setImage(null);
            ImageTransfer.finishedPublishable.onNext(Optional.empty());
            System.out.println("drag done");
        });
    }
}