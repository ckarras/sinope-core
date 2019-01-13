/**
 * Copyright (c) 2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * @author Christos Karras
 */
package ca.tulip.sinope.core;

import ca.tulip.sinope.core.appdata.SinopeOutputIntensityData;
import ca.tulip.sinope.core.appdata.SinopeRoomTempData;
import ca.tulip.sinope.util.ByteUtil;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SinopeOutputIntensityDataTest {

    @Test
    public void requestToByteTest() {
        byte[] expected = {
                // preamble
                0x55,
                // framectl
                0x00,
                // payload size
                0x16,0x00,
                // command
                0x40,0x02,
                // seq
                0x78,0x56,0x34,0x12,
                // req type
                0x00,
                // reserved
                0x00,0x00,0x00,0x00,0x00,0x00,
                // device id
                0x44,0x04,0x00,0x00,
                // app data size
                0x04,
                // app data id
                0x00,0x10,0x00,0x00,
                // empty payload for request
                // crc
                0x67
        };

        System.out.println(ByteUtil.toString(expected));
        SinopeDataReadRequest dr = new SinopeDataReadRequest(new byte[] { 0x12, 0x34, 0x56, 0x78 },
                new byte[] { 0, 0, 0x04, 0x44 }, new SinopeOutputIntensityData());
        byte[] actual = dr.getPayload();
        System.out.println(ByteUtil.toString(actual));
        assertArrayEquals("failure - byte arrays not same", expected, actual);

    }
}
