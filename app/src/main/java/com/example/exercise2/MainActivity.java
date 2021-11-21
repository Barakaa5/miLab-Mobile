package com.example.exercise2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stark_button = (Button) findViewById(R.id.stark_button);
        stark_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activity2.class);
                startActivity(intent);
            }
        });

        Button lannister_button = (Button) findViewById(R.id.lannister_button);
        lannister_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activity3.class);
                startActivity(intent);
            }
        });
    }
}