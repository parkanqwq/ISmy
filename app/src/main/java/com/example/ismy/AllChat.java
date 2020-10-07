package com.example.ismy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismy.Adapter.AdapterAllChat;
import com.example.ismy.BDHelper.DBHelper;
import com.example.ismy.Model.Chat;

import java.util.ArrayList;
import java.util.List;

public class AllChat extends AppCompatActivity {

    public DBHelper dbHelper;
    AdapterAllChat adapterAllChat;
    List<Chat> mChat;
    RecyclerView recycler_view_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);

        dbHelper = new DBHelper(this);

        recycler_view_all = findViewById(R.id.recycler_view_all);
        recycler_view_all.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recycler_view_all.setLayoutManager(linearLayoutManager);

        mChat = new ArrayList<>();
        mChat.clear();

        SQLiteDatabase database = new DBHelper(this).getReadableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {

            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int otvetIndex = cursor.getColumnIndex(DBHelper.KEY_OTVET);
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int voprosIndex = cursor.getColumnIndex(DBHelper.KEY_VOPROS);

            do {
                Chat piper = new Chat();
                piper.setName(cursor.getString(nameIndex));
                piper.setVopros(cursor.getString(voprosIndex));
                piper.setId(cursor.getString(idIndex));
                piper.setOtvet(cursor.getString(otvetIndex));

                mChat.add(piper);

            }while (cursor.moveToNext());
        }else
        {}
        cursor.close();

        adapterAllChat = new AdapterAllChat(AllChat.this, mChat);
        recycler_view_all.setAdapter(adapterAllChat);
    }
}
