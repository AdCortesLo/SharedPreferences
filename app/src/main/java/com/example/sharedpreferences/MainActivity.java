package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static String sharedPreferencesFile = "MainActivitySharedPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usuaris, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sp = findViewById(R.id.spinner);
        sp.setAdapter(adapter);
    }

    public void onClickLogin(View view){

        EditText et = findViewById(R.id.editText);
        Spinner sp = findViewById(R.id.spinner);
        CheckBox cb = findViewById(R.id.checkBox);

        if (cb.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE).edit();
            editor.putString(sp.getSelectedItem().toString(), et.getText().toString());
            editor.apply();
        }
        else {
            SharedPreferences preferences = getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE);
            String passwordGuardada = preferences.getString(sp.getSelectedItem().toString(), "");
            checkLogin(passwordGuardada, et.getText().toString());
        }
    }

    public void checkLogin(String passwordGuardada, String passwordIntent){

        if (passwordGuardada.equals(passwordIntent)){
            Intent i = new Intent(this, BenvingudaActivity.class);
            startActivity(i);
        }

    }
}
