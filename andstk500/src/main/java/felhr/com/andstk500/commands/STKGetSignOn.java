package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * The PC sends this command to check if the starterkit is present on the communication
 channel.
 */
public class STKGetSignOn extends STK500Command
{
    public STKGetSignOn()
    {
        super(STK500Constants.Cmnd_STK_GET_SIGN_ON, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_GET_SIGN_ON);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
