package com.example.myapplication;

import android.content.Intent; // এটি যোগ করতে হবে
import android.os.Bundle; // এটি যোগ করতে হবে
import android.os.Handler; // এটি যোগ করতে হবে
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);


                finish();
            }
        }, 3000);
    }
}