package felhr.com.andstk500.responses;

import felhr.com.andstk500.commands.STK500Command;
import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKReadPage;

/**
 * Class for generate STK500 responses
 */
public class STK500ResponseGenerator
{
    private static final int MAX_BUFFER = 256 + 5; // Max command length

    private byte[] responseBuffer;
    private int pointer;

    private STK500Response currentResponse;


    public STK500ResponseGenerator()
    {
        this.responseBuffer = new byte[MAX_BUFFER];
        this.pointer = 0;
    }

    public boolean generateSTK500Response(STK500Command command, byte[] buffer)
    {
        int commandType = command.getCommandId();

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
            return generateResponse(STK500Constants.Cmnd_STK_SET_PARAMETER, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_SET_DEVICE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_SET_DEVICE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_SET_DEVICE_EXT)
        {
            return generateResponse(STK500Constants.Cmnd_STK_SET_DEVICE_EXT, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_ENTER_PROGMODE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_ENTER_PROGMODE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_LEAVE_PROGMODE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_LEAVE_PROGMODE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_CHIP_ERASE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_CHIP_ERASE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_CHECK_AUTOINC)
        {
            return generateResponse(STK500Constants.Cmnd_STK_CHECK_AUTOINC, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_FLASH)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_FLASH, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_DATA)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_DATA, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_FUSE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_FUSE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_FUSE_EXT)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_FUSE_EXT, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_LOCK)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_LOCK, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_PROG_PAGE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_PROG_PAGE, 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_FLASH)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_FLASH, 4, 2, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_DATA)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_DATA, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_FUSE)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_FUSE, 4, 2, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_FUSE_EXT)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_FUSE_EXT, 5, 3, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_LOCK)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_LOCK, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_PAGE)
        {
            STKReadPage readPage = (STKReadPage) command;
            int length = readPage.getDataLength();
            return generateResponse(STK500Constants.Cmnd_STK_READ_PAGE, length + 2, 0, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_SIGN)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_SIGN, 5, 3, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_OSCCAL)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_OSCCAL, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_READ_OSCCAL_EXT)
        {
            return generateResponse(STK500Constants.Cmnd_STK_READ_OSCCAL_EXT, 3, 1, buffer);
        }else if(commandType == STK500Constants.Cmnd_STK_LOAD_ADDRESS)
        {
            return generateResponse(STK500Constants.Cmnd_STK_LOAD_ADDRESS, 2, 0, buffer);
        }

        return false;
    }

    public STK500Response getCurrentResponse()
    {
        STK500Response response = currentResponse;
        currentResponse = null;
        return response;
    }

    /*
        Method that generates a response stored in currentResponse variable.
        Sometimes some USB to Serial converters attached to an Android phone split received data in more than one call.
        this must be kept in mind because that's because this method doesn't retrieve directly a STK500Response method.

        Received data is buffered and if its length matches expected length it will create a STK500Response object and
        will return true.
        If doesn't match will return false except if we are dealing with a SET_PARAMETER (length is 2 or 3), in which case
        if last byte is OK it will create a new STK500Response object
     */
    private boolean generateResponse(int commandId, int maxLength, int numberArguments, byte[] buffer)
    {
        System.arraycopy(buffer, 0, responseBuffer, pointer, buffer.length);
        pointer += buffer.length;

        if(pointer == maxLength)
        {
            int[] args = new int[numberArguments];
            byte[] newBuffer = new byte[0];

            if(commandId != STK500Constants.Cmnd_STK_READ_PAGE)
            {
                for(int i=1;i<=numberArguments;i++)
                {
                    args[i-1] = responseBuffer[i];
                }
            }else
            {
                newBuffer = new byte[maxLength - 2];
                System.arraycopy(responseBuffer, 1, newBuffer, 0, maxLength - 2);
            }
            currentResponse = new STKInsync(commandId, args, newBuffer, responseBuffer[pointer-1] == STK500Constants.Resp_STK_OK);
            pointer = 0;
            return true;
        }else
        {
            if(commandId == STK500Constants.Cmnd_STK_SET_PARAMETER)
            {
                if(responseBuffer[pointer - 1] == STK500Constants.Resp_STK_OK)
                {
                    int[] args = new int[0];
                    byte[] newBuffer = new byte[0];
                    currentResponse = new STKInsync(commandId, args, newBuffer, true);
                    pointer = 0;
                    return true;
                }
            }
        }
        return false;
    }
}
