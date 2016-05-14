package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKCheckAutoInc;

/**
 * STKCheckAutoInc unit test
 */
public class STKCheckAutoIncTest extends TestCase
{
    private STKCheckAutoInc candidate;

    @Before
    public void setUp()
    {
        candidate = new STKCheckAutoInc();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_CHECK_AUTOINC, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
