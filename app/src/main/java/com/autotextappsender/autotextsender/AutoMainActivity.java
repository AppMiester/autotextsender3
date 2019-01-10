package com.autotextappsender.autotextsender;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;


public class AutoMainActivity extends AppCompatActivity {

    static int firstime = 1;
    public static boolean savePermission= false;



    public void changeCreateContact(View view) {
        Intent intent = new Intent(this, CreateContact.class);
        startActivity(intent);
    }

    public void changeContactList(View view)  {
        Intent intent = new Intent(this,ContactListAT.class);
        startActivity(intent);
    }

   private void saveData(boolean permission) {
       if (permission) {
           System.out.println("Save data1");
           SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit();
           Gson gson = new Gson();
           String json = gson.toJson(Contact.contactArraylist);
           editor.putString("task list", json);
           editor.apply();
           System.out.println("savedata2");

       }}

       private void loadData ( int time){
           if (time == 1) {
               System.out.println("loaddata1" + firstime);
               SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
               Gson gson = new Gson();
               String json = sharedPreferences.getString("task list", null);
               Type type = new TypeToken<ArrayList<Contact>>() {
               }.getType();

               ArrayList <Contact> temp = new ArrayList<Contact>();
               temp = gson.fromJson(json, type);

               if(temp != null) {
                   Iterator<Contact> it = temp.iterator();

                   while (it.hasNext()) {
                       Contact.contactArraylist.add(it.next());
                   }
                   System.out.println("loaddata2");
                   // System.out.println("loaddatares" + Contact.contactArraylist.size());
               }

               if (Contact.contactArraylist == null) {
                   Contact.contactArraylist = new ArrayList<Contact>();
                   System.out.println("loaddata3");
               }
           }
       }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_main);
      //  int contactSize = Contact.contactArraylist.size();
     //  boolean bnull = (Contact.contactArraylist== null);
       // if(Contact.contactArraylist.size() > 0) {
          //  saveData();
      //  }
        saveData(savePermission);
        savePermission=false;

       loadData(firstime);
        firstime++;



    }



}
