package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // ডাটাবেসের নাম এবং ভার্সন
    private static final String DATABASE_NAME = "StudentDB.db";
    // ভার্সন ১ থেকে ২ করা হলো কারণ আমরা নতুন টেবিল যোগ করছি
    private static final int DATABASE_VERSION = 2;

    // ১. স্টুডেন্ট টেবিল (Login/Registration এর জন্য)
    private static final String TABLE_STUDENT = "student_table";
    public static final String S_COL_1 = "ID";
    public static final String S_COL_2 = "NAME";
    public static final String S_COL_3 = "STUDENT_ID";
    public static final String S_COL_4 = "EMAIL";
    public static final String S_COL_5 = "PASSWORD";

    // ২. কোর্স টেবিল (Course Info সেভ করার জন্য)
    private static final String TABLE_COURSE = "course_table";
    public static final String C_COL_1 = "ID";
    public static final String C_COL_2 = "COURSE_NAME";
    public static final String C_COL_3 = "COURSE_CODE";
    public static final String C_COL_4 = "CREDIT";
    public static final String C_COL_5 = "FACULTY";
    public static final String C_COL_6 = "SCHEDULE";
    public static final String C_COL_7 = "BOOKS";
    public static final String C_COL_8 = "DESCRIPTION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // স্টুডেন্ট টেবিল তৈরি
        db.execSQL("CREATE TABLE " + TABLE_STUDENT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, STUDENT_ID TEXT, EMAIL TEXT, PASSWORD TEXT)");

        // কোর্স টেবিল তৈরি
        db.execSQL("CREATE TABLE " + TABLE_COURSE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_NAME TEXT, COURSE_CODE TEXT, CREDIT TEXT, FACULTY TEXT, SCHEDULE TEXT, BOOKS TEXT, DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }

    // --- স্টুডেন্ট ডাটা মেথড ---
    public boolean insertData(String name, String studentId, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_COL_2, name);
        contentValues.put(S_COL_3, studentId);
        contentValues.put(S_COL_4, email);
        contentValues.put(S_COL_5, password);
        long result = db.insert(TABLE_STUDENT, null, contentValues);
        return result != -1;
    }

    // --- কোর্স ডাটা ইনসার্ট মেথড (আপনার নতুন ইনপুট পেজের জন্য) ---
    public boolean insertCourse(String name, String code, String credit, String faculty, String schedule, String books, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_COL_2, name);
        contentValues.put(C_COL_3, code);
        contentValues.put(C_COL_4, credit);
        contentValues.put(C_COL_5, faculty);
        contentValues.put(C_COL_6, schedule);
        contentValues.put(C_COL_7, books);
        contentValues.put(C_COL_8, desc);

        long result = db.insert(TABLE_COURSE, null, contentValues);
        return result != -1;
    }

    // সব কোর্স তুলে আনার জন্য মেথড (কোর্স লিস্টে দেখানোর জন্য)
    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_COURSE, null);
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
    }


    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student_table WHERE EMAIL=? AND PASSWORD=?", new String[]{email, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}