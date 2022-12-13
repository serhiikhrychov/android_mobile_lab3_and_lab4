package com.example.mylab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;
    Button btnAddData, btnViewData;
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
        btnViewData = findViewById(R.id.btnViewData);

        AddData();
        ViewData();
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

    public void ViewData() {
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = peopleDB.ShowData();
                if (data.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (data.moveToNext()) {
                        buffer.append("ID: " + data.getString(0) + "\n");
                        buffer.append("Name: " + data.getString(1) + "\n");
                        buffer.append("Email: " + data.getString(2) + "\n");
                        buffer.append("Favorite TV Show: " + data.getString(3) + "\n");

                        display("Your stored data", buffer.toString());
                    }
                }
            }
        });
    }

    public void display (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}