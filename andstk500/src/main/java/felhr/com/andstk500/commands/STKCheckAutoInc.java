package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Check if the write/read address is automatically incremented while using the
 * Cmnd_STK_PROG/READ_FLASH/EEPROM commands. Since STK500 always autoincrements
 * the address, this command will always be successful.
 */
public class STKCheckAutoInc extends STK500Command
{
    public STKCheckAutoInc()
    {
        super(STK500Constants.Cmnd_STK_CHECK_AUTOINC, 2);
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_CHECK_AUTOINC);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
