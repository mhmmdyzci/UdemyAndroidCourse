package com.mhmmdyzci.multipleactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
    }
    public  void changeActivity(View view ){
        String name = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class );
        intent.putExtra("userName", name);

        startActivity(intent);
    }
}