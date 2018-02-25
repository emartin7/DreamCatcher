package edu.vt.emartin7.dreamcatcher.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class DreamActivity extends SingleFragmentActivity {

    private static final String EXTRA_DREAM_ID = "dream_id";

    public static Intent newIntent(Context packageContext, UUID dreamId) {
        Intent intent = new Intent(packageContext, DreamActivity.class);
        intent.putExtra(EXTRA_DREAM_ID, dreamId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID dreamId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DREAM_ID);
        return DreamFragment.newInstance(dreamId);
    }
}
