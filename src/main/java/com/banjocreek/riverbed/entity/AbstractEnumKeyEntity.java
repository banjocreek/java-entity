/**
 * Copyright (C) Greg Wiley
 *
 * Licensed under the Apache License, Version 2.0 (the "License") under
 * one or more contributor license agreements. See the NOTICE file
 * distributed with this work for information regarding copyright
 * ownership. You may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Media Science Incorporated Confidential
 *
 * (C) Media Science Incorporated
 *
 * This file is subject to the terms and conditions defined in the
 * file, 'NOTICE', which is part of this source code package.
 */
package com.banjocreek.riverbed.entity;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public abstract class AbstractEnumKeyEntity<K extends Enum<K>, V> {

    protected final Map<K, V> data;

    protected AbstractEnumKeyEntity(final Map<K, ? extends V> data) {

        this.data = Collections.unmodifiableMap(new EnumMap<>(data));

    }

    @Override
    public final boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.getClass().isInstance(obj)) {
            return ((AbstractEnumKeyEntity<?, ?>) obj).data.equals(this.data);
        }

        return false;

    }

    @Override
    public final int hashCode() {
        return this.data.hashCode();
    }

    @Override
    public final String toString() {
        return this.data.toString();
    }

}
