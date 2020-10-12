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
package com.animaker.model.transition;

import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by lemmi on 19.12.16.
 */
@XmlType(name = "drift")
public class Drift extends Transition {

    public enum DriftDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public Drift() {
        super("Drift");
    }

    // drift direction support

    private final ObjectProperty<DriftDirection> direction = new SimpleObjectProperty(this, "direction", DriftDirection.LEFT_TO_RIGHT);

    public final ObjectProperty<DriftDirection> directionProperty() {
        return direction;
    }

    public final void setDirection(DriftDirection direction) {
        this.direction.set(direction);
    }

    public final DriftDirection getDirection() {
        return direction.get();
    }
}
