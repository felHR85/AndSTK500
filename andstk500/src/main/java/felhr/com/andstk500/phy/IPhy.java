package felhr.com.andstk500.phy;

/**
 * Interface to implement classes which define different hardware interfaces like USB, Bluetooth or Wifi
 */
public interface IPhy
{
    void open();
    void write(byte[] data);
    void close();

    interface OnChangesFromPhyLayer
    {
        void onChannelOpened();
        void onDataReceived(byte[] data);
        void onChannelClosed();
    }
}
