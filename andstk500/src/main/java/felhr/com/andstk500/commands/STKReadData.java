package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read one byte from EEPROM memory.
 */
public class STKReadData extends STK500Command
{
    public STKReadData()
    {
        super(STK500Constants.Cmnd_STK_READ_DATA, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_DATA);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
