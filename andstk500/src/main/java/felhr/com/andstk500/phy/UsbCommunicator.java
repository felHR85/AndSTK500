package felhr.com.andstk500.phy;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

/**
 * Usb frontend for UsbSerial lib
 */
public class UsbCommunicator implements IPhy
{
    private UsbDevice device;
    private UsbDeviceConnection connection;

    public UsbCommunicator(UsbDevice device, UsbDeviceConnection connection)
    {
        this.device = device;
        this.connection = connection;
    }

    @Override
    public boolean open()
    {
        return false;
    }

    @Override
    public boolean write(byte[] data)
    {
        return false;
    }

    @Override
    public boolean close()
    {
        return false;
    }
}
