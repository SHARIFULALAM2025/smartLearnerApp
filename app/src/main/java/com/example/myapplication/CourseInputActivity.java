package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CourseInputActivity extends AppCompatActivity {

    EditText etName, etId, etCredit, etFaculty, etSchedule, etBooks, etDesc;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_input);

        db = new DatabaseHelper(this);

        // ১. XML এর সাথে ID কানেক্ট করা
        etName = findViewById(R.id.etInputCourseName);
        etId = findViewById(R.id.etInputCourseId);
        etCredit = findViewById(R.id.etInputCredit);
        etFaculty = findViewById(R.id.etInputFaculty);
        etSchedule = findViewById(R.id.etInputSchedule);
        etBooks = findViewById(R.id.etInputBooks);
        etDesc = findViewById(R.id.etInputDescription);
        btnSave = findViewById(R.id.btnAddCourse);

        // ২. বাটন ক্লিকের কাজ
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCourse();
            }
        });
    }

    private void saveCourse() {
        String name = etName.getText().toString();
        String courseId = etId.getText().toString();
        String credit = etCredit.getText().toString();
        String faculty = etFaculty.getText().toString();
        String schedule = etSchedule.getText().toString();
        String books = etBooks.getText().toString();
        String desc = etDesc.getText().toString();

        // খালি ঘর চেক করা
        if (name.isEmpty() || courseId.isEmpty() || credit.isEmpty()) {
            Toast.makeText(this, "Please fill required fields (Name, ID, Credit)", Toast.LENGTH_SHORT).show();
            return;
        }

        // ডাটাবেসে সেভ করা
        boolean isInserted = db.insertCourse(name, courseId, credit, faculty, schedule, books, desc);

        if (isInserted) {
            Toast.makeText(this, "Course Saved Successfully!", Toast.LENGTH_LONG).show();
            finish(); // সেভ হওয়ার পর অটোমেটিক আগের পেজে ফিরে যাবে
        } else {
            Toast.makeText(this, "Failed to Save Data", Toast.LENGTH_SHORT).show();
        }
    }
}