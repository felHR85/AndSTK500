package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKChipErase;

/**
 * STKChipErase unit test
 */
public class STKChipEraseTest extends TestCase
{
    private STKChipErase candidate;

    @Before
    public void setUp()
    {
        candidate = new STKChipErase();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_CHIP_ERASE, buffer[0]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[1]);
    }
}
