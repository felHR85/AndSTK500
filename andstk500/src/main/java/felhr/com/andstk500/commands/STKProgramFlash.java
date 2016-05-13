package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Program one word in FLASH memory.
 */
public class STKProgramFlash extends STK500Command
{
    private int flashLow;
    private int flashHigh;

    public STKProgramFlash(int flashLow, int flashHigh)
    {
        super(STK500Constants.Cmnd_STK_PROG_FLASH, 4);
        this.flashLow = flashLow;
        this.flashHigh = flashHigh;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_PROG_FLASH);
        buffer.put((byte) flashLow);
        buffer.put((byte) flashHigh);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
