package edu.vt.emartin7.dreamcatcher.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

import edu.vt.emartin7.dreamcatcher.database.DreamDbSchema;

/**
 * Created by ericmartin on 2/25/18.
 */

public class DreamEntryCursorWrapper extends CursorWrapper {
    public DreamEntryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DreamEntry getDreamEntry() {
        String text = getString(getColumnIndex(DreamDbSchema.DreamEntryTable.Cols.TEXT));
        long date = getLong(getColumnIndex(DreamDbSchema.DreamEntryTable.Cols.DATE));
        String kind = getString(getColumnIndex(DreamDbSchema.DreamEntryTable.Cols.KIND));
        return new DreamEntry(text, new Date(date), DreamEntryKind.valueOf(kind));
    }
}
