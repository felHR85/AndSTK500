package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKGetParameter;

/**
 * STKGetParameter unit test
 */
public class STKGetParameterTest extends TestCase
{
    private final int parameterExpected = 0x80;
    private STKGetParameter candidate;

    @Before
    public void setUp()
    {
        candidate = new STKGetParameter(parameterExpected);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_GET_PARAMETER, buffer[0]);
        assertEquals(parameterExpected, buffer[1]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[2]);
    }
}
