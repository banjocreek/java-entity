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

import com.banjocreek.riverbed.entity.AbstractEnumKeyEntity;
import com.banjocreek.riverbed.entity.AbstractMutableEnumKeyEntityBuilder;

public class MutableEnumKeyEntityBuilderTest {

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
        this.builder.withA("A");

        /*
         * it produces an entity with corresponding state
         */
        assertEquals("A", this.builder.merge().copyData().get(Field.A));
        assertEquals(Collections.singletonMap(Field.A, "A"), this.builder
                .merge().copyData());

    }

    static class Bldr extends
            AbstractMutableEnumKeyEntityBuilder<Ent, Field, Ent, Bldr> {

        Bldr() {
            super(Field.class, Ent::new);
        }

        Bldr withA(final String v) {
            values(Field.A, v);
            return this;
        }

        Bldr withB(final String v) {
            values(Field.B, v);
            return this;
        }

        Bldr withC(final String v) {
            values(Field.C, v);
            return this;
        }
    }

    static class Ent extends AbstractEnumKeyEntity<Field> {

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
