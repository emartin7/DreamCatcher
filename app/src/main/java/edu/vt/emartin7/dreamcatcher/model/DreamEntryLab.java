package edu.vt.emartin7.dreamcatcher.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edu.vt.emartin7.dreamcatcher.database.DreamBaseHelper;

/**
 * Created by ericmartin on 2/25/18.
 */

class DreamEntryLab {

    private static DreamEntryLab INSTANCE;
    private static Context mContext;
    private static SQLiteDatabase mDatabase;


    private DreamEntryLab() {
        INSTANCE = new DreamEntryLab();
    }

    static DreamEntryLab getInstance(Context context) {
        mContext = context;
        mDatabase = new DreamBaseHelper(mContext).getWritableDatabase();

        return INSTANCE;
    }

    public void addDreamEntry(DreamEntry entry, Dream dream) {
        //TODO
    }

    public List<DreamEntry> getDreamEntries(Dream dream) {
        //TODO
        return null;
    }
}
