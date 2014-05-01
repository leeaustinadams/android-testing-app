package com.sample.library.contact;

import android.content.Context;
import android.database.Cursor;

public interface ContactFetcher {
    Cursor fetchContacts(final Context context);
}
