package pl.pkrysztofiak.gridpanels.view.panels.grid.behaviour.remove;

import pl.pkrysztofiak.gridpanels.view.panels.grid.GridPaneView;

public abstract class RemoveBehaviourAbstract implements RemoveBehaviour {

    protected final GridPaneView gridPanelView;
    
    public RemoveBehaviourAbstract(GridPaneView gridPanelView) {
        this.gridPanelView = gridPanelView;
    }
}