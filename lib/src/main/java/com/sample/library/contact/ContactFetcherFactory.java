package com.sample.library.contact;

public class ContactFetcherFactory {

    private static ContactFetcher sInstance;

    private ContactFetcherFactory() {
    }

    public static ContactFetcher get() {
        if (sInstance == null) {
            return new ContactFetcherImpl();
        } else {
            return sInstance;
        }
    }

    // visible for testing
    public static void setInstance(final ContactFetcher contactFetcher) {
        sInstance = contactFetcher;
    }
}
