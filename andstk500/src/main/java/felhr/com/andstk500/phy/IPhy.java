package felhr.com.andstk500.phy;

/**
 * Interface to implement classes which define different hardware interfaces like USB, Bluetooth or Wifi
 */
public interface IPhy
{
    boolean open();
    void write(byte[] data);
    void close();
    void setCallback(OnChangesFromPhyLayer onChangesFromPhyLayer);

    interface OnChangesFromPhyLayer
    {
        void onChannelOpened();
        void onReceivedData(byte[] data);
        void onChannelClosed();
    }
}
