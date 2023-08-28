package lp.fe.javafx.bf2components.stats;

import javafx.stage.Stage;
import lp.be.business.dto.Player;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

public class StatsPane extends BF2Component {

    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final StatsOneThird statsOneThird;

    public StatsPane() {
        super(NodeTextEnum.TAB_MENU_STATS);
        statsOneThird = new StatsOneThird(getLeftSidePart());
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

        int rank = player.getRank();
        NodeTextEnum.getComponentsForTranslate().replace(statsOneThird.getCurrentRank().textProperty(),
                NodeTextEnum.valueOf(NamespaceEnum.RANK_PREFIX.getText() + rank));
        if (rank < 21) {
            NodeTextEnum.getComponentsForTranslate().replace(statsOneThird.getNextRank().textProperty(),
                    NodeTextEnum.valueOf(NamespaceEnum.RANK_PREFIX.getText() + (rank + 1)));
            statsOneThird.getProgressBar().setProgress(
                    (player.getScore() - statsOneThird.getRankLimits()[rank])
                            / (statsOneThird.getRankLimits()[rank + 1] - statsOneThird.getRankLimits()[rank]));
        } else {
            NodeTextEnum.getComponentsForTranslate().replace(
                    statsOneThird.getNextRank().textProperty(), NodeTextEnum.EMPTY_STRING);
            statsOneThird.getProgressBar().setProgress(1);
        }

        statsOneThird.getRankImage().updateData(pictureService.getRankBF2Image(rank), false);

        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.GLOBAL_SCORE).setText(String.valueOf(player.getScore()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.TIME).setText(manager.longToTime(player.getTime()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.KILLS).setText(String.valueOf(player.getKills()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.DEATHS).setText(String.valueOf(player.getDeaths()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.TEAM_KILLS).setText(
                String.valueOf(player.getTeamkills()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.WINS).setText(String.valueOf(player.getWins()));
        statsOneThird.getPersonalInfoLabels().get(NodeTextEnum.LOSSES).setText(String.valueOf(player.getLosses()));

        statsOneThird.resetAwardsImages();
        int i = 0;
        for (BF2Image bf2Image : manager.getAwardsForSelectedPlayer(3)) {
            statsOneThird.getLastThreeAwards().get(i++).updateData(bf2Image, manager.isShowToolkit());
        }
        NodeTextEnum.getComponentsForTranslate().replace(statsOneThird.getLastAwardLabel().textProperty(),
                NodeTextEnum.EMPTY_STRING);
        for (BF2Image bf2Image : manager.getAwardsForSelectedPlayer(1)) {
            NodeTextEnum.getComponentsForTranslate().replace(statsOneThird.getLastAwardLabel().textProperty(),
                    bf2Image.getNodeTextEnum());
        }
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        double oneSixth = stage.getWidth() / 6;
        double oneNinth = stage.getWidth() / 9;
        statsOneThird.getRankDataPane().setPrefWidth(oneSixth);
        statsOneThird.getProgressBar().setPrefWidth(oneSixth);
        statsOneThird.getImagePane().setPrefWidth(oneSixth);
        statsOneThird.getRankImage().setImageViewSize(oneNinth, oneNinth);
        statsOneThird.getLastThreeAwards().forEach(bf2Image -> bf2Image.setImageViewSize(oneNinth - 20, oneNinth));
    }
}
