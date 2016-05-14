package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read Lock bits. The Lock bit mapping for the currently supported devices is described in
 * the appendix.
 * Note: For ISP Programming, use the Cmnd_STK_UNIVERSAL command with the appropriate
 * ISP command bytes (found in the device data sheet).
 * Note that some device combine Lock bits and Fuse bits in way that requires some modification
 * of the read-back fuse before it can be interpreted normally. This is described
 * closer in the appendix.
 */
public class STKReadLock extends STK500Command
{
    public STKReadLock()
    {
        super(STK500Constants.Cmnd_STK_READ_LOCK, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_LOCK);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
