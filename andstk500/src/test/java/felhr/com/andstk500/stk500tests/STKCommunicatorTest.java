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
import felhr.com.andstk500.phy.IPhy;
import felhr.com.andstk500.responses.STK500ResponseGenerator;
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

    @Override
    public void onChannelOpened()
    {

    }

    @Override
    public void onReceivedData(byte[] data)
    {

    }

    @Override
    public void onChannelClosed()
    {

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

