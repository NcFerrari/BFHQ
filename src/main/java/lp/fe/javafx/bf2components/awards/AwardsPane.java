package lp.fe.javafx.bf2components.awards;

import javafx.stage.Stage;
import lp.be.business.dto.Player;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

public class AwardsPane extends BF2Component {

    private final AwardTwoThird awardTwoThird;

    public AwardsPane() {
        super(NodeTextEnum.TAB_MENU_AWARDS);
        awardTwoThird = new AwardTwoThird(getRightSidePart());
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }

    @Override
    public void rewriteData() {
        Player player = manager.getSelectedPlayer();
        if (player == null) {
            return;
        }
        awardTwoThird.rewriteData();
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        awardTwoThird.resize(stage);
    }
}
