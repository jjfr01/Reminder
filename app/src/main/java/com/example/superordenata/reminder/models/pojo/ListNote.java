package com.example.superordenata.reminder.models.pojo;

import com.example.superordenata.reminder.app.MyApplication;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ListNote extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private RealmList<String> list; //ArrayList normales no sirven, deben ser RealmList<>
    @Required
    private Date date;
    private String color;
    private boolean alarm;
    private boolean haveAlarm;

    public ListNote() {
    }

    public ListNote(String title, RealmList<String> list, String color) {
        this.id = MyApplication.ListNoteID.incrementAndGet();
        this.title = title;
        this.list = list;
        this.date = new Date();
        this.color = color;
        this.alarm = false;
        this.haveAlarm = false;
    }

    public ListNote(String title, RealmList<String> list, String color, Date date) {
        this.id = MyApplication.ListNoteID.incrementAndGet();
        this.title = title;
        this.list = list;
        this.date = date;
        this.color = color;
        this.alarm = false;
        this.haveAlarm = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<String> getList() {
        return list;
    }

    public void setList(RealmList<String> list) {
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isHaveAlarm() {
        return haveAlarm;
    }

    public void setHaveAlarm(boolean haveAlarm) {
        this.haveAlarm = haveAlarm;
    }
}
