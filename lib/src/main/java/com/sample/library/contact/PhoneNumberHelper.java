package com.sample.library.contact;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public final class PhoneNumberHelper {
    private PhoneNumberHelper() {
        throw new AssertionError();
    }

    public static boolean hasPhoneNumber(final Context context) {
        return !TextUtils.isEmpty(getPhoneNumber(context));
    }

    public static String getPhoneNumber(final Context context) {
        final TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return PhoneNumberUtils.formatNumber(telephonyManager.getLine1Number());
    }
}
