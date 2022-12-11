package com.example.mylab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;
    Button btnAddData;
    EditText etName, etEmail, etTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleDB = new DatabaseHelper(this);

        etName = findViewById(R.id.etNewName);
        etEmail = findViewById(R.id.etNewEmail);
        etTvShow = findViewById(R.id.etNewTVShow);
        btnAddData = findViewById(R.id.btnAddData);

        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = peopleDB.addData(etName.getText().toString(), etEmail.getText().toString(), etTvShow.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}