package felhr.com.andstk500.responsestests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Random;

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
        buffer[2] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SIGN_ON, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
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
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SIGN_ON, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());

    }

    @Test
    public void testGetSync()
    {
        // Mock getCommandId method
        STKGetSync stk500Command = Mockito.mock(STKGetSync.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_SYNC);

        // Create received buffer
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SYNC, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testGetSyncSplit()
    {
        // Mock getCommandId method
        STKGetSync stk500Command = Mockito.mock(STKGetSync.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_SYNC);

        // Create received buffer
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_SYNC, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testGetParameter()
    {
        // Mock getCommandId method
        STKGetParameter stk500Command = Mockito.mock(STKGetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_PARAMETER);

        // Create received buffer
        byte[] buffer = new byte[3];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_PARAMETER, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testGetParameterSplit()
    {
        // Mock getCommandId method
        STKGetParameter stk500Command = Mockito.mock(STKGetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_GET_PARAMETER);

        // Create received buffer
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[2];
        buffer2[0] = (byte) 0x80;
        buffer2[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_GET_PARAMETER, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetParameter1()
    {
        // Mock getCommandId method
        STKSetParameter stk500Command = Mockito.mock(STKSetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_PARAMETER);

        // Create received buffer
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_PARAMETER, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetParameter2()
    {
        // Mock getCommandId method
        STKSetParameter stk500Command = Mockito.mock(STKSetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_PARAMETER);

        // Create received buffer
        byte[] buffer = new byte[3];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) STK500Constants.Resp_STK_FAILED;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_PARAMETER, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(false, response.isOk());
    }

    @Test
    public void testSetParameter1Split()
    {
        // Mock getCommandId method
        STKSetParameter stk500Command = Mockito.mock(STKSetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_PARAMETER);

        // Create received buffer
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_PARAMETER, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetParameter2Split()
    {
        // Mock getCommandId method
        STKSetParameter stk500Command = Mockito.mock(STKSetParameter.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_PARAMETER);

        // Create received buffer
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[2];
        buffer2[0] = (byte) 0x80;
        buffer2[1] = (byte) STK500Constants.Resp_STK_FAILED;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_PARAMETER, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(false, response.isOk());
    }

    @Test
    public void testSetDevice()
    {
        // Mock getCommandId method
        STKSetDevice stk500Command = Mockito.mock(STKSetDevice.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_DEVICE);

        // Create received buffer
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetDeviceSplit()
    {
        // Mock getCommandId method
        STKSetDevice stk500Command = Mockito.mock(STKSetDevice.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_DEVICE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetExtendedDevice()
    {
        // Mock getCommandId method
        STKSetDeviceExt stk500Command = Mockito.mock(STKSetDeviceExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_DEVICE_EXT);

        // Create received buffer
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE_EXT, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSetExtendedDeviceSplit()
    {
        // Mock getCommandId method
        STKSetDeviceExt stk500Command = Mockito.mock(STKSetDeviceExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_SET_DEVICE_EXT);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_SET_DEVICE_EXT, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testEnterProgModeOK()
    {
        // Mock getCommandId method
        STKEnterProgMode stk500Command = Mockito.mock(STKEnterProgMode.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_ENTER_PROGMODE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_ENTER_PROGMODE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testEnterProgModeNoDevice()
    {
        // Mock getCommandId method
        STKEnterProgMode stk500Command = Mockito.mock(STKEnterProgMode.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_ENTER_PROGMODE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_NODEVICE;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_ENTER_PROGMODE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(false, response.isOk());
    }

    @Test
    public void testEnterProgModeSplit()
    {
        // Mock getCommandId method
        STKEnterProgMode stk500Command = Mockito.mock(STKEnterProgMode.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_ENTER_PROGMODE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_ENTER_PROGMODE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testLeaveProgMode()
    {
        // Mock getCommandId method
        STKLeaveProgramMode stk500Command = Mockito.mock(STKLeaveProgramMode.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_LEAVE_PROGMODE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_LEAVE_PROGMODE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testLeaveProgModeSplit()
    {
        // Mock getCommandId method
        STKLeaveProgramMode stk500Command = Mockito.mock(STKLeaveProgramMode.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_LEAVE_PROGMODE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_LEAVE_PROGMODE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testChipErase()
    {
        // Mock getCommandId method
        STKChipErase stk500Command = Mockito.mock(STKChipErase.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_CHIP_ERASE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_CHIP_ERASE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testChipEraseSplit()
    {
        // Mock getCommandId method
        STKChipErase stk500Command = Mockito.mock(STKChipErase.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_CHIP_ERASE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_CHIP_ERASE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testCheckAutoinc()
    {
        // Mock getCommandId method
        STKCheckAutoInc stk500Command = Mockito.mock(STKCheckAutoInc.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_CHECK_AUTOINC);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_CHECK_AUTOINC, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testCheckAutoincSplit()
    {
        // Mock getCommandId method
        STKCheckAutoInc stk500Command = Mockito.mock(STKCheckAutoInc.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_CHECK_AUTOINC);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_CHECK_AUTOINC, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testLoadAddress()
    {
        // Mock getCommandId method
        STKLoadAddress stk500Command = Mockito.mock(STKLoadAddress.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_LOAD_ADDRESS);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_LOAD_ADDRESS, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testLoadAddressSplit()
    {
        // Mock getCommandId method
        STKLoadAddress stk500Command = Mockito.mock(STKLoadAddress.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_LOAD_ADDRESS);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_LOAD_ADDRESS, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFlash()
    {
        // Mock getCommandId method
        STKProgramFlash stk500Command = Mockito.mock(STKProgramFlash.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FLASH);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FLASH, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFlashSplit()
    {
        // Mock getCommandId method
        STKProgramFlash stk500Command = Mockito.mock(STKProgramFlash.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FLASH);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FLASH, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgData()
    {
        // Mock getCommandId method
        STKProgramData stk500Command = Mockito.mock(STKProgramData.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_DATA);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_DATA, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgDataSplit()
    {
        // Mock getCommandId method
        STKProgramData stk500Command = Mockito.mock(STKProgramData.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_DATA);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_DATA, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFuse()
    {
        // Mock getCommandId method
        STKProgramFuse stk500Command = Mockito.mock(STKProgramFuse.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FUSE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFuseSplit()
    {
        // Mock getCommandId method
        STKProgramFuse stk500Command = Mockito.mock(STKProgramFuse.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FUSE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFuseExt()
    {
        // Mock getCommandId method
        STKProgramFuseExt stk500Command = Mockito.mock(STKProgramFuseExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FUSE_EXT);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE_EXT, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgFuseExtSplit()
    {
        // Mock getCommandId method
        STKProgramFuseExt stk500Command = Mockito.mock(STKProgramFuseExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_FUSE_EXT);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_FUSE_EXT, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgLock()
    {
        // Mock getCommandId method
        STKProgramLock stk500Command = Mockito.mock(STKProgramLock.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_LOCK);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_LOCK, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgLockSplit()
    {
        // Mock getCommandId method
        STKProgramLock stk500Command = Mockito.mock(STKProgramLock.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_LOCK);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_LOCK, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgPage()
    {
        // Mock getCommandId method
        STKProgramPage stk500Command = Mockito.mock(STKProgramPage.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_PAGE);

        // Create received buffer OK
        byte[] buffer = new byte[2];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_PAGE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testProgPageSplit()
    {
        // Mock getCommandId method
        STKProgramPage stk500Command = Mockito.mock(STKProgramPage.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_PROG_PAGE);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_PROG_PAGE, response.getCommandId());
        assertEquals(0, response.getParameters().length);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadFlash()
    {
        // Mock getCommandId method
        STKReadFlash stk500Command = Mockito.mock(STKReadFlash.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_FLASH);

        // Create received buffer OK
        byte[] buffer = new byte[4];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0xff;
        buffer[2] = (byte) 0x80;
        buffer[3] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_FLASH, response.getCommandId());
        assertEquals((byte) 0xff, response.getParameters()[0]);
        assertEquals((byte) 0x80, response.getParameters()[1]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadData()
    {
        // Mock getCommandId method
        STKReadData stk500Command = Mockito.mock(STKReadData.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_DATA);

        // Create received buffer
        byte[] buffer = new byte[3];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_DATA, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadDataSplit()
    {
        // Mock getCommandId method
        STKReadData stk500Command = Mockito.mock(STKReadData.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_DATA);

        // Create received buffer1
        byte[] buffer1 = new byte[1];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;

        byte[] buffer2 = new byte[2];
        buffer2[0] = (byte) 0x80;
        buffer2[1] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_DATA, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());

    }

    @Test
    public void testReadFuse()
    {
        // Mock getCommandId method
        STKReadFuse stk500Command = Mockito.mock(STKReadFuse.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_FUSE);

        // Create received buffer
        byte[] buffer = new byte[4];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) 0xff;
        buffer[3] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadFuseSplit()
    {
        // Mock getCommandId method
        STKReadFuse stk500Command = Mockito.mock(STKReadFuse.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_FUSE);

        // Create received buffer1
        byte[] buffer1 = new byte[3];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer1[1] = (byte) 0x80;
        buffer1[2] = (byte) 0xff;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadFuseExt()
    {
        // Mock getCommandId method
        STKReadFuseExt stk500Command = Mockito.mock(STKReadFuseExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_FUSE_EXT);

        // Create received buffer
        byte[] buffer = new byte[5];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) 0xff;
        buffer[3] = (byte) 0x27;
        buffer[4] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE_EXT, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals((byte) 0x27, response.getParameters()[2]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadFuseExtSplit()
    {
        // Mock getCommandId method
        STKReadFuseExt stk500Command = Mockito.mock(STKReadFuseExt.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_FUSE_EXT);

        // Create received buffer1
        byte[] buffer1 = new byte[4];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer1[1] = (byte) 0x80;
        buffer1[2] = (byte) 0xff;
        buffer1[3] = (byte) 0x27;

        // Create received buffer2
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_FUSE_EXT, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals((byte) 0x27, response.getParameters()[2]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadLock()
    {
        // Mock getCommandId method
        STKReadLock stk500Command = Mockito.mock(STKReadLock.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_LOCK);

        // Create received buffer OK
        byte[] buffer = new byte[3];
        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_LOCK, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());

    }

    @Test
    public void testReadLockSplit()
    {
        // Mock getCommandId method
        STKReadLock stk500Command = Mockito.mock(STKReadLock.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_LOCK);

        // Create received buffer1
        byte[] buffer1 = new byte[2];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer1[1] = (byte) 0x80;

        // Create received buffer1
        byte[] buffer2 = new byte[1];
        buffer2[0] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_LOCK, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadPage()
    {
        // Mock getCommandId method
        STKReadPage stk500Command = Mockito.mock(STKReadPage.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_PAGE);
        Mockito.when(stk500Command.getDataLength()).thenReturn(256);

        byte[] buffer = new byte[258];
        byte[] data = new byte[256];
        new Random().nextBytes(data);

        buffer[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        System.arraycopy(data, 0, buffer, 1, 256);
        buffer[257] = STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_PAGE, response.getCommandId());

        int i = 0;
        for(byte b : data)
        {
            assertEquals(b, response.getData()[i]);
            i++;
        }
        assertEquals(true, response.isOk());
    }

    @Test
    public void testReadPageSplit()
    {
        // Mock getCommandId method
        STKReadPage stk500Command = Mockito.mock(STKReadPage.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_PAGE);
        Mockito.when(stk500Command.getDataLength()).thenReturn(256);

        // Create received buffer1
        byte[] data = new byte[256];
        new Random().nextBytes(data);

        byte[] buffer1 = new byte[257];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        System.arraycopy(data, 0, buffer1, 1, 256);


        // Create received buffer1
        byte[] buffer2 = new byte[1];
        buffer2[0] = STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_PAGE, response.getCommandId());

        int i = 0;
        for(byte b : data)
        {
            assertEquals(b, response.getData()[i]);
            i++;
        }
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSignatureBytes()
    {
        // Mock getCommandId method
        STKReadSignature stk500Command = Mockito.mock(STKReadSignature.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_SIGN);

        // Create received buffer OK
        byte[] buffer = new byte[5];
        buffer[0] = STK500Constants.Cmnd_STK_READ_SIGN;
        buffer[1] = (byte) 0x80;
        buffer[2] = (byte) 0xff;
        buffer[3] = (byte) 0xf2;
        buffer[4] = STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_SIGN, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals((byte) 0xf2, response.getParameters()[2]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }

    @Test
    public void testSignatureBytesSplit()
    {
        // Mock getCommandId method
        STKReadSignature stk500Command = Mockito.mock(STKReadSignature.class);
        Mockito.when(stk500Command.getCommandId()).thenReturn(STK500Constants.Cmnd_STK_READ_SIGN);

        // Create received buffer1
        byte[] buffer1 = new byte[2];
        buffer1[0] = (byte) STK500Constants.Resp_STK_INSYNC;
        buffer1[1] = (byte) 0x80;

        // Create received buffer1
        byte[] buffer2 = new byte[3];
        buffer2[0] = (byte) 0xff;
        buffer2[1] = (byte) 0xf2;
        buffer2[2] = (byte) STK500Constants.Resp_STK_OK;

        // Generate STK500 response object
        assertEquals(false, candidate.generateSTK500Response(stk500Command, buffer1));
        assertEquals(true, candidate.generateSTK500Response(stk500Command, buffer2));

        STKInsync response = (STKInsync) candidate.getCurrentResponse();
        assertEquals(STK500Constants.Cmnd_STK_READ_SIGN, response.getCommandId());
        assertEquals((byte) 0x80, response.getParameters()[0]);
        assertEquals((byte) 0xff, response.getParameters()[1]);
        assertEquals((byte) 0xf2, response.getParameters()[2]);
        assertEquals(0, response.getData().length);
        assertEquals(true, response.isOk());
    }
}
