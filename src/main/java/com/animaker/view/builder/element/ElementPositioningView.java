/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.animaker.view.builder.element;

import com.animaker.model.Element;
import com.animaker.model.Element.LayoutStrategy;
import com.animaker.model.Element.Side;
import com.animaker.view.builder.Workbench;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

/**
 * Created by lemmi on 10.03.17.
 */
public class ElementPositioningView extends ElementSettingsView<Element> {

    private ComboBox<LayoutStrategy> layoutBox;
    private TextField xLayoutField;
    private TextField yLayoutField;
    private ComboBox<Side> sideBox;
    private ComboBox<Pos> positionBox;

    public ElementPositioningView(Workbench workbench) {
        super(workbench);

        layoutBox = new ComboBox<>();
        xLayoutField = new TextField();
        yLayoutField = new TextField();
        sideBox = new ComboBox<>();
        positionBox = new ComboBox<>();

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.getChildren().addAll(
                new Label("X:"), xLayoutField,
                new Label("Y:"), yLayoutField,
                new Label("Side:"), sideBox,
                new Label("Position:"), positionBox
        );

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(layoutBox);
        borderPane.setCenter(hbox);

        getChildren().add(borderPane);

        layoutBox.getItems().setAll(LayoutStrategy.values());
        sideBox.getItems().setAll(Side.values());
        positionBox.getItems().setAll(Pos.values());

        elementProperty().bind(workbench.selectedElementProperty());
    }

    @Override
    protected void update(Element oldElement, Element newElement) {
        if (oldElement != null) {
            Bindings.unbindBidirectional(layoutBox.valueProperty(), oldElement.layoutStrategyProperty());
            Bindings.unbindBidirectional(xLayoutField.textProperty(), oldElement.layoutXProperty());
            Bindings.unbindBidirectional(yLayoutField.textProperty(), oldElement.layoutYProperty());
            Bindings.unbindBidirectional(sideBox.valueProperty(), oldElement.sideProperty());
            Bindings.unbindBidirectional(positionBox.valueProperty(), oldElement.positionProperty());
        }

        if (newElement != null) {
            Bindings.bindBidirectional(layoutBox.valueProperty(), newElement.layoutStrategyProperty());
            Bindings.bindBidirectional(xLayoutField.textProperty(), newElement.layoutXProperty(), new NumberStringConverter());
            Bindings.bindBidirectional(yLayoutField.textProperty(), newElement.layoutYProperty(), new NumberStringConverter());
            Bindings.bindBidirectional(sideBox.valueProperty(), newElement.sideProperty());
            Bindings.bindBidirectional(positionBox.valueProperty(), newElement.positionProperty());
        }
    }
}
