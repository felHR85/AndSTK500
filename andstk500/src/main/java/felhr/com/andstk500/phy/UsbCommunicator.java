package felhr.com.andstk500.phy;

import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

/**
 * Usb frontend for UsbSerial lib
 */
public class UsbCommunicator implements IPhy, UsbSerialInterface.UsbReadCallback
{
    private static final int BAUD_RATE = 9600;

    private IPhy.OnChangesFromPhyLayer callback;

    private UsbSerialDevice programmingPort;

    public UsbCommunicator(UsbSerialDevice serialPort)
    {
        this.programmingPort = serialPort;
    }

    @Override
    public void setCallback(IPhy.OnChangesFromPhyLayer callback)
    {
        this.callback = callback;
    }

    @Override
    public boolean open()
    {
        if(programmingPort != null)
        {
            if(programmingPort.open())
            {
                programmingPort.setBaudRate(BAUD_RATE);
                programmingPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                programmingPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                programmingPort.setParity(UsbSerialInterface.PARITY_NONE);
                programmingPort.read(this);
                callback.onChannelOpened();
                return true;
            }
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
        callback.onReceivedData(bytes);
    }

}
