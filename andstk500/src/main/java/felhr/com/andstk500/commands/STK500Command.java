package felhr.com.andstk500.commands;

public abstract class STK500Command
{
    protected int commandId;
    protected int length;

    private byte[] buffer;

    public STK500Command(int commandId, int length)
    {
        this.commandId = commandId;
        this.length = length;
    }

    public abstract byte[] getCommandBuffer();

    public int getLength()
    {
        return length;
    }
}
