package lp.fe.javafx.bf2components.leaderboard;

import javafx.scene.image.ImageView;
import lombok.Data;

@Data
public class PlayerForSorting {

    private static int totalOrder = 1;

    private int order;
    private String name;
    private ImageView rank;
    private int value;

    public void setOrder() {
        order++;
    }

    public static void restartCounter() {
        totalOrder = 1;
    }
}
