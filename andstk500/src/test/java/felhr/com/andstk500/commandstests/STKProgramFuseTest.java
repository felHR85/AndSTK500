package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramFuse;

/**
 * STKProgramFuse unit test
 */
public class STKProgramFuseTest extends TestCase
{
    private final int fuseLow = 0x80;
    private final int fuseHigh = 0x00;

    private STKProgramFuse candidate;

    @Before
    public void setUp()
    {
        candidate = new STKProgramFuse(fuseLow, fuseHigh);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE, buffer[0]);
        assertEquals((byte) fuseLow, buffer[1]);
        assertEquals((byte) fuseHigh, buffer[2]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[3]);
    }
}
