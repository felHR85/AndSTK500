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
    private static final int BAUD_RATE = 9600;

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
        if(canBeArduino())
        {
            programmingPort = UsbSerialDevice.createUsbSerialDevice(device, connection);
            if(programmingPort != null)
            {
                if(programmingPort.open())
                {
                    programmingPort.setBaudRate(BAUD_RATE);
                    programmingPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                    programmingPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                    programmingPort.setParity(UsbSerialInterface.PARITY_NONE);
                    programmingPort.read(this);
                    return true;
                }
            }
        }else
        {
            return false;
        }
        return false;
    }

    @Override
    public void write(byte[] data)
    {
        programmingPort.write(data);
    }

    @Override
    public void close()
    {
        programmingPort.close();
    }

    @Override
    public void onReceivedData(byte[] bytes)
    {
        callback.onDataReceived(bytes);
    }

    private boolean canBeArduino()
    {
        int deviceVID = device.getVendorId();
        int devicePID = device.getProductId();
        if(deviceVID != 0x1d6b && (devicePID != 0x0001 || devicePID != 0x0002 || devicePID != 0x0003))
            return true;
        else
           return false;
    }
}
