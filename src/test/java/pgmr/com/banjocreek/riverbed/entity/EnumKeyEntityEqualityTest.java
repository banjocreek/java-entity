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
package pgmr.com.banjocreek.riverbed.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.riverbed.entity.AbstractEntity;

public class EnumKeyEntityEqualityTest {

    private Ent control, identical;

    private Collection<Ent> diffs;

    @Before
    public void setup() {

        final Map<Field, Object> sdata = Collections
                .singletonMap(Field.A, "AA");

        this.control = new Ent(sdata);
        this.identical = new Ent(sdata);

        this.diffs = new ArrayList<>();
        this.diffs.add(new Ent(new EnumMap<>(Field.class)));
        this.diffs.add(new Ent(Collections.singletonMap(Field.A, "DIFF")));
        final HashMap<Field, Object> ddata = new HashMap<>(sdata);
        ddata.put(Field.B, "BB");
        this.diffs.add(new Ent(ddata));

    }

    @Test
    public void testHashIdentical() {
        assertEquals(this.control.hashCode(), this.identical.hashCode());
    }

    @Test
    public void testIdentical() {
        assertTrue(this.control.equals(this.identical));
    }

    @Test
    public void testNotEqualArbitrary() {
        assertFalse(this.control.equals(new Object()));
    }

    @Test
    public void testNotEqualNull() {
        assertFalse(this.control.equals(null));
    }

    @Test
    public void testReflexive() {
        assertTrue(this.control.equals(this.control));
    }

    @Test
    public void testRepeatableHash() {
        assertEquals(this.control.hashCode(), this.control.hashCode());
    }

    @Test
    public void testSymmetric() {
        assertTrue(this.identical.equals(this.control));
    }

    @Test
    public void testUnequal() {
        this.diffs.forEach(d -> {
            assertFalse("{" + this.control + "} neq {" + d + "}",
                    this.control.equals(d));
            assertFalse("{" + d + "} neq {" + this.control + "}",
                    d.equals(this.control));
        });
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
