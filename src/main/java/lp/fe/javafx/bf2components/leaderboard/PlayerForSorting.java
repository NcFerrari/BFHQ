package lp.fe.javafx.bf2components.leaderboard;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.Data;
import lp.Manager;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;

@Data
public class PlayerForSorting {

    private static int totalOrder = 1;

    private int order;
    private Text name;
    private ImageView rankImage;
    private int rank;
    private int kills;
    private int deaths;
    private int countOfAwards;
    private int time;
    private int score;
    private int wins;
    private int losses;
    private int teamKilling;
    private int teamPlayerScore;
    private int killStreak;

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

    public void setName(String name) {
        this.name.setText(name);
    }

    public void prepareValue(String methodName) {
        if (NamespaceEnum.GET_RANK.getText().equals(methodName)) {
            getValue().setText(NodeTextEnum.getRankTitleFromInt(getRank()).getSelectedText());
        } else if (NamespaceEnum.GET_TIME.getText().equals(methodName)) {
            getValue().setText(Manager.getInstance().longToTime((long) getTime()));
        } else if (methodName != null) {
            try {
                getValue().setText(String.valueOf(getClass().getMethod(methodName).invoke(this)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
