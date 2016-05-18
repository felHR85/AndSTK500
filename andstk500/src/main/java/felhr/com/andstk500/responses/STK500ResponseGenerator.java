package felhr.com.andstk500.responses;

/**
 * Class for generate STK500 responses
 */
public class STK500ResponseGenerator
{
    private STK500Response currentResponse;

    public STK500ResponseGenerator()
    {

    }

    public boolean generateSTK500Response(int commandType, byte[] buffer)
    {
        //TODO: Generate correct STK500 responses classes for each command.
        //TODO: Return true if the response is correct. False if some bytes are missing
        return false;
    }

    public STK500Response getCurrentResponse()
    {
        STK500Response response = currentResponse;
        currentResponse = null;
        return response;
    }
}
