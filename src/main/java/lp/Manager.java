package lp;

public class Manager {

    private static Manager manager;

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    private Manager() {

    }

    public static void main(String[] args) {
        Manager.getInstance();
    }
}
