package com.example.superordenata.reminder.models;


import com.example.superordenata.reminder.models.pojo.Note;

import io.realm.Realm;
import io.realm.RealmResults;

public class GlobalData {

    public static Realm realm;
    public static RealmResults<Note> data;

}
