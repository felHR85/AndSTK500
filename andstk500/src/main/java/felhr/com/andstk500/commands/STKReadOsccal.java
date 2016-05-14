package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read Oscillator calibration byte.
 */
public class STKReadOsccal extends STK500Command
{
    public STKReadOsccal()
    {
        super(STK500Constants.Cmnd_STK_READ_OSCCAL, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_OSCCAL);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
