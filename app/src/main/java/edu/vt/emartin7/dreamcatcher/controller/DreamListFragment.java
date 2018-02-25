package edu.vt.emartin7.dreamcatcher.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.vt.emartin7.dreamcatcher.R;
import edu.vt.emartin7.dreamcatcher.model.Dream;
import edu.vt.emartin7.dreamcatcher.model.DreamLab;
import edu.vt.emartin7.dreamcatcher.view.DreamAdapter;

/**
 * Created by ericmartin on 2/10/18.
 */

public class DreamListFragment extends Fragment {

    private RecyclerView mDreamRecyclerView;

    private DreamAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dream_list, container, false);

        mDreamRecyclerView = view.findViewById(R.id.dream_recycler_view);
        mDreamRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        DreamLab dreamLab = DreamLab.get(getActivity());
        List<Dream> dreams = dreamLab.getDreams();

        if (mAdapter == null) {
            mAdapter = new DreamAdapter(this.getActivity(), dreams);
            mDreamRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}


