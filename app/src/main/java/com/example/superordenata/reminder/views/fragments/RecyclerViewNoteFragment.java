package com.example.superordenata.reminder.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superordenata.reminder.R;
import com.example.superordenata.reminder.models.GlobalData;
import com.example.superordenata.reminder.views.adapters.RecyclerAdapterViewNote;

public class RecyclerViewNoteFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapterViewNote recyclerAdapterViewNote;

    public RecyclerViewNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_note, container, false);

        recyclerView = view.findViewById(R.id.rvMain);
        recyclerAdapterViewNote = new RecyclerAdapterViewNote(view.getContext(), GlobalData.dataMyNote, GlobalData.realm);
        recyclerView.setAdapter(recyclerAdapterViewNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    public void updateRecyclerFragment(){
        recyclerAdapterViewNote.notifyDataSetChanged();
    }
}
