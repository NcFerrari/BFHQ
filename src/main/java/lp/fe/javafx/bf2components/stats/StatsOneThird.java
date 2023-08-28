package lp.fe.javafx.bf2components.stats;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.LeftSidePart;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class StatsOneThird {

    private final double[] rankLimits = {0, 150, 500, 800, 2_500, 5_000, 8_000, 20_000, 20_000, 50_000, 50_000, 50_000,
            60_000, 75_000, 90_000, 11_500, 12_500, 15_000, 18_000, 18_000, 20_000, 20_000};
    private final Map<NodeTextEnum, Label> personalInfoLabels = new EnumMap<>(NodeTextEnum.class);
    private final ObservableList<BF2Image> lastThreeAwards = FXCollections.observableArrayList();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final LeftSidePart leftSidePart;

    private VBox rankDataPane;
    private Label currentRank;
    private Label nextRank;
    private ProgressBar progressBar;
    private StackPane imagePane;
    private BF2Image rankImage;
    private Label lastAwardLabel;

    public StatsOneThird(LeftSidePart leftSidePart) {
        this.leftSidePart = leftSidePart;

        initRankTitle();
        initRankDataAndImage();
        initRankProgressPane();

        initPersonalInfoPane();
        initLastThreeAwards();
    }

    private void initRankTitle() {
        Label rankTitle = new Label();
        rankTitle.setText(NodeTextEnum.RANK_TITLE.getText(rankTitle.textProperty()));
        rankTitle.setId(NamespaceEnum.SUB_TITLE_STYLE.getText());
        leftSidePart.getLeftPane().getChildren().add(rankTitle);
    }

    private void initRankDataAndImage() {
        rankDataPane = new VBox();
        addLabel(rankDataPane, NodeTextEnum.CURRENT_RANK, false);
        currentRank = addLabel(rankDataPane, NodeTextEnum.EMPTY_STRING, true);
        addLabel(rankDataPane, NodeTextEnum.EMPTY_STRING, false);
        addLabel(rankDataPane, NodeTextEnum.NEXT_RANK, false);
        nextRank = addLabel(rankDataPane, NodeTextEnum.EMPTY_STRING, true);
        addLabel(rankDataPane, NodeTextEnum.EMPTY_STRING, false);
        HBox rankPane = new HBox();
        rankPane.getChildren().add(rankDataPane);
        leftSidePart.getLeftPane().getChildren().add(rankPane);

        imagePane = new StackPane();
        rankImage = new BF2Image();
        imagePane.getChildren().add(rankImage.getImageView());
        rankPane.getChildren().add(imagePane);
    }

    private Label addLabel(Pane rootPane, NodeTextEnum nodeTextEnum, boolean valueFromDB) {
        Label label = new Label();
        if (valueFromDB) {
            label.setId(NamespaceEnum.DB_VALUE_STYLE.getText());
        } else {
            label.setId(NamespaceEnum.VALUE_STYLE.getText());
        }
        label.setText(nodeTextEnum.getText(label.textProperty()));
        rootPane.getChildren().add(label);
        return label;
    }

    private void initRankProgressPane() {
        BorderPane progressPane = new BorderPane();
        Label progressTowLabel = new Label();
        progressTowLabel.setId(NamespaceEnum.VALUE_STYLE.getText());
        progressTowLabel.setText(NodeTextEnum.PROGRESS_TOWARDS_NEXT_RANK.getText(progressTowLabel.textProperty()));
        progressPane.setLeft(progressTowLabel);
        progressBar = new ProgressBar();
        progressBar.setId(NamespaceEnum.PROGRESS_BAR_STYLE.getText());
        progressPane.setRight(progressBar);
        leftSidePart.getLeftPane().getChildren().add(progressPane);
    }

    private void initPersonalInfoPane() {
        Label personalInfoTitle = new Label();
        personalInfoTitle.setText(NodeTextEnum.PERSONAL_INFO.getText(personalInfoTitle.textProperty()));
        personalInfoTitle.setId(NamespaceEnum.SUB_TITLE_STYLE.getText());
        leftSidePart.getLeftPane().getChildren().add(personalInfoTitle);

        addBorderLine(NodeTextEnum.GLOBAL_SCORE);
        addBorderLine(NodeTextEnum.TIME);
        addBorderLine(NodeTextEnum.KILLS);
        addBorderLine(NodeTextEnum.DEATHS);
        addBorderLine(NodeTextEnum.TEAM_KILLS);
        addBorderLine(NodeTextEnum.WINS);
        addBorderLine(NodeTextEnum.LOSSES);
    }

    private void addBorderLine(NodeTextEnum nodeTextEnum) {
        BorderPane borderPane = new BorderPane();
        if (personalInfoLabels.size() % 2 == 0) {
            borderPane.setId(NamespaceEnum.BORDER_LIGHT_STYLE.getText());
        }
        leftSidePart.getLeftPane().getChildren().add(borderPane);

        Label textLabel = new Label();
        textLabel.setText(nodeTextEnum.getText(textLabel.textProperty()));
        textLabel.setId(NamespaceEnum.VALUE_STYLE.getText());
        borderPane.setLeft(textLabel);
        Label valueLabel = new Label();
        valueLabel.setId(NamespaceEnum.DB_VALUE_STYLE.getText());
        borderPane.setRight(valueLabel);

        personalInfoLabels.put(nodeTextEnum, valueLabel);
    }

    private void initLastThreeAwards() {
        Label lastThreeAwardsTitle = new Label();
        lastThreeAwardsTitle.setText(NodeTextEnum.LAST_THREE_AWARDS_TITLE.getText(lastThreeAwardsTitle.textProperty()));
        lastThreeAwardsTitle.setId(NamespaceEnum.SUB_TITLE_STYLE.getText());
        leftSidePart.getLeftPane().getChildren().add(lastThreeAwardsTitle);

        HBox lastThreeAwardsPane = new HBox();
        lastThreeAwardsPane.setAlignment(Pos.CENTER);
        lastThreeAwardsPane.setSpacing(20);
        lastThreeAwardsPane.setId(NamespaceEnum.LAST_THREE_AWARDS_PANE_STYLE.getText());
        leftSidePart.getLeftPane().getChildren().add(lastThreeAwardsPane);

        for (int i = 0; i < 3; i++) {
            BF2Image bf2Image = new BF2Image();
            lastThreeAwards.add(bf2Image);
            lastThreeAwardsPane.getChildren().add(bf2Image.getImageView());
        }
        resetAwardsImages();
        addBorderLine(NodeTextEnum.LAST_AWARD);
        lastAwardLabel = personalInfoLabels.get(NodeTextEnum.LAST_AWARD);
        lastAwardLabel.setText(NodeTextEnum.EMPTY_STRING.getText(lastAwardLabel.textProperty()));
    }

    public void resetAwardsImages() {
        lastThreeAwards.forEach(BF2Image::removeImage);
    }
}
