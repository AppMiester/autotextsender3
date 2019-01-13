package com.autotextappsender.autotextsender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.EditText;

public class CreateMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        setEnterText();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    public void setEnterText(){

        EditText enterMessage=(EditText) findViewById(R.id.editText2_enter_message);

        String enterMessageStringResource =getString(R.string.enter_message_for)+ " "+ ContactListAT.nameHolder;

        enterMessage.setHint(enterMessageStringResource);

    }
}
