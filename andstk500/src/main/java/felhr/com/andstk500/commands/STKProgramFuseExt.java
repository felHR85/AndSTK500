package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Program Extended Fuse bits. The Fuse bit mapping for the currently supported devices
 * is described in the appendix.
 * Note: For ISP Programming, use the Cmnd_STK_UNIVERSAL command with the appropriate
 * ISP command bytes (found in the device data sheet).
 */
public class STKProgramFuseExt extends STK500Command
{
    private int fuseLow;
    private int fuseHigh;
    private int fuseExt;

    public STKProgramFuseExt(int fuseLow, int fuseHigh, int fuseExt)
    {
        super(STK500Constants.Cmnd_STK_PROG_FUSE_EXT, 5);
        this.fuseLow = fuseLow;
        this.fuseHigh = fuseHigh;
        this.fuseExt = fuseExt;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_PROG_FUSE_EXT);
        buffer.put((byte) fuseLow);
        buffer.put((byte) fuseHigh);
        buffer.put((byte) fuseExt);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
