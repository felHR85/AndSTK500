package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read one word from FLASH memory.
 */
public class STKReadFlash extends STK500Command
{
    public STKReadFlash()
    {
        super(STK500Constants.Cmnd_STK_READ_FLASH, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_FLASH);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
