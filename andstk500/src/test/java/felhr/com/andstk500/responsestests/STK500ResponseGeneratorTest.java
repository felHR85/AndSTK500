package felhr.com.andstk500.responsestests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import felhr.com.andstk500.commands.STK500Command;
import felhr.com.andstk500.commands.STK500Constants;
import felhr.com.andstk500.commands.STKCheckAutoInc;
import felhr.com.andstk500.commands.STKChipErase;
import felhr.com.andstk500.commands.STKEnterProgMode;
import felhr.com.andstk500.commands.STKGetParameter;
import felhr.com.andstk500.commands.STKGetSignOn;
import felhr.com.andstk500.commands.STKGetSync;
import felhr.com.andstk500.commands.STKLeaveProgramMode;
import felhr.com.andstk500.commands.STKLoadAddress;
import felhr.com.andstk500.commands.STKProgramData;
import felhr.com.andstk500.commands.STKProgramFlash;
import felhr.com.andstk500.commands.STKProgramFuse;
import felhr.com.andstk500.commands.STKProgramFuseExt;
import felhr.com.andstk500.commands.STKProgramLock;
import felhr.com.andstk500.commands.STKProgramPage;
import felhr.com.andstk500.commands.STKReadData;
import felhr.com.andstk500.commands.STKReadFlash;
import felhr.com.andstk500.commands.STKReadFuse;
import felhr.com.andstk500.commands.STKReadFuseExt;
import felhr.com.andstk500.commands.STKReadLock;
import felhr.com.andstk500.commands.STKReadOsccal;
import felhr.com.andstk500.commands.STKReadOsccalExt;
import felhr.com.andstk500.commands.STKReadPage;
import felhr.com.andstk500.commands.STKReadSignature;
import felhr.com.andstk500.commands.STKSetDevice;
import felhr.com.andstk500.commands.STKSetDeviceExt;
import felhr.com.andstk500.commands.STKSetParameter;
import felhr.com.andstk500.responses.STK500ResponseGenerator;
import felhr.com.andstk500.responses.STKInsync;

/**
 * STK500 response generator test
 */
public class STK500ResponseGeneratorTest extends TestCase
{
    private STK500ResponseGenerator candidate;

    //Mocked STK commands
    @Mock
    private STKReadPage readPage;


    @Before
    public void setUp()
    {
        this.candidate = new STK500ResponseGenerator();
    }

    @Test
    public void testGetSignOn()
    {
        // Mock getCommandId method
        STKGetSignOn stk500Command = Mockito.mock(STKGetSignOn.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_SIGN_ON);

        // Create received buffer
        byte[] buffer = new byte[3];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) STK500Constants.Sync_CRC_EOP;

        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SIGN_ON, response.getCommandId());
        assertEquals(0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testGetSignOnSplit()
    {
        // Mock getCommandId method
        STKGetSignOn stk500Command = Mockito.mock(STKGetSignOn.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_SIGN_ON);

        // Create received buffer1
        byte[] buffer1 = new byte[2];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer1[1] = (byte) 0x80;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Sync_CRC_EOP;

        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SIGN_ON, response.getCommandId());
        assertEquals(0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());

    }
}
