package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Program one byte in EEPROM memory.
 */
public class STKProgramData extends STK500Command
{
    private int dataByte;

    public STKProgramData(int dataByte)
    {
        super(STK500Constants.Cmnd_STK_PROG_DATA, 3);
        this.dataByte = dataByte;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) STK500Constants.Cmnd_STK_PROG_DATA);
        buffer.put((byte) dataByte);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
