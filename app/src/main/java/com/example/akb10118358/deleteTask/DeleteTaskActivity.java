package com.example.akb10118358.deleteTask;

// 3 Juni 2021 - 10118358 - Alfredo Soritua - IF9

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.akb10118358.R;
import com.example.akb10118358.database.SQLite;
import com.example.akb10118358.main.MainActivity;

public class DeleteTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);

        getSupportActionBar().hide();

        Button yesButton = findViewById(R.id.deleteYes);
        Button noButton = findViewById(R.id.deleteNo);

        SQLite helper = new SQLite(this);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("Id");

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isSuccess = helper.deteleData(id);

                if (isSuccess > 0) {
                    Toast.makeText(DeleteTaskActivity.this, "Data has been remove", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DeleteTaskActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(DeleteTaskActivity.this, "Data failed to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteTaskActivity.this, MainActivity.class));
            }
        });
    }
}