package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadFuseExt;

/**
 * STKReadFuseExt unit test
 */
public class STKReadFuseExtTest extends TestCase
{
    private STKReadFuseExt candidate;

    @Before
    public void setUp()
    {
        candidate = new STKReadFuseExt();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE_EXT, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
