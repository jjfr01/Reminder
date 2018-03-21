package com.example.superordenata.reminder.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.superordenata.reminder.R;
import com.example.superordenata.reminder.models.pojo.Note;
import com.example.superordenata.reminder.views.adapters.RecyclerAdapterViewNote;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Note> data;

    private RecyclerAdapterViewNote recyclerAdapterViewNote;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private void init() {
        realm = Realm.getDefaultInstance();

        data = realm.where(Note.class).findAll();

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        event();
    }

    private void event() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Note note;
                        if(data.size() > 0){
                            //Nada por ahora
                        } else {
                            note = new Note("Titulo1", "Nota1", "Rojo");
                            //Esto es provicional
                            realm.copyToRealmOrUpdate(note);
                            recyclerAdapterViewNote.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView = findViewById(R.id.rvMain);
        recyclerAdapterViewNote = new RecyclerAdapterViewNote(this, data, realm);
        recyclerView.setAdapter(recyclerAdapterViewNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
