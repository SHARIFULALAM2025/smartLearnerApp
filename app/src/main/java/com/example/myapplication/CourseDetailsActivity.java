package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailsActivity extends AppCompatActivity {

    TextView tvName, tvId, tvCredit, tvFaculty, tvSchedule, tvBooks, tvDesc;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        // ১. আইডি কানেক্ট করা
        tvName = findViewById(R.id.tvDetailName);
        tvId = findViewById(R.id.tvDetailId);
        tvCredit = findViewById(R.id.tvDetailCredit);
        tvFaculty = findViewById(R.id.tvDetailFaculty);
        tvSchedule = findViewById(R.id.tvDetailSchedule);
        tvBooks = findViewById(R.id.tvDetailBooks);
        tvDesc = findViewById(R.id.tvDetailDesc);
        btnBack = findViewById(R.id.btnBack);

        // ২. ডাটা রিসিভ করা (ইন্টেন্ট থেকে)
        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        String credit = getIntent().getStringExtra("credit");
        String faculty = getIntent().getStringExtra("faculty");
        String schedule = getIntent().getStringExtra("schedule");
        String books = getIntent().getStringExtra("books");
        String desc = getIntent().getStringExtra("desc");

        // ৩. টেক্সট ভিউতে ডাটা সেট করা
        tvName.setText(name);
        tvId.setText("Course ID: " + id);
        tvCredit.setText("Credits: " + credit);
        tvFaculty.setText("Faculty: " + faculty);
        tvSchedule.setText("Schedule: " + schedule);
        tvBooks.setText("References: " + books);
        tvDesc.setText("Description: \n" + desc);

        // ব্যাক বাটন কাজ
        btnBack.setOnClickListener(v -> finish());
    }
}