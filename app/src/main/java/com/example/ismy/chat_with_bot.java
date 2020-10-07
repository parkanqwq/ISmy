package com.example.ismy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismy.Adapter.AdapterChat;
import com.example.ismy.BDHelper.DBHelper;
import com.example.ismy.BDHelper.DBHelperChat;
import com.example.ismy.Model.Chat;

import java.util.ArrayList;
import java.util.List;

public class chat_with_bot extends AppCompatActivity {

    public DBHelper dbHelper;
    public DBHelperChat dbChata;
    public String MyMSG = "MyMSG";
    public boolean messageB = false;

    AdapterChat adapterChat;
    List<Chat> mChat;
    RecyclerView recycler_view;
    EditText text_send;
    Button chatActivity;
    ImageButton btn_send, btn_otvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_with_bot);

        dbHelper = new DBHelper(this);
        dbChata = new DBHelperChat(this);

        chatActivity = findViewById(R.id.chatActivity);
        chatActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chat_with_bot.this, AllChat.class);
                startActivity(intent);
              /*  SQLiteDatabase database = dbHelper.getWritableDatabase();
                SQLiteDatabase database2 = dbChata.getWritableDatabase();
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                database2.delete(DBHelperChat.TABLE_CONTACTS, null, null);
                Toast.makeText(chat_with_bot.this, "бызы удалены", Toast.LENGTH_SHORT).show();
                */
            }
        });
        recycler_view = findViewById(R.id.recycler_view);
        text_send = findViewById(R.id.text_send);
        btn_otvet = findViewById(R.id.btn_otvet);
        btn_send = findViewById(R.id.btn_send);
        text_send.setMaxLines(5);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(linearLayoutManager);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_send.getText().toString().equals("")){
                    Toast.makeText(chat_with_bot.this, "Нельзя отправить пустое сообщение", Toast.LENGTH_SHORT).show();
                } else {
                    sendMessage();
                }
            }
        });

        ReadMSG();
    }

    private void sendMessage() {

        final SQLiteDatabase databaseChat = dbChata.getWritableDatabase();
        final ContentValues contentValuesChat = new ContentValues();
        contentValuesChat.put(DBHelperChat.KEY_NAME, MyMSG);
        contentValuesChat.put(DBHelperChat.KEY_VOPROS, text_send.getText().toString());
        databaseChat.insert(DBHelperChat.TABLE_CONTACTS, null, contentValuesChat);
        ReadMSG();

        ThisIsNow(text_send.getText().toString());

        if (messageB == false){
            final SQLiteDatabase database = dbHelper.getWritableDatabase();
            final ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_VOPROS, text_send.getText().toString());
            contentValues.put(DBHelper.KEY_NAME, MyMSG);
            text_send.setText("");

        contentValuesChat.put(DBHelperChat.KEY_NAME, "BOT");
        contentValuesChat.put(DBHelperChat.KEY_OTVET, "Как мне на это ответить?");
        databaseChat.insert(DBHelperChat.TABLE_CONTACTS, null, contentValuesChat);
        ReadMSG();
        btn_send.setVisibility(View.GONE);

        btn_otvet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentValues.put(DBHelper.KEY_OTVET, text_send.getText().toString());
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                btn_send.setVisibility(View.VISIBLE);
                contentValuesChat.put(DBHelperChat.KEY_NAME, MyMSG);
                contentValuesChat.put(DBHelperChat.KEY_VOPROS, text_send.getText().toString());
                databaseChat.insert(DBHelperChat.TABLE_CONTACTS, null, contentValuesChat);
                text_send.setText("");
                contentValuesChat.put(DBHelperChat.KEY_NAME, "BOT");
                contentValuesChat.put(DBHelperChat.KEY_OTVET, "Спасибо, запомню!");
                databaseChat.insert(DBHelperChat.TABLE_CONTACTS, null, contentValuesChat);
                ReadMSG();
            }
        });
             }
        messageB = false;
    }

    private void ReadMSG(){
        mChat = new ArrayList<>();
        mChat.clear();

        SQLiteDatabase database = new DBHelperChat(this).getReadableDatabase();

        Cursor cursor = database.query(DBHelperChat.TABLE_CONTACTS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DBHelperChat.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelperChat.KEY_NAME);
            int otvetIndex = cursor.getColumnIndex(DBHelperChat.KEY_OTVET);
            int voprosIndex = cursor.getColumnIndex(DBHelperChat.KEY_VOPROS);

            do {
                Chat piper = new Chat();
                piper.setId(cursor.getString(idIndex));
                piper.setName(cursor.getString(nameIndex));
                piper.setOtvet(cursor.getString(otvetIndex));
                piper.setVopros(cursor.getString(voprosIndex));
                mChat.add(piper);

            }while (cursor.moveToNext());
        }else
        {}
        cursor.close();

        adapterChat = new AdapterChat(chat_with_bot.this, mChat);
        recycler_view.setAdapter(adapterChat);
    }

    private void ThisIsNow(String MSG){

        SQLiteDatabase database = new DBHelper(this).getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {

            int otvetIndex = cursor.getColumnIndex(DBHelper.KEY_OTVET);
            int voprosIndex = cursor.getColumnIndex(DBHelper.KEY_VOPROS);

            do {
                Chat piper = new Chat();
                piper.setVopros(cursor.getString(voprosIndex));
                piper.setOtvet(cursor.getString(otvetIndex));
                if (MSG.equals(piper.getVopros())){
                    SQLiteDatabase databaseChat = dbChata.getWritableDatabase();
                    ContentValues contentValuesChat = new ContentValues();
                    contentValuesChat.put(DBHelperChat.KEY_NAME, "BOT");
                    contentValuesChat.put(DBHelperChat.KEY_OTVET, piper.getOtvet());
                    databaseChat.insert(DBHelperChat.TABLE_CONTACTS, null, contentValuesChat);
                    text_send.setText("");
                    ReadMSG();
                    messageB = true;
                } else {}

            } while (cursor.moveToNext());
        }else
        {}
        cursor.close();
    }
}
