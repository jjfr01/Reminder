package com.example.superordenata.reminder.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superordenata.reminder.R;

public class MyNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = getIntent();
        resultIntent.putExtra("OK", "SI");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        //super.onBackPressed();
    }
}
