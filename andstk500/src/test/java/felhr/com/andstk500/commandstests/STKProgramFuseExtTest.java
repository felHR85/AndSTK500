package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramFuseExt;

/**
 * STKProgramFuseExt unit test
 */
public class STKProgramFuseExtTest extends TestCase
{
    private final int fuseLow = 0x80;
    private final int fuseHigh = 0x00;
    private final int fuseExt = 0xff;

    private STKProgramFuseExt candidate;

    @Before
    public void setUp()
    {
        candidate = new STKProgramFuseExt(fuseLow, fuseHigh, fuseExt);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE_EXT, buffer[0]);
        assertEquals((byte) fuseLow, buffer[1]);
        assertEquals((byte) fuseHigh, buffer[2]);
        assertEquals((byte) fuseExt, buffer[3]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[4]);
    }
}
