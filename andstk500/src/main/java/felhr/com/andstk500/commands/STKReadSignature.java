package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Read signature bytes.
 */
public class STKReadSignature extends STK500Command
{
    public STKReadSignature()
    {
        super(STK500Constants.Cmnd_STK_READ_SIGN, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_READ_SIGN);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
