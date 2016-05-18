package felhr.com.andstk500.responses;

/**
 * STKInsync response
 */
public class STKInsync extends STK500Response
{
    private int commandId;
    private int[] parameters;
    private byte[] data;
    private boolean ok;

    public STKInsync(int commandId, int[] parameters, byte[] data, boolean ok)
    {
        this.commandId = commandId;
        this.parameters = parameters;
        this.data = data;
        this.ok = ok;
    }

    public int getCommandId()
    {
        return commandId;
    }

    public void setCommandId(int commandId)
    {
        this.commandId = commandId;
    }

    public int[] getParameters()
    {
        return parameters;
    }

    public void setParameters(int[] parameters)
    {
        this.parameters = parameters;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean ok)
    {
        this.ok = ok;
    }
}
