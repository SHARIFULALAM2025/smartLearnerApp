package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CgpaCalculatorActivity extends AppCompatActivity {

    EditText etGpa1, etCredit1;
    Button btnCalculate, btnAddCourse;
    TextView tvResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        // ১. আইডি কানেক্ট করা
        etGpa1 = findViewById(R.id.etGpa1);
        etCredit1 = findViewById(R.id.etCredit1);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        btnAddCourse = findViewById(R.id.btnAddCourse);

        // ২. CGPA ক্যালকুলেশন বাটনের কাজ
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });

        // ৩. অ্যাড কোর্স বাটনের কাজ (এটি এখন onCreate এর ভেতরে)
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // নিশ্চিত করুন আপনার ইনপুট পেজের ক্লাসের নাম CourseInputActivity
                Intent intent = new Intent(CgpaCalculatorActivity.this, CourseInputActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateCGPA() {
        try {
            String gpaStr = etGpa1.getText().toString();
            String creditStr = etCredit1.getText().toString();

            if (gpaStr.isEmpty() || creditStr.isEmpty()) {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double gpa = Double.parseDouble(gpaStr);
            double credit = Double.parseDouble(creditStr);

            // সিম্পল ক্যালকুলেশন
            double cgpa = (gpa * credit) / credit;

            tvResult.setText("Your CGPA: " + String.format("%.2f", cgpa));
        } catch (Exception e) {
            Toast.makeText(this, "Error: Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }
}