package felhr.com.andstk500.commandstests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKSetDevice;

/**
 * STKSetDevice unit test
 */
public class STKSetDeviceTest extends TestCase
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

    private STKSetDevice candidate;

    @Before
    public void setUp()
    {
        Random r = new Random();

        deviceCode = r.nextInt() & 0xff;
        revision = r.nextInt() & 0xff;
        progType = r.nextInt() & 0xff;
        parMode = r.nextInt() & 0xff;
        polling = r.nextInt() & 0xff;
        selfTimed = r.nextInt() & 0xff;
        lockBytes = r.nextInt() & 0xff;
        fuseBytes = r.nextInt() & 0xff;
        flashPollVal1 = r.nextInt() & 0xff;
        flashPollVal2 = r.nextInt() & 0xff;
        eepromPollVal1 = r.nextInt() & 0xff;
        eepromPollVal2 = r.nextInt() & 0xff;
        pageSizeHigh = r.nextInt() & 0xff;
        pageSizeLow = r.nextInt() & 0xff;
        eepromSizeHigh = r.nextInt() & 0xff;
        eepromSizeLow = r.nextInt() & 0xff;
        flashSize4 = r.nextInt() & 0xff;
        flashSize3 = r.nextInt() & 0xff;
        flashSize2 = r.nextInt() & 0xff;
        flashSize1 = r.nextInt() & 0xff;

        candidate = new STKSetDevice.STKSetDeviceBuilder()
                .setDeviceCode(deviceCode)
                .setRevision(revision)
                .setProgType(progType)
                .setParMode(parMode)
                .setPolling(polling)
                .setSelfTimed(selfTimed)
                .setLockBytes(lockBytes)
                .setFuseBytes(fuseBytes)
                .setFlashPollVal1(flashPollVal1)
                .setFlashPollVal2(flashPollVal2)
                .setEepromPollVal1(eepromPollVal1)
                .setEepromPollVal2(eepromPollVal2)
                .setPageSizeHigh(pageSizeHigh)
                .setPageSizeLow(pageSizeLow)
                .setEepromSizeHigh(eepromSizeHigh)
                .setEepromSizeLow(eepromSizeLow)
                .setFlashSize4(flashSize4)
                .setFlashSize3(flashSize3)
                .setFlashSize2(flashSize2)
                .setFlashSize1(flashSize1)
                .build();
    }

    @Test
    public void testCommandBuffer()
    {
        byte[] buffer = candidate.getCommandBuffer();

        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE, buffer[0]);
        assertEquals((byte) deviceCode, buffer[1]);
        assertEquals((byte) revision, buffer[2]);
        assertEquals((byte) progType, buffer[3]);
        assertEquals((byte) parMode, buffer[4]);
        assertEquals((byte) polling, buffer[5]);
        assertEquals((byte) selfTimed, buffer[6]);
        assertEquals((byte) lockBytes, buffer[7]);
        assertEquals((byte) fuseBytes, buffer[8]);
        assertEquals((byte) flashPollVal1, buffer[9]);
        assertEquals((byte) flashPollVal2, buffer[10]);
        assertEquals((byte) eepromPollVal1, buffer[11]);
        assertEquals((byte) eepromPollVal2, buffer[12]);
        assertEquals((byte) pageSizeHigh, buffer[13]);
        assertEquals((byte) pageSizeLow, buffer[14]);
        assertEquals((byte) eepromSizeHigh, buffer[15]);
        assertEquals((byte) eepromSizeLow, buffer[16]);
        assertEquals((byte) flashSize4, buffer[17]);
        assertEquals((byte) flashSize3, buffer[18]);
        assertEquals((byte) flashSize2, buffer[19]);
        assertEquals((byte) flashSize1, buffer[20]);
        assertEquals(STK500Constants.Sync_CRC_EOP, buffer[21]);
    }

}
