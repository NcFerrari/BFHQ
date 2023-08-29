package lp.fe.javafx.bf2components.stats;

import javafx.stage.Stage;
import lp.be.business.dto.Player;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

public class StatsPane extends BF2Component {

    private final StatsOneThird statsOneThird;
    private final StatsTwoThird statsTwoThird;

    public StatsPane() {
        super(NodeTextEnum.TAB_MENU_STATS);
        statsOneThird = new StatsOneThird(getLeftSidePart());
        statsTwoThird = new StatsTwoThird(getRightSidePart());
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
        rewriteData();
    }

    @Override
    public void rewriteData() {
        Player player = manager.getSelectedPlayer();
        if (player == null) {
            return;
        }
        statsOneThird.rewriteData(player);
        statsTwoThird.rewriteData(player);
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        statsOneThird.resize(stage);
        statsTwoThird.resize(stage);
    }
}
