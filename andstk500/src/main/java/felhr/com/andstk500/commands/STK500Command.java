package felhr.com.andstk500.commands;

public abstract class STK500Command
{
    protected int commandId;

    private byte[] buffer;

    public STK500Command(int commandId)
    {
        this.commandId = commandId;
    }

    public abstract byte[] getCommandBuffer();

}
