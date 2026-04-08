package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    LinearLayout courseContainer;
    DatabaseHelper db;
    ArrayList<CourseModel> courseList; // এটিই আপনার MongoDB স্টাইল "Array of Objects"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        courseContainer = findViewById(R.id.courseContainer); // XML-এ এই আইডিটি দিন
        db = new DatabaseHelper(this);
        courseList = new ArrayList<>();

        loadCoursesFromDB();
    }

    private void loadCoursesFromDB() {
        Cursor cursor = db.getAllCourses();

        while (cursor.moveToNext()) {
            // ডাটাবেস থেকে অবজেক্ট তৈরি করে লিস্টে পুশ (Push) করা
            courseList.add(new CourseModel(
                    cursor.getString(1), // Name
                    cursor.getString(2), // ID
                    cursor.getString(3), // Credit
                    cursor.getString(4), // Faculty
                    cursor.getString(5), // Schedule
                    cursor.getString(6), // Books
                    cursor.getString(7)  // Description
            ));
        }

        // এখন এই লিস্টের ওপর লুপ চালিয়ে কার্ড রেন্ডার করা (MongoDB .map() এর মতো)
        for (CourseModel course : courseList) {
            renderCourseCard(course);
        }
    }

    private void renderCourseCard(CourseModel course) {
        // একটি কার্ড ডিজাইন ইনফ্লেট করা
        View cardView = getLayoutInflater().inflate(R.layout.item_course_card, null);

        TextView tvTitle = cardView.findViewById(R.id.tvCourseTitle);
        TextView tvSubtitle = cardView.findViewById(R.id.tvCourseSubtitle);
        CardView card = cardView.findViewById(R.id.courseCard);

        tvTitle.setText(course.name);
        tvSubtitle.setText("ID: " + course.id + " | Credit: " + course.credit);

        // কার্ডে ক্লিক করলে ডিটেইলস পেজে ডাটা পাঠানো
        card.setOnClickListener(v -> {
            Intent intent = new Intent(CourseListActivity.this, CourseDetailsActivity.class);
            // মঙ্গোডিবির মতো পুরো অবজেক্টের প্রোপার্টি পাঠিয়ে দেওয়া
            intent.putExtra("name", course.name);
            intent.putExtra("id", course.id);
            intent.putExtra("credit", course.credit);
            intent.putExtra("faculty", course.faculty);
            intent.putExtra("schedule", course.schedule);
            intent.putExtra("books", course.books);
            intent.putExtra("desc", course.desc);
            startActivity(intent);
        });

        courseContainer.addView(cardView);
    }
}