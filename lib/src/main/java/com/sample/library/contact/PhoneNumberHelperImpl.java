package com.sample.library.contact;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

class PhoneNumberHelperImpl implements PhoneNumberHelper {
    private final Context mContext;

    public PhoneNumberHelperImpl(final Context context) {
        mContext = context;
    }

    public boolean hasPhoneNumber() {
        return !TextUtils.isEmpty(getPhoneNumber());
    }

    public String getPhoneNumber() {
        final TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(
                Context.TELEPHONY_SERVICE);
        return PhoneNumberUtils.formatNumber(telephonyManager.getLine1Number());
    }
}
