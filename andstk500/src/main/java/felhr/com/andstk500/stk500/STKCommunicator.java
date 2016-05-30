package felhr.com.andstk500.stk500;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import java.util.concurrent.atomic.AtomicBoolean;

import felhr.com.andstk500.commands.*;
import felhr.com.andstk500.phy.IPhy;
import felhr.com.andstk500.phy.UsbCommunicator;

/**
 * STK500v1 api
 */
public class STKCommunicator implements IPhy.OnChangesFromPhyLayer
{
    private IPhy phyComm;
    private AtomicBoolean allowNewCommand;

    // Usb constructor
    public STKCommunicator(UsbDevice device, UsbDeviceConnection connection)
    {
        phyComm = new UsbCommunicator(device, connection);
        allowNewCommand = new AtomicBoolean(true);
    }

    /**
     * Public api
     */
    public void openSTK500Channel()
    {
        phyComm.open();
    }

    public void closeSTK500Channel()
    {
        phyComm.close();
    }

    public void getSignOn()
    {
        if(allowNewCommand.get())
        {
            phyComm.write(new STKGetSignOn().getCommandBuffer());
            allowNewCommand.set(false);
        }
    }

    public void getSync()
    {
        if(allowNewCommand.get())
        {
            phyComm.write(new STKGetSync().getCommandBuffer());
            allowNewCommand.set(false);
        }
    }

    public void getParameterValue(int parameter)
    {
        if(allowNewCommand.get())
        {
            phyComm.write(new STKGetParameter(parameter).getCommandBuffer());
            allowNewCommand.set(false);
        }
    }

    public void setParameterValue(int parameter, int value)
    {
        if(allowNewCommand.get())
        {
            phyComm.write(new STKSetParameter(parameter, value).getCommandBuffer());
            allowNewCommand.set(false);
        }
    }

    public void setDevice()
    {
        if(allowNewCommand.get())
        {
            //TODO!! A lot of parameters. See how many of them are really important
            phyComm.write(new STKSetDevice.STKSetDeviceBuilder().build().getCommandBuffer());
            allowNewCommand.set(false);
        }
    }

    public void setDeviceExt(int commandSize, int eepromPageSize, int signalPagel,
                             int signalbs2, int resetDisable)
    {
        if(allowNewCommand.get())
        {
            phyComm.write(new STKSetDeviceExt.STKSetDeviceExtBuilder()
                .setCommandSize(commandSize)
                .setEepromPageSize(eepromPageSize)
                .setSignalPagel(signalPagel)
                .setSignalBs2(signalbs2)
                .setResetDisable(resetDisable)
                .build().getCommandBuffer());
            allowNewCommand.set(false);
        }
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
