package lp.fe.javafx.bf2components;

/**
 * Needed to get actual data from database (refreshing data)
 */
public interface Valuable {

    /**
     * Each class with this method is responsible for refreshing data from database. By this method all classes
     * get signal to refreshing data from database.
     */
    void reloadData();
}

