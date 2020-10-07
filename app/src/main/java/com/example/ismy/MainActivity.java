package com.example.ismy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(mStart, 1000);
    }

    private Runnable mStart = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, chat_with_bot.class);
            startActivity(intent);
            finish();
        }
    };
}
