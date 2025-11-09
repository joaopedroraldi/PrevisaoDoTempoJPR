package com.previsaodotempojpr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private EditText cityEditText;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cityEditText = findViewById(R.id.city_edittext);
        goButton = findViewById(R.id.go_button);

        goButton.setOnClickListener(v -> {
            String city = cityEditText.getText().toString();
            if (!city.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("city", city);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}