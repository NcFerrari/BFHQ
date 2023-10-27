package lp.fe.javafx.bf2components.mapinfo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.MapInfoEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

public class MapInfo extends BF2Component {

    private final ListView<Label> mapList = new ListView<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final BorderPane teamFlags = new BorderPane();
    private final Label firstTeam = new Label();
    private final Label secondTeam = new Label();
    private final Label title = new Label();
    private final Label mapNeedForRibbonLabel = new Label();
    private final ImageView ribbonImageView = new ImageView();
    private Stage stage;
    private BF2Image bf2MapImage;

    public MapInfo() {
        super(NodeTextEnum.TAB_MENU_MAP_INFO);
        NodeTextEnum.getMaps().forEach(map -> {
            Label label = new Label();
            label.setId(map.name());
            label.setText(map.getText(label.textProperty()));
            label.setTextFill(Color.WHITE);
            mapList.getItems().add(label);
        });

        ((BorderPane) getLeftSidePart().getMainPane().getChildren().get(0)).setCenter(mapList);
        mapList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showMap(NodeTextEnum.valueOf(newValue.getId())));

        initTopRightSide();
        initBottomRightSide();
    }

    private void initTopRightSide() {
        firstTeam.setText(NodeTextEnum.EMPTY_STRING.getText(firstTeam.textProperty()));
        firstTeam.setTextFill(Color.WHITE);
        secondTeam.setText(NodeTextEnum.EMPTY_STRING.getText(secondTeam.textProperty()));
        secondTeam.setTextFill(Color.WHITE);
        title.setText(NodeTextEnum.EMPTY_STRING.getText(title.textProperty()));
        title.setId(NamespaceEnum.MAP_TITLE_STYLE.getText());
        getRightSidePart().getRightPane().setTop(new VBox(
                new BorderPane(title, null, null, null, null),
                new BorderPane(null, null, secondTeam, null, firstTeam),
                teamFlags));
    }

    private void initBottomRightSide() {
        mapNeedForRibbonLabel.setText(NodeTextEnum.EMPTY_STRING.getText(mapNeedForRibbonLabel.textProperty()));
        mapNeedForRibbonLabel.setTextFill(Color.LIGHTGRAY);
        mapNeedForRibbonLabel.setFont(new Font(16));
        HBox hbox = new HBox(mapNeedForRibbonLabel, ribbonImageView);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        getRightSidePart().getRightPane().setBottom(hbox);
    }

    private void showMap(NodeTextEnum mapNodeText) {
        int mapId = Integer.parseInt(mapNodeText.name().split(NamespaceEnum.UNDERSCORE.getText())[1]);
        MapInfoEnum mapInfo = MapInfoEnum.valueOf(NamespaceEnum.MAP.getText() + mapId);

        NodeTextEnum.getComponentsForTranslate().replace(firstTeam.textProperty(), mapInfo.getFirstTeam());
        NodeTextEnum.getComponentsForTranslate().replace(secondTeam.textProperty(), mapInfo.getSecondTeam());
        NodeTextEnum.getComponentsForTranslate().replace(title.textProperty(), mapNodeText);
        teamFlags.setLeft(pictureService.getFactionBF2Image(mapInfo.getIdFirstTeam()).getImageView());
        teamFlags.setRight(pictureService.getFactionBF2Image(mapInfo.getIdSecondTeam()).getImageView());
        if (mapInfo.getIdForRibbon() != null) {
            ribbonImageView.setImage(pictureService.getSmallAwardBF2Image(mapInfo.getIdForRibbon(), 1).getImage());
        } else {
            ribbonImageView.setImage(null);
        }
        NodeTextEnum.getComponentsForTranslate().replace(mapNeedForRibbonLabel.textProperty(),
                NodeTextEnum.valueOf((NamespaceEnum.FOR_RIBBON.getText() + mapInfo.getIdForRibbon()).toUpperCase()));

        bf2MapImage = pictureService.getMapBF2Image(mapId);
        getRightSidePart().getRightPane().setCenter(bf2MapImage.getImageView());
        resizeBF2Map();
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        this.stage = stage;
        double oneThird = stage.getWidth() / 3;
        mapList.setPrefWidth(oneThird);
        resizeBF2Map();
    }

    private void resizeBF2Map() {
        if (bf2MapImage != null) {
            double twoThird = stage.getWidth() / 2;
            bf2MapImage.setImageViewSize(twoThird, twoThird / 1.8);
            ((ImageView) teamFlags.getLeft()).setFitWidth(twoThird / 4.9);
            ((ImageView) teamFlags.getLeft()).setFitHeight(twoThird / 9.8);
            ((ImageView) teamFlags.getRight()).setFitWidth(((ImageView) teamFlags.getLeft()).getFitWidth());
            ((ImageView) teamFlags.getRight()).setFitHeight(((ImageView) teamFlags.getLeft()).getFitHeight());
            ribbonImageView.setFitWidth(((ImageView) teamFlags.getLeft()).getFitWidth() / 2);
            ribbonImageView.setFitHeight(((ImageView) teamFlags.getLeft()).getFitHeight() / 4);
        }
    }
}
