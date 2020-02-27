package pl.pkrysztofiak.gridpanels.demo2;

import java.util.stream.IntStream;

import javafx.scene.layout.HBox;
import pl.pkrysztofiak.gridpanels.demo2.panel.PanelView;
import pl.pkrysztofiak.gridpanels.demo2.panel.PanelViewModel;

public class View extends HBox {

    public View(ViewModel viewModel) {
        IntStream.range(0, 2).forEach(i -> {
            getChildren().add(new PanelView(new PanelViewModel(viewModel.getModel())));
        });
    }
}
