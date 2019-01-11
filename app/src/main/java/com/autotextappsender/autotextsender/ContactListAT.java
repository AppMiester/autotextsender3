package com.autotextappsender.autotextsender;

import android.content.Intent;
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



import java.util.ArrayList;

public class ContactListAT extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static String nameHolder;
    public static String phoneNumberHolder;



    Contact [] contacts = new Contact [Contact.contactArraylist.size()];
    String [] firstname = new String [Contact.contactArraylist.size()];

    String [] phonenumber= new String [Contact.contactArraylist.size()];

    public  void populateArrays(){
        for(int j = 0; j<contacts.length; j++){
            contacts[j]=Contact.contactArraylist.get(j);
        }

        for(int k = 0; k < contacts.length; k++){
            firstname[k]=contacts[k].name;
            phonenumber[k]=contacts[k].phoneNumber;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_at);





       populateArrays();
       ListView listView =(ListView)findViewById(R.id.listview);
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


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contacts.length;
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
            convertView =getLayoutInflater().inflate(R.layout.customlayout,null);

            TextView textview_firstname = (TextView)convertView.findViewById(R.id.textView2_firstname);
            TextView textview_phonenumber = (TextView)convertView.findViewById(R.id.textView4_phonenumber);

            textview_firstname.setText(firstname[position]);
            textview_phonenumber.setText(phonenumber[position]);

            return convertView;
        }
    }


    public void starMenu(View v){

        RelativeLayout viewParentRow =(RelativeLayout) v.getParent();

        TextView textViewName = (TextView)viewParentRow.getChildAt(0);
        TextView textPhoneNumber = (TextView)viewParentRow.getChildAt(1);


        nameHolder = textViewName.getText().toString();
        phoneNumberHolder=textPhoneNumber.getText().toString();
        System.out.println("Namee and number = "+ nameHolder + " "+ phoneNumberHolder);


        PopupMenu popup = new PopupMenu(this,v);
        MenuInflater menuInflater = popup.getMenuInflater();




        //sets popup menu list items
        popup.inflate(R.menu.contactpopup);
        Menu menu = popup.getMenu();
        onPrepareOptionsMenu(menu);
        popup.setOnMenuItemClickListener(ContactListAT.this);



        popup.show();


    }








    public boolean onPrepareOptionsMenu (Menu menu){

        String createmsgStringResource=getString(R.string.createmsg)+" "  +nameHolder;
        String  viewEditmessageStringResource=getString(R.string.view_edt_msg)+ " " + nameHolder;

        menu.clear();
        MenuInflater inflater =getMenuInflater();

           inflater.inflate(R.menu.contactpopup,menu);
        MenuItem item0 = menu.findItem(R.id.textView2_create_msg);
        MenuItem item1 = menu.findItem(R.id.textView2_view_edit_msg);
        menu.getItem(0).setTitle(createmsgStringResource);
        menu.getItem(1).setTitle(viewEditmessageStringResource);

       // item0.setTitle(createmsgStringResource);
        //item1.setTitle(viewEditmessageStringResource);


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


   /* public boolean onMenuItemClick(MenuItem item) {
        System.out.println("onran");

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
        return true;

    }*/



    }

















