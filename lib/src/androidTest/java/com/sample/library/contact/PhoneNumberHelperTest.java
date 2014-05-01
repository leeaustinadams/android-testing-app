package com.sample.library.contact;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.test.AndroidTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ladams on 4/30/14.
 */
public class PhoneNumberHelperTest extends AndroidTestCase {
    private static String MOCK_NUMBER = "14153099358";
    private static String FORMATTED_NUMBER = "1-415-309-9358";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().toString());
    }

    public void testGetPhoneNumber() {
        final Context mockContext = mock(Context.class);
        final TelephonyManager mockTelephonyManager = mock(TelephonyManager.class);

        when(mockContext.getSystemService(Context.TELEPHONY_SERVICE)).thenReturn(
                mockTelephonyManager);
        when(mockTelephonyManager.getLine1Number()).thenReturn(MOCK_NUMBER);

        final PhoneNumberHelper phoneNumberHelper = new PhoneNumberHelperImpl(mockContext);
        final String actualNumber = phoneNumberHelper.getPhoneNumber();
        assertEquals(FORMATTED_NUMBER, actualNumber);
    }

    public void testHasPhoneNumberFalseWhenEmptyPhoneNumber() {
        final Context mockContext = mock(Context.class);
        final TelephonyManager mockTelephonyManager = mock(TelephonyManager.class);

        when(mockContext.getSystemService(Context.TELEPHONY_SERVICE)).thenReturn(
                mockTelephonyManager);
        when(mockTelephonyManager.getLine1Number()).thenReturn("");

        final PhoneNumberHelper phoneNumberHelper = new PhoneNumberHelperImpl(mockContext);
        assertFalse(phoneNumberHelper.hasPhoneNumber());
    }
}
