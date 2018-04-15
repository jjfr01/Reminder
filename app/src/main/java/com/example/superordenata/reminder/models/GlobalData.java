package com.example.superordenata.reminder.models;


import com.example.superordenata.reminder.models.pojo.ListNote;
import com.example.superordenata.reminder.models.pojo.MyNote;

import io.realm.Realm;
import io.realm.RealmResults;

public class GlobalData {

    public static Realm realm;
    public static RealmResults<MyNote> dataMyNote;
    public static RealmResults<ListNote> dataListNote;

}
