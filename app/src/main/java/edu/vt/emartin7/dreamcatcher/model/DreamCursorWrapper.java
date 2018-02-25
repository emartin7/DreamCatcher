package edu.vt.emartin7.dreamcatcher.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edu.vt.emartin7.dreamcatcher.database.DreamDbSchema.*;

/**
 * Created by ericmartin on 2/25/18.
 */

public class DreamCursorWrapper extends CursorWrapper {
    public DreamCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dream getDream() {
        String uuidString = getString(getColumnIndex(DreamTable.Cols.UUID));
        String title = getString(getColumnIndex(DreamTable.Cols.TITLE));
        long date = getLong(getColumnIndex(DreamTable.Cols.DATE));
        int isDeferred = getInt(getColumnIndex(DreamTable.Cols.DEFERRED));
        int isRealized = getInt(getColumnIndex(DreamTable.Cols.REALIZED));
        Dream dream = new Dream(UUID.fromString(uuidString));
        dream.setTitle(title);
        dream.setDateRevealed(new Date(date));
        dream.setDeferred(isDeferred != 0);
        dream.setRealized(isRealized != 0);
        return dream;
    }
}