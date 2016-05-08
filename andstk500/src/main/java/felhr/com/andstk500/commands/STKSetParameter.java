package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Set the value of a valid parameter in the STK500 starterkit. See the parameters section
 * for valid parameters and their meaning
 */
public class STKSetParameter extends STK500Command
{
    private int parameter;
    private int value;

    public STKSetParameter(int parameter, int value)
    {
        super(STK500Constants.Cmnd_STK_SET_PARAMETER, 4);
        this.parameter = parameter;
        this.value = value;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put((byte) commandId);
        buffer.put((byte) parameter);
        buffer.put((byte) value);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
