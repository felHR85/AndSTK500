package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Set extended programming parameters for the current device.
 */
public class STKSetDeviceExt extends STK500Command
{
    private int commandSize;
    private int eepromPageSize;
    private int signalPagel;
    private int signalBs2;
    private int resetDisable;

    public STKSetDeviceExt()
    {
        super(STK500Constants.Cmnd_STK_SET_DEVICE_EXT, 7);
        commandSize = 0x05;
        eepromPageSize = 0x04;
        signalPagel = 0xd7;
        signalBs2 = 0xc2;
        resetDisable = 0x00;
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte) commandId);
        buffer.put((byte) commandSize);
        buffer.put((byte) eepromPageSize);
        buffer.put((byte) signalPagel);
        buffer.put((byte) signalBs2);
        buffer.put((byte) resetDisable);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }

    public class STKSetDeviceExtBuilder
    {
        private STKSetDeviceExt stkSetDeviceExt;

        public STKSetDeviceExtBuilder()
        {
            stkSetDeviceExt = new STKSetDeviceExt();
        }

        public STKSetDeviceExtBuilder setCommandSize(int commandSize)
        {
            stkSetDeviceExt.commandSize = commandSize;
            return this;
        }

        public STKSetDeviceExtBuilder setEepromPageSize(int eepromPageSize)
        {
            stkSetDeviceExt.eepromPageSize = eepromPageSize;
            return this;
        }

        public STKSetDeviceExtBuilder setSignalPagel(int signalPagel)
        {
            stkSetDeviceExt.signalPagel = signalPagel;
            return this;
        }

        public STKSetDeviceExtBuilder setSignalBs2(int signalBs2)
        {
            stkSetDeviceExt.signalBs2 = signalBs2;
            return this;
        }

        public STKSetDeviceExtBuilder setResetDisable(int resetDisable)
        {
            stkSetDeviceExt.resetDisable = resetDisable;
            return this;
        }

        public STKSetDeviceExt build()
        {
            return stkSetDeviceExt;
        }
    }
}
