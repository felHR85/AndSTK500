package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadOsccalExt;

/**
 * STKReadOsccalExt unit test
 */
public class STKReadOsccalExtTest extends TestCase
{
    private final int address = 0x80;

    private STKReadOsccalExt candidate;

    @Before
    public void setUp()
    {
        candidate = new STKReadOsccalExt(address);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_OSCCAL_EXT, buffer[0]);
        assertEquals((byte) address, buffer[1]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[2]);
    }
}
