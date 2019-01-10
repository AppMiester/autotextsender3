package com.autotextappsender.autotextsender;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Contact {





        public String name;

        public String phoneNumber;
        public ArrayList<Message> messageArrayList= new ArrayList<Message>();
        public static ArrayList<Contact> contactArraylist= new <Contact> ArrayList();

        public Contact(String name,String phonenumber){
            this.name=name;
            phoneNumber = phonenumber;

            contactArraylist.add(this);
            sortContactArray();
        }


        public static void sortContactArray(){
            Collections.sort(Contact.contactArraylist,new SortContactArray());
        }




 public static class SortContactArray implements Comparator <Contact>{


     @Override
     public int compare(Contact o1, Contact o2) {
         int x = o1.name.compareToIgnoreCase(o2.name);
         return x;
     }
 }

}

