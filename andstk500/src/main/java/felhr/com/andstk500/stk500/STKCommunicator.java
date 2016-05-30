package felhr.com.andstk500.stk500;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import felhr.com.andstk500.phy.IPhy;

/**
 * STK500v1 api
 */
public class STKCommunicator implements IPhy.OnChangesFromPhyLayer
{
    private IPhy phyComm;

    public STKCommunicator(UsbDevice device, UsbDeviceConnection connection)
    {

    }

    @Override
    public void onChannelOpened()
    {

    }

    @Override
    public void onDataReceived(byte[] dataReceived)
    {

    }

    @Override
    public void onChannelClosed()
    {

    }
}
