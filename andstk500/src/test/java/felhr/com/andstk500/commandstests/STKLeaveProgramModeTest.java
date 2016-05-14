package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKLeaveProgramMode;

/**
 * STKLeaveProgramMode unit test
 */
public class STKLeaveProgramModeTest extends TestCase
{
    private STKLeaveProgramMode candidate;

    @Before
    public void setUp()
    {
        candidate = new STKLeaveProgramMode();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_LEAVE_PROGMODE, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
