package pl.pkrysztofiak.gridpanels.model.panels;

public abstract class Panel {

    private GridPanel parent;
    
    public GridPanel getParent() {
        return parent;
    }

    public void setParent(GridPanel parent) {
        this.parent = parent;
    }

    public abstract PanelType getType();
}
