package com.sample.library.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public final class ContactFetcher {
    public static final String SELECTION = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '1'";
    public static final String SORT_ORDER = ContactsContract.Contacts.DISPLAY_NAME
            + " COLLATE LOCALIZED ASC";

    public static Cursor fetchContacts(final Context context) {
        final Uri uri = ContactsContract.Contacts.CONTENT_URI;
        final String[] projection = new String[] {
                ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME
        };
        return context.getContentResolver()
                .query(uri, projection, SELECTION, null, SORT_ORDER);
    }
}
