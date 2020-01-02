package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPanelView;

public abstract class RemoveBehaviourAbstract implements RemoveBehaviour {

    protected final GridPanelView gridPanelView;
    
    public RemoveBehaviourAbstract(GridPanelView gridPanelView) {
        this.gridPanelView = gridPanelView;
    }
}