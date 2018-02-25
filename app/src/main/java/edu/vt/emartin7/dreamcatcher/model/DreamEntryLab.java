package edu.vt.emartin7.dreamcatcher.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.vt.emartin7.dreamcatcher.database.DreamBaseHelper;
import edu.vt.emartin7.dreamcatcher.database.DreamDbSchema;

/**
 * Created by ericmartin on 2/25/18.
 */

public class DreamEntryLab {

    private static DreamEntryLab sDreamEntryLab;
    private SQLiteDatabase mDatabase;


    private DreamEntryLab(Context context) {
        mDatabase = new DreamBaseHelper(context).getWritableDatabase();
    }

    public static DreamEntryLab getInstance(Context context) {

        if (sDreamEntryLab == null) {
            sDreamEntryLab = new DreamEntryLab(context);
        }
        return sDreamEntryLab;
    }

    public void addDreamEntry(Dream dream, DreamEntry dreamEntry) {
        ContentValues values = getDreamEntryValues(dream, dreamEntry);
        mDatabase.insert(DreamDbSchema.DreamEntryTable.NAME, null, values);
    }

    public List<DreamEntry> getDreamEntries(Dream dream) {
        List<DreamEntry> dreams = new ArrayList<>();
        DreamEntryCursorWrapper cursor = queryDreams(DreamDbSchema.DreamEntryTable.Cols.UUID + " = ?",
                new String[]{dream.getID().toString()});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dreams.add(cursor.getDreamEntry());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return dreams;
    }

    private DreamEntryCursorWrapper queryDreams(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DreamDbSchema.DreamEntryTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DreamEntryCursorWrapper(cursor);
    }

    private ContentValues getDreamEntryValues(Dream dream, DreamEntry dreamEntry) {
        ContentValues values = new ContentValues();
        values.put(DreamDbSchema.DreamEntryTable.Cols.UUID, dream.getID().toString());
        values.put(DreamDbSchema.DreamEntryTable.Cols.TEXT, dreamEntry.getText());
        values.put(DreamDbSchema.DreamEntryTable.Cols.DATE, dreamEntry.getDate().getTime());
        values.put(DreamDbSchema.DreamEntryTable.Cols.KIND, dreamEntry.getDreamKind().toString());
        return values;
    }


    public void updateDreamEntries(Dream dream) {
        mDatabase.delete(DreamDbSchema.DreamEntryTable.NAME, DreamDbSchema.DreamEntryTable.Cols.UUID + " = ?",
                new String[]{dream.getID().toString()});

        for(DreamEntry entry: dream.getDreamEntries()) {
            addDreamEntry(dream, entry);
        }
    }
}
