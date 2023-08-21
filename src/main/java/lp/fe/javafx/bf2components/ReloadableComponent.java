package lp.fe.javafx.bf2components;

import lp.Manager;

public abstract class ReloadableComponent {

    protected final Manager manager = Manager.getInstance();

    protected ReloadableComponent() {
        manager.registerReloadableComponent(this);
    }

    public abstract void reloadData();
}
