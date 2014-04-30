package com.sample.app;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sample.library.contact.ContactFetcher;

public class ContactsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private CursorAdapter mAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_list, container, false);
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Cursor cursor = ContactFetcher.fetchContacts(getActivity());
        mAdapter = new SimpleCursorAdapter(
                getActivity(), R.layout.contact_row, cursor,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                new int[] { R.id.contact_name }, 0);
        final View parent = getView();
        final ListView listView = (ListView) parent.findViewById(android.R.id.list);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        updateList(cursor.getCount() > 0);

        final Button refresh = (Button) parent.findViewById(R.id.refresh_button);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                refreshContacts();
            }
        });
    }

    private void refreshContacts() {
        final Cursor cursor = ContactFetcher.fetchContacts(getActivity());
        mAdapter.changeCursor(cursor);
        updateList(cursor.getCount() > 0);
    }

    private void updateList(final boolean hasContacts) {
        final View parent = getView();
        final View noContacts = parent.findViewById(R.id.no_contacts_label);
        final View list = parent.findViewById(android.R.id.list);
        if (hasContacts) {
            noContacts.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        } else {
            noContacts.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view,
            final int position, final long id) {
        final TextView textView = (TextView) view.findViewById(R.id.contact_name);
        final String name = textView.getText().toString();
        final Intent intent = new Intent(getActivity(), ContactActivity.class);
        intent.putExtra(ContactActivity.CONTACT_NAME, name);
        startActivity(intent);
    }
}
