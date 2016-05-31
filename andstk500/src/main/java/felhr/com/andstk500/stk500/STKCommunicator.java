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
            allowNewCommand.set(false);
            phyComm.write(new STKGetSignOn().getCommandBuffer());
        }
    }

    public void getSync()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKGetSync().getCommandBuffer());
        }
    }

    public void getParameterValue(int parameter)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKGetParameter(parameter).getCommandBuffer());
        }
    }

    public void setParameterValue(int parameter, int value)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKSetParameter(parameter, value).getCommandBuffer());
        }
    }

    public void setDevice()
    {
        if(allowNewCommand.get())
        {
            //TODO!! A lot of parameters. See how many of them are really important
            allowNewCommand.set(false);
            phyComm.write(new STKSetDevice.STKSetDeviceBuilder().build().getCommandBuffer());
        }
    }

    public void setDeviceExt(int commandSize, int eepromPageSize, int signalPagel,
                             int signalbs2, int resetDisable)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKSetDeviceExt.STKSetDeviceExtBuilder()
                .setCommandSize(commandSize)
                .setEepromPageSize(eepromPageSize)
                .setSignalPagel(signalPagel)
                .setSignalBs2(signalbs2)
                .setResetDisable(resetDisable)
                .build().getCommandBuffer());
        }
    }

    public void enterProgamMode()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKEnterProgMode().getCommandBuffer());
        }
    }

    public void leaveProgramMode()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKLeaveProgramMode().getCommandBuffer());
        }
    }

    public void chipErase()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKChipErase().getCommandBuffer());
        }
    }

    public void checkAutoInc()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKCheckAutoInc().getCommandBuffer());
        }
    }

    public void loadAddress(int addrLow, int addrHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKLoadAddress(addrLow, addrHigh).getCommandBuffer());
        }
    }

    public void programFlash(int flashLow, int flashHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramFlash(flashLow, flashHigh).getCommandBuffer());
        }
    }

    public void programData(int data)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramData(data).getCommandBuffer());
        }
    }

    public void programFuse(int fuseLow, int fuseHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramFuse(fuseLow, fuseHigh).getCommandBuffer());
        }
    }

    public void programFuseExt(int fuseLow, int fuseHigh, int fuseExt)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramFuseExt(fuseLow, fuseHigh, fuseExt).getCommandBuffer());
        }
    }

    public void programLock()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramLock().getCommandBuffer());
        }
    }

    public void programPage(String memType, byte[] data)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKProgramPage(memType, data).getCommandBuffer());
        }
    }

    public void readFlash()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadFlash().getCommandBuffer());
        }
    }

    public void readData()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadData().getCommandBuffer());
        }
    }

    public void readFuse()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadFuse().getCommandBuffer());
        }
    }

    public void readFuseExt()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadFuseExt().getCommandBuffer());
        }
    }

    public void readLock()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadLock().getCommandBuffer());
        }
    }

    public void readPage(int bytesHigh, int bytesLow, String memType)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadPage(bytesHigh, bytesLow, memType).getCommandBuffer());
        }
    }

    public void readSignature()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadSignature().getCommandBuffer());
        }
    }

    public void readOscillator()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadOsccal().getCommandBuffer());
        }
    }

    public void readOscillatorExt(int calByte)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            phyComm.write(new STKReadOsccalExt(calByte).getCommandBuffer());
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
