package com.example.superordenata.reminder.models.pojo;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class MyNote extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String note;
    private String color;
    @Required
    private Date createDate;
    private Date alarmDate;
    private boolean alarm;
    private boolean haveAlarm;
    private boolean isList;
    private RealmList<String> list; //ArrayList normales no sirven, deben ser RealmList<>
    private RealmList<String> groups;
    private boolean isDraft;
    // TODO Averiguar como hacer los mapas en la nota
    private boolean haveMap;

    public MyNote() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
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

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public RealmList<String> getList() {
        return list;
    }

    public void setList(RealmList<String> list) {
        this.list = list;
    }

    public RealmList<String> getGroups() {
        return groups;
    }

    public void setGroups(RealmList<String> groups) {
        this.groups = groups;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }
}
