package com.example.akb10118358.main.menu.task;

// 3 Juni 2021 - 10118358 - Alfredo Soritua - IF9

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.akb10118358.R;
import com.example.akb10118358.deleteTask.DeleteTaskActivity;
import com.example.akb10118358.editTask.EditTaskActivity;
import com.example.akb10118358.model.Task;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Task> listTask;
    private Context context;
    private TextView judul, date, kategori, isi;
    private Button editButton, deleteButton;

    public ListViewAdapter(List<Task> listTask, Context context) {
        this.listTask = listTask;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_card,null);

        judul = view.findViewById(R.id.cardJudul);
        date = view.findViewById(R.id.cardDate);
        kategori = view.findViewById(R.id.cardKategori);
        isi = view.findViewById(R.id.cardIsi);
        editButton = view.findViewById(R.id.editButton);
        deleteButton = view.findViewById(R.id.deleteButton);

        judul.setText(listTask.get(position).getJudul());
        date.setText(listTask.get(position).getDate());
        kategori.setText(listTask.get(position).getKategori());
        isi.setText(listTask.get(position).getIsi());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskActivity.class);
                intent.putExtra("Id", listTask.get(position).getId());
                intent.putExtra("Judul", listTask.get(position).getJudul());
                intent.putExtra("Date", listTask.get(position).getDate());
                intent.putExtra("Kategori", listTask.get(position).getKategori());
                intent.putExtra("Isi", listTask.get(position).getIsi());

                context.startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeleteTaskActivity.class);
                intent.putExtra("Id", listTask.get(position).getId());

                context.startActivity(intent);
            }
        });

        return view;
    }
}
