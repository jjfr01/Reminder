package com.example.superordenata.reminder.views.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superordenata.reminder.R;
import com.example.superordenata.reminder.models.pojo.MyNote;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecyclerAdapterViewNote extends RecyclerView.Adapter<RecyclerAdapterViewNote.NoteViewHolder> {

    private Context context;
    private Realm realm;
    private RealmResults<MyNote> dataMyNote;

    public RecyclerAdapterViewNote(RealmResults<MyNote> dataMyNote) {
        this.dataMyNote = dataMyNote;
    }

    public RecyclerAdapterViewNote(Context context, RealmResults<MyNote> dataMyNote) {
        this.context = context;
        this.dataMyNote = dataMyNote;
    }

    public RecyclerAdapterViewNote(Context context, RealmResults<MyNote> dataMyNote, Realm realm) {
        this.context = context;
        this.dataMyNote = dataMyNote;
        this.realm = realm;
    }

    public RecyclerAdapterViewNote(Context context, RealmResults<MyNote> dataMyNote, FragmentActivity fragmentActivity, Bundle bundle) {
        this.context = context;
        this.dataMyNote = dataMyNote;
        /*this.fragmentActivity = fragmentActivity;
        this.bundle = bundle;*/
    }

    @Override
    public RecyclerAdapterViewNote.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);

        NoteViewHolder nch = new NoteViewHolder(itemView);

        return nch;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterViewNote.NoteViewHolder holder, int position) {
        MyNote myNote = dataMyNote.get(position);

        holder.bindNote(myNote, position);
    }

    @Override
    public int getItemCount() {
        return dataMyNote.size();
    }

    public void updateArray(RealmResults<MyNote> updatedArray) {
        dataMyNote = updatedArray;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        //Declara elementos de item_recycler
        private TextView tvTitle, tvDate;
        private ImageView ivColor;
        private ImageView alarmButton;

        public NoteViewHolder(View itemView) {
            super(itemView);
            //Cargamos los elementos como en los init (itemView.findViewById())
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.ivColor = itemView.findViewById(R.id.ivColor);
            this.alarmButton = itemView.findViewById(R.id.alarmButton);

        }

        public void bindNote(final MyNote s, final int position) {
            //Cargamos los datos en los label y demás y definimos OnClickListener
            this.tvTitle.setText(s.getTitle());
            Date date = s.getCreateDate();
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            this.tvDate.setText(fmt.format(date));
            this.displayAlarm(s);
        }

        private void displayAlarm(final MyNote s) {
            if (s.isHaveAlarm() == true) {
                alarmButton.setVisibility(View.VISIBLE);
                alarmButton.setEnabled(true);

                if (s.isAlarm() == true) {
                    alarmButton.setImageResource(R.drawable.ic_bell);
                } else {
                    alarmButton.setImageResource(R.drawable.ic_bell_off);
                }

                alarmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (s.isAlarm() == true) {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    s.setAlarm(false);
                                    alarmButton.setImageResource(R.drawable.ic_bell_off);
                                }
                            });
                        } else {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    s.setAlarm(true);
                                    alarmButton.setImageResource(R.drawable.ic_bell);
                                }
                            });
                        }
                    }
                });
            } else {
                alarmButton.setVisibility(View.INVISIBLE);
                alarmButton.setEnabled(false);
            }
        }
    }
}
