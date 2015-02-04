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
package pgmr.com.banjocreek.riverbed.entity;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.riverbed.builder.map.MapDelta;
import com.banjocreek.riverbed.entity.AbstractEntity;
import com.banjocreek.riverbed.entity.AbstractImmutableEntityBuilder;

public class ImmutableEntityBuilderTest {

    private Bldr builder;

    @Before
    public void setup() {
        this.builder = new Bldr();
    }

    @Test
    public void testBuilderDataToEntity() {

        /*
         * given a builder with recognizable state
         */
        // SETUP
        final Bldr mutated = this.builder.withA("A");

        /*
         * it produces an entity with corresponding state
         */
        assertEquals(Collections.singletonMap(Field.A, "A"), mutated.done()
                .copyData());

    }

    /**
     * Test fixed defaults dispatch bug.
     */
    @Test
    public void testDefaultsAndValues() {

        final Ent defs = this.builder.withA("DEFA").withB("DEFB").build();
        final Ent vals = this.builder.withB("VB").withC("VC").build();

        final Ent expected = this.builder.withA("DEFA").withB("VB").withC("VC")
                .build();
        final Ent actual = this.builder.withValues(vals).withDefaults(defs)
                .build();

        assertEquals(expected, actual);

    }

    static class Bldr extends
            AbstractImmutableEntityBuilder<Ent, Ent, Field, Object, Ent, Bldr> {

        Bldr() {
            super(Ent::new, Ent::new, Bldr::new);
        }

        Bldr(final Bldr previous, final MapDelta<Field, Object> delta) {
            super(previous, delta);
        }

        Bldr withA(final String v) {
            return new Bldr(this, values(Field.A, v));
        }

        Bldr withB(final String v) {
            return new Bldr(this, values(Field.B, v));
        }

        Bldr withC(final String v) {
            return new Bldr(this, values(Field.C, v));
        }
    }

    static class Ent extends AbstractEntity<Field, Object> {

        Ent(final Map<Field, Object> data) {
            super(data);
        }

        Map<Field, Object> copyData() {
            return Collections.unmodifiableMap(new EnumMap<>(this.data));
        }

    }

    enum Field {
        A, B, C;
    }

}
