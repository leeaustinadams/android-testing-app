package com.sample.library.contact;

import android.content.Context;

public class PhoneNumberHelperFactory {
    private static PhoneNumberHelper sInstance;

    public static PhoneNumberHelper get(final Context context) {
        if (sInstance == null) {
            return new PhoneNumberHelperImpl(context);
        }
        return sInstance;
    }

    // visible for testing
    public static void setInstance(final PhoneNumberHelper phoneNumberHelper) {
        sInstance = phoneNumberHelper;
    }
}
