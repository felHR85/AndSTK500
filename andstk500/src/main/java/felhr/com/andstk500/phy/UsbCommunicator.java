package felhr.com.andstk500.phy;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

/**
 * Usb frontend for UsbSerial lib
 */
public class UsbCommunicator implements IPhy, UsbSerialInterface.UsbReadCallback
{
    private IPhy.OnChangesFromPhyLayer callback;

    private UsbDevice device;
    private UsbDeviceConnection connection;

    private UsbSerialDevice programmingPort;

    public UsbCommunicator(IPhy.OnChangesFromPhyLayer callback ,UsbDevice device, UsbDeviceConnection connection)
    {
        this.callback = callback;
        this.device = device;
        this.connection = connection;
    }

    @Override
    public boolean open()
    {
        return false;
    }

    @Override
    public void write(byte[] data)
    {

    }

    @Override
    public void close()
    {

    }

    @Override
    public void onReceivedData(byte[] bytes)
    {

    }
}
