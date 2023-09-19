package lp.fe.javafx.bf2components.leaderboard;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.Data;

@Data
public class PlayerForSorting {

    private static int totalOrder = 1;

    private int order;
    private Text name;
    private ImageView rank;
    private int sortingValue;
    private int score;
    private final Text value;

    private static void increaseTotalOrder() {
        totalOrder++;
    }

    public static void restartCounter() {
        totalOrder = 1;
    }

    public PlayerForSorting() {
        name = new Text();
        name.setFill(Color.WHITE);
        name.setFont(Font.font(20));
        value = new Text();
        value.setTextAlignment(TextAlignment.CENTER);
        value.setFill(Color.WHITE);
    }

    public void setOrder() {
        order = totalOrder;
        increaseTotalOrder();
    }

    public void setValueText(String text) {
        value.setText(text);
    }

    public void setName(String name) {
        this.name.setText(name);
    }
}
