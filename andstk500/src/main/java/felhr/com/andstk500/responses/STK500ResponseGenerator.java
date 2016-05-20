package felhr.com.andstk500.responses;

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

        if(commandType == STK500Constants.Cmnd_STK_GET_SIGN_ON)
        {
            return generateResponse(STK500Constants.Cmnd_STK_GET_SIGN_ON, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_GET_SYNC)
        {
            return generateResponse(STK500Constants.Cmnd_STK_GET_SYNC, 2 , 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_GET_PARAMETER)
        {
            return generateResponse(STK500Constants.Cmnd_STK_GET_PARAMETER, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_SET_PARAMETER)
        {
           //TODO Variable parameters
        }
        return false;
    }

    public STK500Response getCurrentResponse()
    {
        STK500Response response = currentResponse;
        currentResponse = null;
        return response;
    }

    private boolean generateResponse(int commandId, int maxLength, int numberArguments, byte[] buffer)
    {
        System.arraycopy(buffer, 0, responseBuffer, pointer, buffer.length);
        pointer += buffer.length;

        if(pointer == maxLength)
        {
            int[] args = new int[numberArguments];
            for(int i=1;i<=numberArguments;i++)
            {
                args[i-1] = responseBuffer[i];
            }
            currentResponse = new STKInsync(commandId, args, null, responseBuffer[pointer-1] == STK500Constants.Resp_STK_OK);
            pointer = 0;
            return true;
        }else
        {
            //TODO!!!
        }

        return false;
    }
}
