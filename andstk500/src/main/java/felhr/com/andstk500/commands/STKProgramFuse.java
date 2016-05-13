package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Program Fuse bits. The Fuse bit mapping for the currently supported devices is
 * described in the appendix.
 * Note: For ISP Programming, use the Cmnd_STK_UNIVERSAL command with the appropriate
 * ISP command bytes (found in the device data sheet).
 */
public class STKProgramFuse extends STK500Command
{
    private int fuseLow;
    private int fuseHigh;

    public STKProgramFuse(int fuseLow, int fuseHigh)
    {
        super(STK500Constants.Cmnd_STK_PROG_FUSE, 4);
        this.fuseLow = fuseLow;
        this.fuseHigh = fuseHigh;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_PROG_FUSE);
        buffer.put((byte) fuseLow);
        buffer.put((byte) fuseHigh);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
