package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read Extended Fuse bits. The Fuse bit mapping for the currently supported devices is
 * described in the appendix.
 * Note: For ISP Programming, use the Cmnd_STK_UNIVERSAL command with the appropriate
 * ISP command bytes (found in the device data sheet).
 * Note that some devices combine Lock bits and Fuse bits in way that actually requires
 * using the Cmnd_STK_READ_LOCK to retrieve the Fuse byte(s). This is described
 * closer in the appendix.
 */
public class STKReadFuseExt extends STK500Command
{
    public STKReadFuseExt()
    {
        super(STK500Constants.Cmnd_STK_READ_FUSE_EXT, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_FUSE_EXT);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
