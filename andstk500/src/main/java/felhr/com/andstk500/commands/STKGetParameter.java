package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Get the value of a valid parameter from the STK500 starterkit. If the parameter is not
 * used, the same parameter will be returned together with a Resp_STK_FAILED
 * response to indicate the error. See the parameters section for valid parameters and their
 * meaning.
 */
public class STKGetParameter extends STK500Command
{
    private int parameter;

    public STKGetParameter(int parameter)
    {
        super(STK500Constants.Cmnd_STK_GET_PARAMETER, 3);
        this.parameter = parameter;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put((byte) commandId);
        buffer.put((byte) parameter);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
