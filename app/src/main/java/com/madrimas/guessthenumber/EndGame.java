package com.madrimas.guessthenumber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EndGame extends AppCompatActivity {

    public final static String PREFERENCE_FILE = "com.madrimas.guessthenumber.PREFERENCES";
    public final static String PREF_VAL = "MY_PREFERENCE";
    Map<String, ?> prefsMap = new ArrayMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        String tries = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("Wygrałeś! Udało Ci się zgadnąć w " + tries + ". próbie");

        if(!PREFERENCE_FILE.isEmpty()){
            readPrefs(null);
        }
    }

    public void readPrefs(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        String val = sharedPreferences.getString(PREF_VAL, "");
        EditText editText = (EditText) findViewById(R.id.editText2);
        editText.setText(val);
    }

    public void savePrefs(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        EditText editText = (EditText) findViewById(R.id.editText2);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = getIntent();
        String message = editText.getText().toString() + ", " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        editor.putString(PREF_VAL, message);
        editor.commit();
    }

    public void readPrefsAll(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        prefsMap = sharedPreferences.getAll();
        //prefsMap.get
    }
}