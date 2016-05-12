package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read a block of data from FLASH or EEPROM of the current device. The data block
 * size should not be larger than 256 bytes.
 */
public class STKReadPage extends STK500Command
{
    public static final String EEPROM = "E";
    public static final String FLASH = "F";

    private int bytesHigh;
    private int bytesLow;
    private int memType;


    public STKReadPage(int bytesHigh, int bytesLow, String memType)
    {
        super(STK500Constants.Cmnd_STK_READ_PAGE, 5);
        this.bytesHigh = bytesHigh;
        this.bytesLow = bytesLow;

        if(memType.equals(EEPROM))
            this.memType = EEPROM.getBytes()[0];
        else
            this.memType = FLASH.getBytes()[0];
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_PAGE);
        buffer.put((byte) bytesHigh);
        buffer.put((byte) bytesLow);
        buffer.put((byte) memType);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
