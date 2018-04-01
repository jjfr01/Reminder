package com.example.superordenata.reminder.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superordenata.reminder.R;
import com.example.superordenata.reminder.models.GlobalData;
import com.example.superordenata.reminder.models.pojo.MyNote;
import com.example.superordenata.reminder.views.adapters.MyPagerAdapter;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static final int ID_FRAGMENT_MAIN = 1;

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;

    private int seletedFragment = ID_FRAGMENT_MAIN;

    private void init() {
        GlobalData.realm = Realm.getDefaultInstance();

        GlobalData.data = GlobalData.realm.where(MyNote.class).findAll();

        fab = findViewById(R.id.fab);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);

        //Según Material Desing, deja los icones por encima del nombre
        /*tabLayout.addTab(tabLayout.newTab().setText("Notas"));
        tabLayout.addTab(tabLayout.newTab().setText("Grupos"));
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_single);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder);*/

        loadTabs();

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        viewPager = findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                switch (position){
                    case 0:
                        seletedFragment = ID_FRAGMENT_MAIN;
                        break;
                    case 1:
                        seletedFragment = 2;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Toast.makeText(MainActivity.this, "Unselected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Toast.makeText(MainActivity.this, "Reselected", Toast.LENGTH_SHORT).show();
            }
        });

        event();
    }

    private void event() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                GlobalData.realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        MyNote myNote;
                        if(GlobalData.data.size() > 0){
                            //Nada por ahora
                        } else {
                            myNote = new MyNote("Titulo1", "Nota1", "Rojo");
                            //Esto es provicional
                            realm.copyToRealmOrUpdate(myNote);
                            //Debo hacer que actualice el Recycler
                            //sendDataFragment();
                            viewPager.setAdapter(myPagerAdapter);

                            if(seletedFragment != ID_FRAGMENT_MAIN) {
                                tabLayout.getTabAt(0).select();
                                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
                            }
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

    private void loadTabs(){
        LinearLayout tabLinearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabContent = tabLinearLayout.findViewById(R.id.tabContent);
        tabContent.setText("TODOS");
        tabContent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_single, 0, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabContent));

        tabLinearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabContent = tabLinearLayout.findViewById(R.id.tabContent);
        tabContent.setText("GRUPOS");
        tabContent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder, 0, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tabContent));
    }
}
