package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Load 16-bit address down to starterkit. This command is used to set the address for the
 * next read or write operation to FLASH or EEPROM. Must always be used prior to
 * Cmnd_STK_PROG_PAGE or Cmnd_STK_READ_PAGE
 */
public class STKLoadAddress extends STK500Command
{
    private int addrLow;
    private int addrHigh;

    public STKLoadAddress(int addrLow, int addrHigh)
    {
        super(STK500Constants.Cmnd_STK_LOAD_ADDRESS, 4);
        this.addrLow = addrLow;
        this.addrHigh = addrHigh;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put((byte) STK500Constants.Cmnd_STK_LOAD_ADDRESS);
        buffer.put((byte) addrLow);
        buffer.put((byte) addrHigh);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }
}
