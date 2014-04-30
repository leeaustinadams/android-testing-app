package com.sample.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends Activity {
    public static final String CONTACT_NAME = "contact_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        handleIntent();
    }

    private void handleIntent() {
        final Intent intent = getIntent();
        final String name = intent.getStringExtra(CONTACT_NAME);
        final TextView textView = (TextView) findViewById(R.id.contact_name);
        textView.setText(name);
    }
}
