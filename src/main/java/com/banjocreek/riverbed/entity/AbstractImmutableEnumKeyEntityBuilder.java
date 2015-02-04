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

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.banjocreek.riverbed.builder.enummap.AbstractImmutableEnumMapBuilder;
import com.banjocreek.riverbed.builder.map.MapDelta;

public abstract class AbstractImmutableEnumKeyEntityBuilder<R, P, K extends Enum<K>, V, ENT extends AbstractEnumKeyEntity<K, V>, Z extends EntityBuilder<ENT, Z>>
        extends AbstractImmutableEnumMapBuilder<K, V, R, P> implements
        EntityBuilder<ENT, Z> {

    private final BiFunction<? super Z, ? super MapDelta<K, V>, ? extends Z> nextConstructor;

    protected AbstractImmutableEnumKeyEntityBuilder(
            final AbstractImmutableEnumKeyEntityBuilder<R, P, K, V, ENT, Z> previous,
            final MapDelta<K, V> delta) {
        super(previous, delta);
        this.nextConstructor = previous.nextConstructor;
    }

    protected AbstractImmutableEnumKeyEntityBuilder(
            final Class<K> keyType,
            final Function<Map<K, V>, R> rootConstructor,
            final Function<Map<K, V>, P> parentConstructor,
            final BiFunction<? super Z, ? super MapDelta<K, V>, ? extends Z> nextConstructor) {
        super(keyType, rootConstructor, parentConstructor);
        this.nextConstructor = Objects.requireNonNull(nextConstructor);
    }

    @Override
    public final Z withDefaults(final ENT mdefs) {
        @SuppressWarnings("unchecked")
        final Z z = (Z) this;
        return this.nextConstructor.apply(z, defaults(mdefs.data));
    }

    @Override
    public final Z withValues(final ENT mvals) {
        @SuppressWarnings("unchecked")
        final Z z = (Z) this;
        return this.nextConstructor.apply(z, values(mvals.data));
    }

}
