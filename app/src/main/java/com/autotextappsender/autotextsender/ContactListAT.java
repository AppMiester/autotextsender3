package com.autotextappsender.autotextsender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.autotextappsender.UtilitySaveLoad;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactListAT extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static String nameHolder;
    public static String phoneNumberHolder;
    Context context = this;
    private List<Contact> contacts = new ArrayList<>();


    public void setContacts() {
        contacts = UtilitySaveLoad.loadContacts(context);
    }

    public int getContactSize(){
        return contacts.size();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_at);


        setContacts();
        ListView listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.textView2_create_msg:
                System.out.println("onran2");
                Intent intent = new Intent(this, CreateMessage.class);
                startActivity(intent);
                break;


            default:
                System.out.println("onran3");
                return false;
        }


        return false;
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getContactSize();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout, null);

            TextView textview_firstname = (TextView) convertView.findViewById(R.id.textView2_firstname);
            TextView textview_phonenumber = (TextView) convertView.findViewById(R.id.textView4_phonenumber);

            textview_firstname.setText(contacts.get(position).name);
            textview_phonenumber.setText(contacts.get(position).phoneNumber);

            return convertView;
        }
    }


    public void starMenu(View v) {

        RelativeLayout viewParentRow = (RelativeLayout) v.getParent();

        TextView textViewName = (TextView) viewParentRow.getChildAt(0);
        TextView textPhoneNumber = (TextView) viewParentRow.getChildAt(1);


        nameHolder = textViewName.getText().toString();
        phoneNumberHolder = textPhoneNumber.getText().toString();


        PopupMenu popup = new PopupMenu(this, v);


        //sets popup menu list items
        popup.inflate(R.menu.contactpopup);
        Menu menu = popup.getMenu();
        onPrepareOptionsMenu(menu);
        popup.setOnMenuItemClickListener(ContactListAT.this);


        popup.show();


    }


    public boolean onPrepareOptionsMenu(Menu menu) {

        String createmsgStringResource = getString(R.string.createmsg, nameHolder);
        String viewEditmessageStringResource = getString(R.string.view_edt_msg, nameHolder);

        menu.getItem(0).setTitle(createmsgStringResource);
        menu.getItem(1).setTitle(viewEditmessageStringResource);

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("onran");


        switch (item.getItemId()) {


            case R.id.textView2_create_msg:
                System.out.println("onran2");
                Intent intent = new Intent(this, CreateMessage.class);
                startActivity(intent);
                break;


            default:
                System.out.println("onran3");
                return super.onOptionsItemSelected(item);
        }

        return true;

    }




    @Override
    protected void onResume() {
        super.onResume();
        setContacts();

    }


}