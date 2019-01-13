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
package ca.tulip.sinope.core.appdata;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * The Class SinopeRoomTempData.
 */
public class SinopeOutputIntensityData extends SinopeAppData {

    /**
     * Instantiates a new sinope set point temp data.
     */
    public SinopeOutputIntensityData() {

        super(new byte[] { 0x00, 0x00, 0x10, 0x00 }, new byte[] { 0, 0 });
    }

    /**
     * Gets the dimmer output intensity.
     *
     * @return the dimmer outut intensity (0-100)
     */
    public int getOutputIntensity() {
        if (getData() != null) {
            ByteBuffer bb = ByteBuffer.wrap(getData());
            bb.order(ByteOrder.LITTLE_ENDIAN);
            return (int)(bb.get()) & 0xFF;
        }
        return 0;
    }

    /**
     * @see SinopeAppData#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (getData() != null) {
            sb.append(String.format("\nOutput intensity %d %%", this.getOutputIntensity()));
        }
        return sb.toString();
    }

    public void setOutputIntensity(int newOutputIntensity) {
        ByteBuffer bb = ByteBuffer.wrap(getData());
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte) (newOutputIntensity & 0xFF));
    }

}
