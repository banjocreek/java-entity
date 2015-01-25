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
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.riverbed.entity.AbstractEnumKeyEntity;

public class EnumKeyEntityTest {

    private Ent ent;

    private Map<Field, Object> sourceData;

    @Before
    public void setup() {

        this.sourceData = new HashMap<>();
        this.sourceData.put(Field.A, "A");
        this.sourceData.put(Field.B, "B");

        this.ent = new Ent(this.sourceData);

    }

    @Test
    public void testData() {

        /*
         * given an entity created from source data
         */
        // SETUP

        /*
         * its state reflects its source
         */
        assertEquals(this.sourceData, this.ent.copyData());

    }

    @Test
    public void testIsolatedData() {

        /*
         * given an entity created from source data
         */
        // SETUP
        final Object dataSnap = this.ent.copyData();

        /*
         * when the source data is modified
         */
        this.sourceData.put(Field.A, "AA");
        this.sourceData.put(Field.C, "CC");

        /*
         * it does not change the entity data
         */
        assertEquals(dataSnap, this.ent.copyData());

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
