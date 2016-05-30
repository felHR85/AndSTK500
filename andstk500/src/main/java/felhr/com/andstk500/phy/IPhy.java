package felhr.com.andstk500.phy;

/**
 * Interface to implement classes which define different hardware interfaces like USB, Bluetooth or Wifi
 */
public interface IPhy
{
    boolean open();
    boolean write(byte[] data);
    boolean close();

    interface OnDataReceived
    {
        void onDataReceived();
    }
}
