package com.animaker.view.skins.builder;

import com.animaker.model.*;
import com.animaker.view.builder.SlidesPaletteView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by lemmi on 21.12.16.
 */
public class SlidesPaletteViewSkin extends SkinBase<SlidesPaletteView> {

    private ListView<Slide> listView;

    public SlidesPaletteViewSkin(SlidesPaletteView view) {
        super(view);

        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setFocusTraversable(false);
        //listView.setCellFactory(listView -> new SlideCell());
        view.selectedSlideProperty().bind(listView.getSelectionModel().selectedItemProperty());

        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.setAlignment(Pos.CENTER_LEFT);

        FontAwesomeIconView plusIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
        plusIcon.setGlyphSize(16);
        Button addSlideButton = new Button();
        addSlideButton.setOnAction(evt -> {
            com.animaker.model.Slider slider = view.getSlider();
            if (slider != null) {
                Slide slide = new Slide("Untitled");
                slider.getSlides().add(slide);
            }
        });

        addSlideButton.setGraphic(plusIcon);
        addSlideButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        hbox.getChildren().add(addSlideButton);

        Label title = new Label("Slides");
        title.setMaxWidth(Double.MAX_VALUE);
        title.getStyleClass().add("palette-title");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(title);
        borderPane.setCenter(listView);
        borderPane.setBottom(hbox);

        getChildren().add(borderPane);

        updateView();
    }

    private void updateView() {
        com.animaker.model.Slider slider = getSkinnable().getSlider();
        if (slider != null) {
            listView.setItems(slider.getSlides());
        } else {
            listView.setItems(null);
        }

        if (!listView.getItems().isEmpty()) {
            listView.getSelectionModel().select(0);
        }
    }

    static class SlideCell extends ListCell<Slide> {

        public SlideCell() {
        }
    }
}