package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadPage;

/**
 * STKReadPage unit test
 */
public class STKReadPageTest extends TestCase
{
    private final int bytesHigh = 0x00;
    private final int bytesLow = 0x80;
    private String memTypeE = "E";
    private String memTypeF = "F";

    private STKReadPage candidateE;
    private STKReadPage candidateF;

    @Before
    public void setUp()
    {
        candidateE = new STKReadPage(bytesHigh, bytesLow, memTypeE);
        candidateF = new STKReadPage(bytesHigh, bytesLow, memTypeF);
    }

    @Test
    public void testCommandBuffer()
    {
        // EEPROM
        byte[] buffer = candidateE.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_PAGE, buffer[0]);
        assertEquals((byte) bytesHigh, buffer[1]);
        assertEquals((byte) bytesLow, buffer[2]);
        assertEquals(STKReadPage.EEPROM.getBytes()[0], buffer[3]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[4]);

        // FLASH
        buffer = candidateF.getCommandBuffer();
        assertEquals(STK500Constants.Cmnd_STK_READ_PAGE, buffer[0]);
        assertEquals((byte) bytesHigh, buffer[1]);
        assertEquals((byte) bytesLow, buffer[2]);
        assertEquals(STKReadPage.FLASH.getBytes()[0], buffer[3]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[4]);
    }

    @Test
    public void testGetDataLength()
    {
        assertEquals(0x0080, candidateE.getDataLength());
    }
}
