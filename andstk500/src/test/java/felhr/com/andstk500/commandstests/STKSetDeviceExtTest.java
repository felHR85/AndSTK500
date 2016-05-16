package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKSetDevice;
import felhr.com.andstk500.commands.STKSetDeviceExt;

/**
 * STKSetDeviceExt unit test
 */
public class STKSetDeviceExtTest extends TestCase
{
    private int commandSize;
    private int eepromPageSize;
    private int signalPagel;
    private int signalBs2;
    private int resetDisable;

    private STKSetDeviceExt candidate;

    @Before
    public void setUp()
    {
        Random r = new Random();

        commandSize = r.nextInt() & 0xff;
        eepromPageSize = r.nextInt() & 0xff;
        signalPagel = r.nextInt() & 0xff;
        signalBs2 = r.nextInt() & 0xff;
        resetDisable = r.nextInt() & 0xff;

        candidate = new STKSetDeviceExt.STKSetDeviceExtBuilder()
                .setCommandSize(commandSize)
                .setEepromPageSize(eepromPageSize)
                .setSignalPagel(signalPagel)
                .setSignalBs2(signalBs2)
                .setResetDisable(resetDisable)
                .build();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();

        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE_EXT, buffer[0]);
        assertEquals((byte) commandSize, buffer[1]);
        assertEquals((byte) eepromPageSize, buffer[2]);
        assertEquals((byte) signalPagel, buffer[3]);
        assertEquals((byte) signalBs2, buffer[4]);
        assertEquals((byte) resetDisable, buffer[5]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[6]);
    }
}
