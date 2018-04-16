package com.example.superordenata.reminder.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.superordenata.reminder.R;

public class SelectMyNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_my_note);

        Button btn = findViewById(R.id.addMyNote);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectMyNoteActivity.this, MyNoteActivity.class);
                startActivityForResult(intent, getIntent().getIntExtra("codeIntent", 0));
                /*Intent resultIntent = getIntent();
                resultIntent.putExtra("OK", "SI");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();*/
            }
        });


    }
}
