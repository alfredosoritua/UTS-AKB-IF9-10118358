package com.example.akb10118358.editTask;

// 3 Juni 2021 - 10118358 - Alfredo Soritua - IF9

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.akb10118358.R;
import com.example.akb10118358.database.SQLite;
import com.example.akb10118358.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {

    private EditText judulEditText, kategoriEditText, isiEditText;
    private Date date;
    private SimpleDateFormat dateFormat;
    private Button submitButton;
    private SQLite helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_task);

        Bundle bundle = getIntent().getExtras();

        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());

        submitButton = findViewById(R.id.submitButton);
        judulEditText = findViewById(R.id.judul);
        kategoriEditText = findViewById(R.id.kategori);
        isiEditText = findViewById(R.id.isi);

        judulEditText.setText(bundle.getString("Judul"));
        kategoriEditText.setText(bundle.getString("Kategori"));
        isiEditText.setText(bundle.getString("Isi"));

        helper = new SQLite(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = bundle.getString("Id");
                String judul = judulEditText.getText().toString();
                String kategori = kategoriEditText.getText().toString();
                String isi = isiEditText.getText().toString();
                String formattedDate = dateFormat.format(date);

                if (TextUtils.isEmpty(judul)) {
                    judulEditText.setError("Data tidak boleh kosong");
                    judulEditText.requestFocus();
                } else if (TextUtils.isEmpty(kategori)) {
                    kategoriEditText.setError("Data tidak boleh kosong");
                    kategoriEditText.requestFocus();
                } else if (TextUtils.isEmpty(isi)) {
                    isiEditText.setError("Data tidak boleh kosong");
                    isiEditText.requestFocus();
                } else {
                    boolean isSuccess = helper.updateData(id, judul, kategori, isi, formattedDate);

                    if (isSuccess) {
                        Toast.makeText(EditTaskActivity.this, "Data has been added", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditTaskActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(EditTaskActivity.this, "Data failed to save", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}