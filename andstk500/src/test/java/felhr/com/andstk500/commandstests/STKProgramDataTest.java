package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramData;

/**
 * STKProgramData unit test
 */
public class STKProgramDataTest extends TestCase
{
    private final int dataByte = 0x80;

    private STKProgramData candidate;

    @Before
    public void setUp()
    {
        candidate = new STKProgramData(dataByte);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_PROG_DATA, buffer[0]);
        assertEquals((byte) dataByte, buffer[1]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[2]);
    }
}
