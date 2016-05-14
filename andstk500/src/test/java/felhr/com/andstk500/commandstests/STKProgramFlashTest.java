package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramFlash;

/**
 * STKProgramFlash unit test
 */
public class STKProgramFlashTest extends TestCase
{
    private final int flashLow = 0x80;
    private final int flashHigh = 0x00;

    private STKProgramFlash candidate;

    @Before
    public void setUp()
    {
        candidate = new STKProgramFlash(flashLow, flashHigh);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FLASH, buffer[0]);
        assertEquals((byte) flashLow, buffer[1]);
        assertEquals((byte) flashHigh, buffer[2]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[3]);
    }
}
