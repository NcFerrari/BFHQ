package lp.fe.javafx.bf2components.awards;

import javafx.stage.Stage;
import lombok.Getter;
import lp.be.business.dto.Player;
import lp.be.service.BF2Image;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

@Getter
public class AwardsPane extends BF2Component {

    private final AwardOneThird awardOneThird;
    private final AwardTwoThird awardTwoThird;

    public AwardsPane() {
        super(NodeTextEnum.TAB_MENU_AWARDS);
        awardOneThird = new AwardOneThird(getLeftSidePart());
        awardTwoThird = new AwardTwoThird(getRightSidePart(), this);
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }

    @Override
    public void rewriteData() {
        Player player = manager.getSelectedPlayer();
        if (player != null) {
            awardOneThird.rewriteData();
            awardTwoThird.rewriteData();
        }
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        awardOneThird.resize(stage);
        awardTwoThird.resize(stage);
    }

    public void showBigImage(String key, BF2Image bf2Image) {
        getAwardOneThird().showBigImage(key, bf2Image);
    }

    public void clearBigImage() {
        awardOneThird.clearBigImage();
    }
}
