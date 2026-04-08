package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    // ভেরিয়েবল ডিক্লেয়ার করা
    CardView cardProfile, cardCGPA, cardCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // ১. সব আইডি আগে কানেক্ট করতে হবে (এটি ভুলে গেলে অ্যাপ ক্রাশ করবে)
        cardProfile = findViewById(R.id.cardProfile);
        cardCGPA = findViewById(R.id.cardCGPA);
        cardCourse = findViewById(R.id.cardCourse); // এই লাইনটি যোগ করা হয়েছে

        // ২. Profile বাটনে ক্লিক লজিক
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // ৩. CGPA বাটনে ক্লিক লজিক
        cardCGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // নিশ্চিত করুন আপনার Manifest-এ এই নামই আছে
                Intent intent = new Intent(DashboardActivity.this, CgpaCalculatorActivity.class);
                startActivity(intent);
            }
        });
        cardCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // নিশ্চিত করুন আপনার Manifest-এ এই নামই আছে
                Intent intent = new Intent(DashboardActivity.this, CourseListActivity.class);
                startActivity(intent);
            }
        });


    }
}