package com.autotextappsender;

import android.content.Context;
import android.content.SharedPreferences;

import com.autotextappsender.autotextsender.Constants;
import com.autotextappsender.autotextsender.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class UtilitySaveLoad {


    public static List<Contact> loadContacts(Context context) {




        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CONTACT_PREFS_FILE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.CONTACT_LIST_PREFS, null);
        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();

        ArrayList <Contact> temp = gson.fromJson(json, type);

        if(temp != null){
            return temp;
        }
        return new ArrayList<>();


    }


    public static void saveContacts(Context context, List<Contact> contact){


            Gson gson = new Gson();
            String json = gson.toJson(contact);

            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CONTACT_PREFS_FILE, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.CONTACT_LIST_PREFS,json);
            editor.apply();





    }
}
