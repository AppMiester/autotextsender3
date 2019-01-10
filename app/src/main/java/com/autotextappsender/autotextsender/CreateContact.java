package com.autotextappsender.autotextsender;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateContact extends AppCompatActivity {
    ArrayList<Contact> templist = new ArrayList<Contact>();
    public CreateContact(){};
    static boolean hasContent ;





    public void addContact(String s,String p){
        String checked="";
        for(int i = 0; i < p.length(); i++){
           char check = p.charAt(i);
           if(Character.isDigit(check)){
               checked+=check;
           }
          }

          if(s.equals("")){
            hasContent = false;

          }else{
            hasContent= true;
            Contact contact = new Contact(s,checked);
            }
    }



    public void changeCreateContact(View view){
        Intent intent = new Intent(this,AutoMainActivity.class);
        startActivity(intent);
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);



      final  EditText firstname1 = (EditText)findViewById(R.id.firstname);

        final EditText phonenumber1 = (EditText)findViewById(R.id.phonenumber);





        ImageButton arrow = (ImageButton) this.findViewById(R.id.imageButton2);
        final Context context=this;

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AutoMainActivity.class);
                startActivity(intent);
                }
        });



        Button submit = (Button)findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String firstName = firstname1.getText().toString();

                 String phoneNumber= phonenumber1.getText().toString();

               new CreateContact().addContact(firstName,phoneNumber);
                Intent intent = new Intent(context,AutoMainActivity.class);
                 if(hasContent) {

                     Toast.makeText(context, "contact has been saved", Toast.LENGTH_LONG).show();
                     AutoMainActivity.savePermission=true;
                     startActivity(intent);
                 }
                 if(!hasContent){
                     Toast.makeText(context,"Input Name",Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }



}
