package com.sample.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sample.library.contact.PhoneNumberHelper;
import com.sample.library.contact.PhoneNumberHelperFactory;

public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final PhoneNumberHelper phoneNumberHelper = PhoneNumberHelperFactory.get(this);
        if (phoneNumberHelper.hasPhoneNumber()) {
            final TextView phoneNumber = (TextView) findViewById(R.id.profile_phone_number);
            phoneNumber.setText(phoneNumberHelper.getPhoneNumber());
            phoneNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                final Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
