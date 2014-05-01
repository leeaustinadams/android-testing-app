package com.sample.app.espresso;

import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.sample.app.ProfileActivity;
import com.sample.app.R;
import com.sample.library.contact.PhoneNumberHelper;
import com.sample.library.contact.PhoneNumberHelperFactory;

import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

public class ProfileActivityTest extends ActivityInstrumentationTestCase2<ProfileActivity> {

    @MockitoAnnotations.Mock
    private PhoneNumberHelper mPhoneNumberHelper;

    public ProfileActivityTest() {
        super(ProfileActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initMocks(this);
        PhoneNumberHelperFactory.setInstance(mPhoneNumberHelper);
    }

    @Override
    protected void tearDown() throws Exception {
        PhoneNumberHelperFactory.setInstance(null);
        super.tearDown();
    }

    public void testNoPhoneNumber() {
        when(mPhoneNumberHelper.hasPhoneNumber()).thenReturn(false);
        when(mPhoneNumberHelper.getPhoneNumber()).thenReturn("");
        getActivity();
        onView(withId(R.id.profile_name)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_phone_number))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    public void testWithPhoneNumber() {
        final String phoneNumber = "1-555-123-4567";
        when(mPhoneNumberHelper.hasPhoneNumber()).thenReturn(true);
        when(mPhoneNumberHelper.getPhoneNumber()).thenReturn(phoneNumber);
        getActivity();
        onView(withId(R.id.profile_name)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_phone_number))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .check(matches(withText(phoneNumber)));
    }
}
