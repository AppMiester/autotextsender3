package com.autotextappsender.autotextsender;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.autotextappsender.UtilitySaveLoad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CreateContact extends AppCompatActivity {
    List<Contact> contacts;

    public static Context context;
    static boolean hasContent;


    public void addContact(String s, String p) {
        String checked = "";
        for (int i = 0; i < p.length(); i++) {
            char check = p.charAt(i);
            if (Character.isDigit(check)) {
                checked += check;
            }
        }

        if (s.equals("")) {
            hasContent = false;

        } else {
            hasContent = true;
            Contact contactNew = new Contact(s, checked);

            contacts = UtilitySaveLoad.loadContacts(context);
            contacts.add(contactNew);
            Collections.sort(contacts, new Contact.SortContactArray());
            UtilitySaveLoad.saveContacts(context, contacts);

        }
    }


    public void changeCreateContact(View view) {
        Intent intent = new Intent(this, AutoMainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        context = this;


        final EditText firstname1 = (EditText) findViewById(R.id.firstname);

        final EditText phonenumber1 = (EditText) findViewById(R.id.phonenumber);


        ImageButton arrow = (ImageButton) this.findViewById(R.id.imageButton2);
        final Context context = this;

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AutoMainActivity.class);
                startActivity(intent);
            }
        });


        Button submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstname1.getText().toString();

                String phoneNumber = phonenumber1.getText().toString();

                new CreateContact().addContact(firstName, phoneNumber);
                Intent intent = new Intent(context, AutoMainActivity.class);
                if (hasContent) {

                    Toast.makeText(context, "contact has been saved", Toast.LENGTH_LONG).show();
                    AutoMainActivity.savePermission = true;
                    startActivity(intent);
                }
                if (!hasContent) {
                    Toast.makeText(context, "Input Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
