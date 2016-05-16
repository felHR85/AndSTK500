package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKSetParameter;

/**
 * STKSetParameter unit test
 */
public class STKSetParameterTest extends TestCase
{
    private final int parameter = 0x80;
    private final int value = 0xff;

    private STKSetParameter candidate;

    @Before
    public void setUp()
    {
        candidate = new STKSetParameter(parameter, value);
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_SET_PARAMETER, buffer[0]);
        assertEquals((byte) parameter, buffer[1]);
        assertEquals((byte) value, buffer[2]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[3]);

    }
}
