package com.example.superordenata.reminder.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.superordenata.reminder.R;
import com.example.superordenata.reminder.models.GlobalData;
import com.example.superordenata.reminder.models.pojo.Note;
import com.example.superordenata.reminder.views.adapters.RecyclerAdapterViewNote;
import com.example.superordenata.reminder.views.fragments.RecyclerViewNoteFragment;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final int ID_FRAGMENT_MAIN = 1;

    private FloatingActionButton fab;
    private Toolbar toolbar;

    private int seletedFragment = 0;

    private void init() {
        GlobalData.realm = Realm.getDefaultInstance();

        GlobalData.data = GlobalData.realm.where(Note.class).findAll();

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        event();
    }

    private void event() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Note note;
                        if(GlobalData.data.size() > 0){
                            //Nada por ahora
                        } else {
                            note = new Note("Titulo1", "Nota1", "Rojo");
                            //Esto es provicional
                            realm.copyToRealmOrUpdate(note);
                            //Debo hacer que actualice el Recycler
                            sendDataFragment();
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
        defaultFragment();
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

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragMain, fragment)
                .commit();
    }

    public void defaultFragment() {
        Fragment fragment = new RecyclerViewNoteFragment();

        changeFragment(fragment);

        seletedFragment = ID_FRAGMENT_MAIN;
    }

    public void sendDataFragment() {
        switch (seletedFragment) {

            case ID_FRAGMENT_MAIN:

                RecyclerViewNoteFragment fragment = (RecyclerViewNoteFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
                fragment.updateRecyclerFragment();

                break;

        }
    }
}
