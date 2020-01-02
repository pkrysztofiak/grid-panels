package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.add;

import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public abstract class AddBehaviourAbstract implements AddBehaviour {

    protected final GridPanelView gridPanelView;
    
    public AddBehaviourAbstract(GridPanelView gridPanelView) {
        this.gridPanelView = gridPanelView;
    }
}