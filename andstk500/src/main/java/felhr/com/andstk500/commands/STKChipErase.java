package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Erase device
 */
public class STKChipErase extends STK500Command
{
    public STKChipErase()
    {
        super(STK500Constants.Cmnd_STK_CHIP_ERASE, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_CHIP_ERASE);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
