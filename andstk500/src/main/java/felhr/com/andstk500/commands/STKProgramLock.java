package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Program Lock bits. The Lock bit mapping for the currently supported devices is
 * described in the appendix.
 * Note: For ISP Programming, use the Cmnd_STK_UNIVERSAL command with the appropriate
 * ISP command bytes (found in the device data sheet).
 */
public class STKProgramLock extends STK500Command
{
    public STKProgramLock()
    {
        super(STK500Constants.Cmnd_STK_PROG_LOCK, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_PROG_LOCK);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
