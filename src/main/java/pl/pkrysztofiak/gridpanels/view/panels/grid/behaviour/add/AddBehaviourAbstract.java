package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public abstract class AddBehaviourAbstract implements AddBehaviour {

    protected final GridPaneView gridPanelView;
    protected final PanelModel panelModel;
    
    public AddBehaviourAbstract(PanelModel panelModel, GridPaneView prarentPanelView) {
        this.panelModel = panelModel;
        this.gridPanelView = prarentPanelView;
    }
}