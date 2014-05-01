package com.sample.app.espresso;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;

import com.sample.app.HomeActivity;
import com.sample.app.R;
import com.sample.library.contact.ContactFetcher;
import com.sample.library.contact.ContactFetcherFactory;

import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.hasDescendant;

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private final String[] mProjection = new String[] {
        ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME
    };

    @Mock
    private ContactFetcher mMockContactFetcher;

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initMocks(this);
        ContactFetcherFactory.setInstance(mMockContactFetcher);
    }

    @Override
    protected void tearDown() throws Exception {
        ContactFetcherFactory.setInstance(null);
        super.tearDown();
    }

    public void testNoContactsWhenCursorEmpty() {
        final MatrixCursor cursor = new MatrixCursor(mProjection, 0);
        when(mMockContactFetcher.fetchContacts(any(Context.class))).thenReturn(cursor);
        getActivity();
        onView(withId(R.id.no_contacts_label)).check(matches(isDisplayed()));
        onView(withId(android.R.id.list)).check(matches(not(isDisplayed())));
    }

    public void testWithContacts() {
        final MatrixCursor cursor = new MatrixCursor(mProjection, 0);
        for (int i = 0; i < 5; i++) {
            cursor.addRow(new Object[] { i, "Contact" + i });
        }
        when(mMockContactFetcher.fetchContacts(any(Context.class))).thenReturn(cursor);
        getActivity();
        onView(withId(R.id.no_contacts_label)).check(matches(not(isDisplayed())));
        onView(withId(android.R.id.list)).check(matches(isDisplayed()));
        for (int i = 0; i < 5; i++) {
            onData(instanceOf(Cursor.class)).atPosition(i)
                    .check(matches(hasDescendant(withText("Contact" + i))));
        }
    }

    public void testClickContact() {
        final MatrixCursor cursor = new MatrixCursor(mProjection, 0);
        for (int i = 0; i < 15; i++) {
            cursor.addRow(new Object[] { i, "Contact" + i });
        }
        when(mMockContactFetcher.fetchContacts(any(Context.class))).thenReturn(cursor);
        getActivity();
        onData(instanceOf(Cursor.class)).atPosition(10)
                .check(matches(hasDescendant(withText("Contact" + 10))))
                .perform(click());
        onView(withId(R.id.contact_name)).check(matches(isDisplayed()))
                .check(matches(withText("Contact" + 10)));
    }
}
