package com.example.guidageparking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_etat);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Etat.class);
            startActivity(intent);

        });
        Button button1 = findViewById(R.id.buttonPlan);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Plan.class);
            startActivity(intent);

        });
    }

}