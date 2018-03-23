package com.example.superordenata.reminder.models.pojo;

import com.example.superordenata.reminder.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class MyNote extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String note;
    @Required
    private Date date;
    @Required
    private String color;
    private boolean alarm;

    public MyNote() {
    }

    public MyNote(String title, String note, String color) {
        this.id = MyApplication.MyNoteID.incrementAndGet();
        this.title = title;
        this.note = note;
        this.date = new Date();
        this.color = color;
        this.alarm = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
}
