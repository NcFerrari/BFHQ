package lp.fe.javafx.bf2components.leaderboard;

import javafx.stage.Stage;
import lombok.Getter;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

@Getter
public class LeaderBoardPane extends BF2Component {

    private final LeaderBoardOneThird leaderBoardOneThird;

    public LeaderBoardPane() {
        super(NodeTextEnum.TAB_MENU_LEADERBOARDS);
        leaderBoardOneThird = new LeaderBoardOneThird(getLeftSidePart());
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        leaderBoardOneThird.resize(stage);
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
        leaderBoardOneThird.reloadData();
    }

    @Override
    public void rewriteData() {
        leaderBoardOneThird.rewriteData();
    }

    public void refreshComboBox() {
        leaderBoardOneThird.refreshComboBox();
    }

    public void resetKillsTab() {
        leaderBoardOneThird.resetKillsTab();
    }
}
