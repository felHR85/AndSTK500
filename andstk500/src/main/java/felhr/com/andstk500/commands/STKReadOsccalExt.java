package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read Oscillator calibration byte.
 */
public class STKReadOsccalExt extends STK500Command
{
    private int address;

    public STKReadOsccalExt(int address)
    {
        super(STK500Constants.Cmnd_STK_READ_OSCCAL_EXT, 3);
        this.address = address;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_OSCCAL_EXT);
        buffer.put((byte) address);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
