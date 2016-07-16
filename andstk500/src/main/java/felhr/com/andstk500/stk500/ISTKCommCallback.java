package felhr.com.andstk500.stk500;

public interface ISTKCommCallback
{
    void onSTKResponse(int commandId, boolean status, int[] parameters, byte[] data);
}
