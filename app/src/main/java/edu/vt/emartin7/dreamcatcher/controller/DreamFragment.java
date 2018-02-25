package edu.vt.emartin7.dreamcatcher.controller;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.vt.emartin7.dreamcatcher.R;
import edu.vt.emartin7.dreamcatcher.model.Dream;
import edu.vt.emartin7.dreamcatcher.model.DreamEntry;
import edu.vt.emartin7.dreamcatcher.model.DreamEntryKind;
import edu.vt.emartin7.dreamcatcher.model.DreamLab;
import edu.vt.emartin7.dreamcatcher.model.DreamEntryLab;



import static android.view.View.GONE;
import static android.view.View.TEXT_ALIGNMENT_TEXT_START;
import static edu.vt.emartin7.dreamcatcher.model.DreamEntryKind.COMMENT;

/**
 * Created by ericmartin on 2/10/18.
 */

public class DreamFragment extends Fragment {

    private static final String ARG_DREAM_ID = "dream_id";

    //Declare Model Fields
    private Dream mDream;

    //Declare View Fields
    private EditText mTitle;
    //TODO see if this makes sense
    private TextView mDateRevealed;
    private CheckBox mIsDefferedCheckBox;
    private CheckBox mIsRealizedCheckBox;
    private Button mEntryButton0;
    private Button mEntryButton1;
    private Button mEntryButton2;
    private Button mEntryButton3;
    private Button mEntryButton4;

    private Map<Button, Integer> entryButtonMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID dreamId = (UUID) getArguments().getSerializable(ARG_DREAM_ID);
        mDream = DreamLab.getInstance(getActivity()).getDream(dreamId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dream, container, false);


        mTitle = view.findViewById(R.id.dream_title_id);

        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDream.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        mIsDefferedCheckBox = view.findViewById(R.id.deferred_id);
        mIsDefferedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mDream.setDeferred(isChecked);
            refreshView();
        });

        mIsRealizedCheckBox = view.findViewById(R.id.realized_id);
        mIsRealizedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mDream.setRealized(isChecked);
            refreshView();
        });

        mEntryButton0 = view.findViewById(R.id.dream_entry0);
        mEntryButton0.setEnabled(false);
        mEntryButton1 = view.findViewById(R.id.dream_entry1);
        mEntryButton1.setEnabled(false);
        mEntryButton2 = view.findViewById(R.id.dream_entry2);
        mEntryButton2.setEnabled(false);
        mEntryButton3 = view.findViewById(R.id.dream_entry3);
        mEntryButton3.setEnabled(false);
        mEntryButton4 = view.findViewById(R.id.dream_entry4);
        mEntryButton4.setEnabled(false);

        entryButtonMap = this.createMap();


        refreshView();

        return view;
    }

    private void refreshView() {
        if (mDream.getTitle() != null) {
            mTitle.setText(mDream.getTitle());
        }
        mIsDefferedCheckBox.setChecked(mDream.isDeferred());
        mIsRealizedCheckBox.setChecked(mDream.isRealized());

        refreshEntryButtons();
    }

    private void refreshEntryButtons() {
        int lastPosition = mDream.getDreamEntries().size() - 1;

        Button button;
        int position;
        DreamEntryKind entryKind;

        for (Map.Entry<Button, Integer> entry : entryButtonMap.entrySet()) {
            button = entry.getKey();
            position = entry.getValue();

            if (position > lastPosition) {
                button.setVisibility(GONE);

            } else {
                entry.getKey().setVisibility(View.VISIBLE);

                DreamEntry dreamEntry = mDream.getDreamEntries().get(position);
                entryKind = dreamEntry.getDreamKind();

                setStyle(entryKind.getBackgroundColor(), entryKind.getTextColor(), button);
                button.setText(entryKind.getTextFromEntry(dreamEntry));

                if(entryKind.equals(COMMENT)) {
                    button.setGravity(TEXT_ALIGNMENT_TEXT_START);
                }
            }
        }

    }

    private void setStyle(int backgroundColor, int textColor, Button button) {
        button.getBackground().setColorFilter(getResources().getColor(backgroundColor), PorterDuff.Mode.MULTIPLY);
        button.setTextColor(getResources().getColor(textColor));
    }

    private Map<Button, Integer> createMap() {
        Map<Button, Integer> result = new HashMap<>();
        result.put(mEntryButton0, 0);
        result.put(mEntryButton1, 1);
        result.put(mEntryButton2, 2);
        result.put(mEntryButton3, 3);
        result.put(mEntryButton4, 4);
        return result;
    }


    public static DreamFragment newInstance(UUID dreamId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DREAM_ID, dreamId);

        DreamFragment fragment = new DreamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        DreamLab.getInstance(getActivity()).updateDream(mDream);
        DreamEntryLab.getInstance(getActivity()).updateDreamEntries(mDream);
    }
}
