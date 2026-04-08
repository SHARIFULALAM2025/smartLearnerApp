package com.example.myapplication;

public class CourseModel {
    String name, id, credit, faculty, schedule, books, desc;

    // কনস্ট্রাক্টর
    public CourseModel(String name, String id, String credit, String faculty, String schedule, String books, String desc) {
        this.name = name;
        this.id = id;
        this.credit = credit;
        this.faculty = faculty;
        this.schedule = schedule;
        this.books = books;
        this.desc = desc;
    }
}