package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKLoadAddress;

/**
 * STKLoadAddress unit test
 */
public class STKLoadAddressTest extends TestCase
{
    private final int addrLow = 0x80;
    private final int addrHigh = 0x00;

    private STKLoadAddress candidate;

    @Before
    public void setUp()
    {
        candidate = new STKLoadAddress(addrLow, addrHigh);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_LOAD_ADDRESS, buffer[0]);
        assertEquals((byte) addrLow, buffer[1]);
        assertEquals((byte) addrHigh, buffer[2]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[3]);
    }
}
