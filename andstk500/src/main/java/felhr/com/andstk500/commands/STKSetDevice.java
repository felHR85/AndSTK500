package felhr.com.andstk500.commands;

import java.nio.ByteBuffer;

/**
 * Set the device Programming parameters for the current device. These parameters must
 * be set before the starterkit can enter Programming mode.
 */
public class STKSetDevice extends STK500Command
{
    private int deviceCode; // Device code as defined in “devices.h”
    private int revision; // Device revision. Currently not used. Should be set to 0.
    private int progType; // Defines which Program modes is supported: “0” – Both Parallel/High-voltage and Serial mode “1” – Only Parallel/High-voltage mode
    private int parMode; // Defines if the device has a full parallel interface or a pseudo parallel programming interface: “0” – Pseudo parallel interface “1” – Full parallel interface
    private int polling; // Defines if polling may be used during SPI access: “0” – No polling may be used “1” – Polling may be used
    private int selfTimed; // Defines if programming instructions are self timed: “0” – Not self timed “1” – Self timed
    private int lockBytes; // Number of Lock bytes. Currently not used. Should be set to actual number of Lock bytes for future compability.
    private int fuseBytes; // Number of Fuse bytes. Currently not used. Should be set to actual number of Fuse bytes for future caompability.
    private int flashPollVal1; // FLASH polling value. See Data Sheet for the device.
    private int flashPollVal2; // FLASH polling value. Same as “flashpollval1”
    private int eepromPollVal1; // EEPROM polling value 1 (P1). See data sheet for the device.
    private int eepromPollVal2; // EEPROM polling value 2 (P2). See data sheet for device
    private int pageSizeHigh; // Page size in bytes for pagemode parts, High Byte of 16- bit value.
    private int pageSizeLow; // Page size in bytes for pagemode parts, Low Byte of 16- bit value.
    private int eepromSizeHigh; // EEPROM size in bytes, High Byte of 16-bit value.
    private int eepromSizeLow; // EEPROM size in bytes, Low Byte of 16-bit value.
    private int flashSize4; // FLASH size in bytes, byte 4 (High Byte) of 32-bit value.
    private int flashSize3; // FLASH size in bytes, byte 3 of 32-bit value.
    private int flashSize2; // FLASH size in bytes, byte 2 of 32-bit value.
    private int flashSize1; // FLASH size in bytes, byte 1 (Low Byte) of 32-bit value.

    public STKSetDevice()
    {
        super(STK500Constants.Cmnd_STK_SET_DEVICE, 22);
        //TODO: Default values
    }

    @Override
    public byte[] getCommandBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(22);
        buffer.put((byte) commandId);
        buffer.put((byte) deviceCode);
        buffer.put((byte) revision);
        buffer.put((byte) progType);
        buffer.put((byte) parMode);
        buffer.put((byte) polling);
        buffer.put((byte) selfTimed);
        buffer.put((byte) lockBytes);
        buffer.put((byte) fuseBytes);
        buffer.put((byte) flashPollVal1);
        buffer.put((byte) flashPollVal2);
        buffer.put((byte) eepromPollVal1);
        buffer.put((byte) eepromPollVal2);
        buffer.put((byte) pageSizeHigh);
        buffer.put((byte) pageSizeLow);
        buffer.put((byte) eepromSizeHigh);
        buffer.put((byte) eepromSizeLow);
        buffer.put((byte) flashSize4);
        buffer.put((byte) flashSize3);
        buffer.put((byte) flashSize2);
        buffer.put((byte) flashSize1);
        buffer.put((byte) STK500Constants.Sync_CRC_EOP);
        return buffer.array();
    }

    public class STKSetDeviceBuilder
    {
        private STKSetDevice setDevice;

        public STKSetDeviceBuilder()
        {
            setDevice = new STKSetDevice();
        }

        public STKSetDeviceBuilder setDeviceCode(int deviceCode)
        {
            setDevice.deviceCode = deviceCode;
            return this;
        }

        public STKSetDeviceBuilder setRevision(int rev)
        {
            setDevice.revision = rev;
            return this;
        }

        public STKSetDeviceBuilder setProgType(int prog)
        {
            setDevice.progType = prog;
            return this;
        }

        public STKSetDeviceBuilder setParMode(int par)
        {
            setDevice.parMode = par;
            return this;
        }

        public STKSetDeviceBuilder setPolling(int poll)
        {
            setDevice.polling = poll;
            return this;
        }

        public STKSetDeviceBuilder setSelfTimed(int self)
        {
            setDevice.selfTimed = self;
            return this;
        }

        public STKSetDeviceBuilder setLockBytes(int lock)
        {
            setDevice.lockBytes = lock;
            return this;
        }

        public STKSetDeviceBuilder setFuseBytes(int fuse)
        {
            setDevice.fuseBytes = fuse;
            return this;
        }

        public STKSetDeviceBuilder setFlashPollVal1(int val1)
        {
            setDevice.flashPollVal1 = val1;
            return this;
        }

        public STKSetDeviceBuilder setFlashPollVal2(int val2)
        {
            setDevice.flashPollVal2 = val2;
            return this;
        }

        public STKSetDeviceBuilder setEepromPollVal1(int val1)
        {
            setDevice.eepromPollVal1 = val1;
            return this;
        }

        public STKSetDeviceBuilder setEepromPollVal2(int val2)
        {
            setDevice.eepromPollVal2 = val2;
            return this;
        }

        public STKSetDeviceBuilder setPageSizeHigh(int pageSizeH)
        {
            setDevice.pageSizeHigh = pageSizeH;
            return this;
        }

        public STKSetDeviceBuilder setPageSizeLow(int pageSizeL)
        {
            setDevice.pageSizeLow = pageSizeL;
            return this;
        }

        public STKSetDeviceBuilder setEepromSizeHigh(int eepromSizeH)
        {
            setDevice.eepromSizeHigh = eepromSizeH;
            return this;
        }

        public STKSetDeviceBuilder setEepromSizeLow(int eepromSizeL)
        {
            setDevice.eepromSizeLow = eepromSizeL;
            return this;
        }

        public STKSetDeviceBuilder setFlashSize4(int flashSize4)
        {
            setDevice.flashSize4 = flashSize4;
            return this;
        }

        public STKSetDeviceBuilder setFlashSize3(int flashSize3)
        {
            setDevice.flashSize3 = flashSize3;
            return this;
        }

        public STKSetDeviceBuilder setFlashSize2(int flashSize2)
        {
            setDevice.flashSize2 = flashSize2;
            return this;
        }

        public STKSetDeviceBuilder setFlashSize1(int flashSize1)
        {
            setDevice.flashSize1 = flashSize1;
            return this;
        }

        public STKSetDevice build()
        {
            return setDevice;
        }
    }
}
