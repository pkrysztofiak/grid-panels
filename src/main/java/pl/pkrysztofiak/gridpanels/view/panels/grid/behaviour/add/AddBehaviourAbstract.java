package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import pl.pkrysztofiak.gridpanels.model.panels.PanelModel;
import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public abstract class AddBehaviourAbstract implements AddBehaviour {

    protected final GridPanelView gridPanelView;
    protected final PanelModel panelModel;
    
    public AddBehaviourAbstract(PanelModel panelModel, GridPanelView prarentPanelView) {
        this.panelModel = panelModel;
        this.gridPanelView = prarentPanelView;
    }
}