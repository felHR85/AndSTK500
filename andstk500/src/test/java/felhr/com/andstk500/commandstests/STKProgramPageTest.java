package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramPage;

/**
 * STKProgramPage unit test
 */
public class STKProgramPageTest extends TestCase
{

    private String memTypeE = "E";
    private String memTypeF = "F";
    private byte[] data;

    private int bytesHigh;
    private int bytesLow;

    private STKProgramPage candidate1;
    private STKProgramPage candidate2;

    @Before
    public void setUp()
    {
        data = new byte[256];

        bytesHigh = data.length >> 8;
        bytesLow = data.length & 0xff;

        new Random().nextBytes(data);
        candidate1 = new STKProgramPage(memTypeE, data);
        candidate2 = new STKProgramPage(memTypeF, data);
    }

    @Test
    public void testCommandBuffer()
    {
        // EEPROM
        byte[] buffer = candidate1.getCommandBuffer();

        assertEquals(STK500Constants.Cmnd_STK_PROG_PAGE, buffer[0]);
        assertEquals((byte) bytesHigh, buffer[1]);
        assertEquals((byte) bytesLow, buffer[2]);
        assertEquals(memTypeE.getBytes()[0], buffer[3]);

        byte[] subBuffer = new byte[256];
        System.arraycopy(buffer, 4, subBuffer, 0, 256);

        int i = 0;
        for(Byte b : subBuffer)
        {
            assertEquals(data[i], b.byteValue());
            i++;
        }

        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[buffer.length-1]);

        // FLASH
        buffer = candidate2.getCommandBuffer();

        assertEquals(STK500Constants.Cmnd_STK_PROG_PAGE, buffer[0]);
        assertEquals((byte) bytesHigh, buffer[1]);
        assertEquals((byte) bytesLow, buffer[2]);
        assertEquals(memTypeF.getBytes()[0], buffer[3]);

        subBuffer = new byte[256];
        System.arraycopy(buffer, 4, subBuffer, 0, 256);

        i = 0;
        for(Byte b : subBuffer)
        {
            assertEquals(data[i], b.byteValue());
            i++;
        }

        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[buffer.length-1]);
    }
}
