package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadFuse;

/**
 * STKReadFuse unit test
 */
public class STKReadFuseTest extends TestCase
{
    private STKReadFuse candidate;

    @Before
    public void setUp()
    {
        candidate = new STKReadFuse();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
