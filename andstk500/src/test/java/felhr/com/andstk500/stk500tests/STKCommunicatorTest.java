package felhr.com.andstk500.stk500tests;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

import junit.framework.TestCase;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

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

    @Before
    public void setUp()
    {
        mockedDevice = Mockito.mock(UsbDevice.class);
        mockedDeviceConnection = Mockito.mock(UsbDeviceConnection.class);

        candidate = new STKCommunicator(new FakePhyInterface(this));

    }

    @Override
    public void onChannelOpened()
    {

    }

    @Override
    public void onDataReceived(byte[] data)
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

        public FakePhyInterface(IPhy.OnChangesFromPhyLayer changesFromPhyLayer)
        {
            this.changesFromPhyLayer = changesFromPhyLayer;
        }

        @Override
        public boolean open()
        {
            return true;
        }

        @Override
        public void write(byte[] data)
        {
            //TODO: 1) check STK command
            //TODO: 2) Notify OnReceive thread
        }

        @Override
        public void close()
        {

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
                //TODO: call IPhy.OnChangesFromPhyLayer
            }
        }
    }
}

