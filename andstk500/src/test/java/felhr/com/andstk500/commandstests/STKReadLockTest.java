package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadLock;

/**
 * STKReadLock unit test
 */
public class STKReadLockTest extends TestCase
{
    private STKReadLock candidate;

    @Before
    public void setUp()
    {
        candidate = new STKReadLock();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_LOCK, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
