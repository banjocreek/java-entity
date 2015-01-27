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
import java.util.function.Function;

import com.banjocreek.riverbed.builder.map.AbstractImmutableMapBuilder;
import com.banjocreek.riverbed.builder.map.MapDelta;

public abstract class AbstractImmutableEntityBuilder<R, P, K, ENT extends AbstractEntity<K>, Z extends EntityBuilder<ENT, Z>>
        extends AbstractImmutableMapBuilder<K, Object, R, P> implements
        EntityBuilder<ENT, Z> {

    protected AbstractImmutableEntityBuilder(
            final AbstractImmutableEntityBuilder<R, P, K, ENT, Z> previous,
            final MapDelta<K, Object> delta) {
        super(previous, delta);
    }

    protected AbstractImmutableEntityBuilder(
            final Function<Map<K, Object>, R> rootConstructor,
            final Function<Map<K, Object>, P> parentConstructor) {
        super(rootConstructor, parentConstructor);
    }

    @Override
    public final Z withDefaults(final ENT mdefs) {
        defaults(mdefs.data);
        @SuppressWarnings("unchecked")
        final Z z = (Z) this;
        return z;
    }

    @Override
    public final Z withValues(final ENT mvals) {
        defaults(mvals.data);
        @SuppressWarnings("unchecked")
        final Z z = (Z) this;
        return z;
    }

}
