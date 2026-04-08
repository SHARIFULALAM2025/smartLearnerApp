package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvId, tvEmail;
    private Button btnBack;
    private DatabaseHelper myDb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ID কানেক্ট করা
        tvName = findViewById(R.id.tvProfileName);
        tvId = findViewById(R.id.tvProfileId);
        tvEmail = findViewById(R.id.tvProfileEmail);
        btnBack = findViewById(R.id.btnBack);

        myDb = new DatabaseHelper(this);

        // ডাটাবেস থেকে তথ্য লোড করা
        showUserData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // ড্যাশবোর্ডে ফিরে যাওয়া
            }
        });
    }

    private void showUserData() {
        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(this, "No profile data found in SQL!", Toast.LENGTH_SHORT).show();
            return;
        }

        // সবশেষ এন্ট্রি করা ডাটাটি নেয়ার জন্য (কারণ ইউজার এইমাত্র রেজিস্টার করেছে)
        if (res.moveToLast()) {
            // DatabaseHelper এর কলাম ইনডেক্স অনুযায়ী (0=ID, 1=Name, 2=StudentId, 3=Email)
            String name = res.getString(1);
            String studentId = res.getString(2);
            String email = res.getString(3);

            tvName.setText(name);
            tvId.setText("Student ID: " + studentId);
            tvEmail.setText("Email: " + email);
        }
        res.close();
    }
}
