package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramLock;

/**
 * STKProgramLock unit test
 */
public class STKProgramLockTest extends TestCase
{
    private STKProgramLock candidate;

    @Before
    public void setUp()
    {
        candidate = new STKProgramLock();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_PROG_LOCK, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
