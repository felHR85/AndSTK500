package felhr.com.andstk500.responses;

import java.util.Arrays;

import felhr.com.andstk500.commands.STK500Constants;

/**
 * Class for generate STK500 responses
 */
public class STK500ResponseGenerator
{
    private static final int MAX_BUFFER = 256 + 5; // Max command length

    private byte[] responseBuffer;
    private int pointer;

    private boolean newCommand;

    private STK500Response currentResponse;


    public STK500ResponseGenerator()
    {
        this.responseBuffer = new byte[MAX_BUFFER];
        this.pointer = 0;
        this.newCommand = true;
    }

    public boolean generateSTK500Response(int commandType, byte[] buffer)
    {
        //TODO: Generate correct STK500 responses classes for each command.
        //TODO: Return true if the response is correct. False if some bytes are missing
        if(newCommand)
        {
            if(commandType == STK500Constants.Cmnd_STK_GET_SIGN_ON)
            {
                return generateSTKGetSignOnResponse(buffer);
            }
        }else
        {

        }
        return false;
    }

    public STK500Response getCurrentResponse()
    {
        STK500Response response = currentResponse;
        currentResponse = null;
        return response;
    }

    private boolean generateSTKGetSignOnResponse(byte[] buffer)
    {
        int maxResponseLength = 3;
        if(buffer.length == maxResponseLength)
        {
            currentResponse = new STKInsync(
                    STK500Constants.Cmnd_STK_GET_SIGN_ON, new int[]{buffer[1]}, null, true);
            return true;
        }else
        {
            System.arraycopy(buffer, 0, responseBuffer, pointer, buffer.length);
            pointer = buffer.length;
            return false;
        }
    }


}
