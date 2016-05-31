package felhr.com.andstk500.stk500;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import java.util.concurrent.atomic.AtomicBoolean;

import felhr.com.andstk500.commands.*;
import felhr.com.andstk500.phy.IPhy;
import felhr.com.andstk500.phy.UsbCommunicator;
import felhr.com.andstk500.responses.STK500Response;
import felhr.com.andstk500.responses.STK500ResponseGenerator;
import felhr.com.andstk500.responses.STKInsync;

/**
 * STK500v1 api
 */
public class STKCommunicator implements IPhy.OnChangesFromPhyLayer
{
    private IPhy phyComm;
    private AtomicBoolean allowNewCommand;
    private STK500Command currentCommand;
    private STK500ResponseGenerator responseGenerator;

    // Usb constructor
    public STKCommunicator(UsbDevice device, UsbDeviceConnection connection)
    {
        phyComm = new UsbCommunicator(device, connection);
        allowNewCommand = new AtomicBoolean(false);
        responseGenerator = new STK500ResponseGenerator();
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
            currentCommand = new STKGetSignOn();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void getSync()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKGetSync();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void getParameterValue(int parameter)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKGetParameter(parameter);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void setParameterValue(int parameter, int value)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKSetParameter(parameter, value);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void setDevice()
    {
        if(allowNewCommand.get())
        {
            //TODO!! A lot of parameters. See how many of them are really important
            allowNewCommand.set(false);
            currentCommand = new STKSetDevice.STKSetDeviceBuilder().build();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void setDeviceExt(int commandSize, int eepromPageSize, int signalPagel,
                             int signalbs2, int resetDisable)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKSetDeviceExt.STKSetDeviceExtBuilder()
                    .setCommandSize(commandSize)
                    .setEepromPageSize(eepromPageSize)
                    .setSignalPagel(signalPagel)
                    .setSignalBs2(signalbs2)
                    .setResetDisable(resetDisable)
                    .build();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void enterProgamMode()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKEnterProgMode();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void leaveProgramMode()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKLeaveProgramMode();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void chipErase()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKChipErase();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void checkAutoInc()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKCheckAutoInc();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void loadAddress(int addrLow, int addrHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKLoadAddress(addrLow, addrHigh);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programFlash(int flashLow, int flashHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramFlash(flashLow, flashHigh);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programData(int data)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramData(data);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programFuse(int fuseLow, int fuseHigh)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramFuse(fuseLow, fuseHigh);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programFuseExt(int fuseLow, int fuseHigh, int fuseExt)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramFuseExt(fuseLow, fuseHigh, fuseExt);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programLock()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramLock();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void programPage(String memType, byte[] data)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKProgramPage(memType, data);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readFlash()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadFlash();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readData()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadData();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readFuse()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadFuse();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readFuseExt()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadFuseExt();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readLock()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadLock();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readPage(int bytesHigh, int bytesLow, String memType)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadPage(bytesHigh, bytesLow, memType);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readSignature()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadSignature();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readOscillator()
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadOsccal();
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    public void readOscillatorExt(int calByte)
    {
        if(allowNewCommand.get())
        {
            allowNewCommand.set(false);
            currentCommand = new STKReadOsccalExt(calByte);
            phyComm.write(currentCommand.getCommandBuffer());
        }
    }

    @Override
    public void onChannelOpened()
    {
        allowNewCommand.set(true);
    }

    @Override
    public void onDataReceived(byte[] dataReceived)
    {
        boolean ret = responseGenerator.generateSTK500Response(currentCommand, dataReceived);
        if(ret)
        {
            STKInsync response = (STKInsync) responseGenerator.getCurrentResponse();
            //TODO: Send OK/NOK up through a callback still not defined
        }
    }

    @Override
    public void onChannelClosed()
    {
        allowNewCommand.set(false);
    }
}
