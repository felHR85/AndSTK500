package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKGetSignOn;

/**
 * STKGetSignOn unit test
 */
public class STKGetSignOnTest extends TestCase
{
    private STKGetSignOn candidate;

    @Before
    public void setUp()
    {
        candidate = new STKGetSignOn();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_GET_SIGN_ON, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
