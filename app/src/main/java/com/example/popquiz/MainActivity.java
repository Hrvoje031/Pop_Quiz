package com.example.popquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerHome = findViewById(R.id.spinner_kategorije);
        String[] arraySpinnerHome = new String[] {"Odaberi kategoriju", "Matematika", "Hrvatski","Povijest", "Geografija", "Fizika"};
        ArrayAdapter<String> length = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item ,arraySpinnerHome);
        length.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHome.setAdapter(length);

        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Matematika")) {
                    Intent intent = new Intent(MainActivity.this, Matematika.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Hrvatski")) {
                    Intent intent = new Intent(MainActivity.this, Hrvatski.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Povijest")) {
                    Intent intent = new Intent(MainActivity.this, Povijest.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Geografija")) {
                    Intent intent = new Intent(MainActivity.this, Geografija.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Fizika")) {
                    Intent intent = new Intent(MainActivity.this, Fizika.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}