package com.example.lab3_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contacts; // List of contacts variable
    private ContactAdapter adapter; // Adapter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+", Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        // Create the adapter and set it to the ListView
        ListView listViewContacts = findViewById(R.id.listViewContacts); // Corrected ID here
        adapter = new ContactAdapter(this, R.layout.list_item_contact, contacts, null);
        listViewContacts.setAdapter(adapter);

        // Set long click listener for the ListView items
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the contact that was long clicked
                Contact contact = contacts.get(position);
                // Delete the contact
                deleteContact(contact);
                return true; // Indicate that the long click event has been handled
            }
        });
    }

    // Method to delete a contact
    private void deleteContact(Contact contact) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteContact(contact); // Delete contact from the database
        contacts.remove(contact); // Remove contact from the list
        adapter.notifyDataSetChanged(); // Notify the adapter that the dataset has changed
    }
}