package edu.vt.emartin7.dreamcatcher.controller;

import android.support.v4.app.Fragment;

public class DreamListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new DreamListFragment();
    }
}
