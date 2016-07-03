package felhr.com.andstk500.stk500tests;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicBoolean;

import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKProgramPage;
import felhr.com.andstk500.phy.IPhy;
import felhr.com.andstk500.stk500.STKCommunicator;

/**
 * STKCommunicator test
 */
public class STKCommunicatorTest extends TestCase implements IPhy.OnChangesFromPhyLayer
{
    @Mock
    private UsbDevice mockedDevice;

    @Mock
    private UsbDeviceConnection mockedDeviceConnection;

    private STKCommunicator candidate;

    private boolean safeguard = true;

    @Before
    public void setUp()
    {
        mockedDevice = Mockito.mock(UsbDevice.class);
        mockedDeviceConnection = Mockito.mock(UsbDeviceConnection.class);
        candidate = new STKCommunicator(new FakePhyInterface(this));
    }

    @Test
    public void testOpenSTK500Channel()
    {
        boolean response = candidate.openSTK500Channel();
        assertEquals(true, response);
    }

    @Test
    public void testGetSignOn()
    {
        candidate.openSTK500Channel();
        candidate.getSignOn();
        hold();
    }

    @Test
    public void testGetSync()
    {
        candidate.openSTK500Channel();
        candidate.getSync();
        hold();
    }

    @Test
    public void testGetParameterValue()
    {
        candidate.openSTK500Channel();
        candidate.getParameterValue(0);
        hold();
    }

    @Test
    public void testSetParameterValue()
    {
        candidate.openSTK500Channel();
        candidate.setParameterValue(0, 0);
        hold();
    }

    @Test
    public void testSetDevice()
    {
        candidate.openSTK500Channel();
        candidate.setDevice();
        hold();
    }

    @Test
    public void testSetDeviceExt()
    {
        candidate.openSTK500Channel();
        candidate.setDeviceExt(0, 0, 0, 0, 0);
        hold();
    }

    @Test
    public void testEnterProgramMode()
    {
        candidate.openSTK500Channel();
        candidate.enterProgamMode();
        hold();
    }

    @Test
    public void testLeaveProgramMode()
    {
        candidate.openSTK500Channel();
        candidate.leaveProgramMode();
        hold();
    }

    @Test
    public void testChipErase()
    {
        candidate.openSTK500Channel();
        candidate.chipErase();
        hold();
    }

    @Test
    public void testCheckAutoInc()
    {
        candidate.openSTK500Channel();
        candidate.checkAutoInc();
        hold();
    }

    @Test
    public void testLoadAddress()
    {
        candidate.openSTK500Channel();
        candidate.loadAddress(0x00, 0x00);
        hold();
    }

    @Test
    public void testProgramFlash()
    {
        candidate.openSTK500Channel();
        candidate.programFlash(0x00, 0x00);
        hold();
    }

    @Test
    public void testProgramData()
    {
        candidate.openSTK500Channel();
        candidate.programData(0x00);
        hold();
    }

    @Test
    public void testProgramFuse()
    {
        candidate.openSTK500Channel();
        candidate.programFuse(0x00, 0x00);
        hold();
    }

    @Test
    public void testProgramFuseExt()
    {
        candidate.openSTK500Channel();
        candidate.programFuseExt(0x00, 0x00, 0x00);
        hold();
    }

    @Test
    public void testProgramLock()
    {
        candidate.openSTK500Channel();
        candidate.programLock();
        hold();
    }

    @Test
    public void testProgramPage()
    {
        candidate.openSTK500Channel();
        candidate.programPage(STKProgramPage.EEPROM, new byte[]{});
        hold();
    }

    @Test
    public void testReadFlash()
    {
        candidate.openSTK500Channel();
        candidate.readFlash();
        hold();
    }

    @Test
    public void testReadData()
    {
        candidate.openSTK500Channel();
        candidate.readData();
        hold();
    }

    @Test
    public void testReadFuse()
    {
        candidate.openSTK500Channel();
        candidate.readFuse();
        hold();
    }

    @Test
    public void testReadFuseExt()
    {
        candidate.openSTK500Channel();
        candidate.readFuseExt();
        hold();
    }

    @Test
    public void testReadLock()
    {
        candidate.openSTK500Channel();
        candidate.readLock();
        hold();
    }

    @Test
    public void testReadPage()
    {
        candidate.openSTK500Channel();
        candidate.readPage(0x00, 0x00, "E");
        hold();
    }

    @Test
    public void testReadSignature()
    {
        candidate.openSTK500Channel();
        candidate.readSignature();
        hold();
    }

    @Test
    public void testReadOscillator()
    {
        candidate.openSTK500Channel();
        candidate.readOscillator();
        hold();
    }

    @Test
    public void testReadOscillatorExt()
    {
        candidate.openSTK500Channel();
        candidate.readOscillatorExt(0x00);
        hold();
    }

    @Override
    public void onChannelOpened()
    {

    }

    @Override
    public void onReceivedData(byte[] data)
    {
        assertEquals((byte) STK500Constants.Resp_STK_INSYNC, data[0]);
        assertEquals((byte) STK500Constants.Resp_STK_OK, data[1]);
        candidate.closeSTK500Channel();
        release();
    }

    @Override
    public void onChannelClosed()
    {

    }

    private synchronized void hold()
    {
        while(safeguard)
        {
            try
            {
                wait();
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private synchronized void release()
    {
        safeguard = false;
        notify();
    }

    /**
     *  Fake Physical interface to mock a physical communication
     */
    private class FakePhyInterface implements IPhy
    {
        IPhy.OnChangesFromPhyLayer changesFromPhyLayer;
        OnReceiveThread onReceiveThread;

        AtomicBoolean keep;

        public FakePhyInterface(IPhy.OnChangesFromPhyLayer changesFromPhyLayer)
        {
            this.changesFromPhyLayer = changesFromPhyLayer;
            onReceiveThread = new OnReceiveThread();
            keep = new AtomicBoolean(true);
            onReceiveThread.start();
            while(!onReceiveThread.isAlive()){}
        }

        @Override
        public boolean open()
        {
            return true;
        }

        @Override
        public void write(byte[] data)
        {
            safeguard = true;
            onReceiveThread.sendResponse();
        }

        @Override
        public void close()
        {
            keep.set(false);
            onReceiveThread.stopThread();
        }

        @Override
        public void setCallback(OnChangesFromPhyLayer changesFromPhyLayer)
        {
            this.changesFromPhyLayer = changesFromPhyLayer;
        }

        private class OnReceiveThread extends Thread
        {
            @Override
            public void run()
            {
                while(keep.get())
                {
                    synchronized(this)
                    {
                        try
                        {
                            wait();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    byte[] response = new byte[]{STK500Constants.Resp_STK_INSYNC, STK500Constants.Resp_STK_OK};
                    changesFromPhyLayer.onReceivedData(response);
                }
            }

            public synchronized void sendResponse()
            {
                notify();
            }

            public synchronized void stopThread()
            {
                notify();
            }
        }
    }
}

