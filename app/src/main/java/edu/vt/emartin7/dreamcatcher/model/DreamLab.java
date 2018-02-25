package edu.vt.emartin7.dreamcatcher.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import edu.vt.emartin7.dreamcatcher.database.DreamBaseHelper;
import edu.vt.emartin7.dreamcatcher.database.DreamDbSchema.*;

/**
 * Created by ericmartin on 2/11/18.
 */

public class DreamLab {

    private static DreamLab sDreamLab;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private DreamLab(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new DreamBaseHelper(mContext).getWritableDatabase();

        Dream dream1 = new Dream();
        dream1.setTitle("Travel to Thailand");
        dream1.setDateRevealed(new Date());
        dream1.addComment("Read travel magazines glorifying it");
        dream1.addComment("Booked Travel");
        dream1.addComment("Had a blast");
        dream1.setRealized(true);
        addDream(dream1);

        Dream dream2 = new Dream();
        dream2.setTitle("Eat an extra large pizza");
        dream2.setDateRevealed(new Date());
        dream2.addComment("Odd goal, but one that I held");
        dream2.addComment("Ordered extra large from Mellow Mushroom");
        dream2.setDeferred(true);
        dream2.addComment("Looked at the pizza and realized it was a terrible idea");
        addDream(dream2);

        Dream dream3 = new Dream();
        dream3.setTitle("Break 80 in golf");
        dream3.setDateRevealed(new Date());
        dream3.addComment("Set practice plan to get better");
        dream3.addComment("Golfing better and better");
        dream3.setRealized(true);
        addDream(dream3);


        for (int i = 0; i < 20; i++) {
            Dream dream = new Dream();
            dream.setTitle("Dream #" + i);
            dream.setDateRevealed(new Date());


            dream.setRealized(i % 3 == 0);
            dream.setDeferred(i % 2 == 0);
            addDream(dream);
        }
    }

    public static DreamLab getInstance(Context context) {
        if (sDreamLab == null) {
            sDreamLab = new DreamLab(context);
        }
        return sDreamLab;
    }

    public Dream getDream(UUID id) {
        DreamCursorWrapper cursor = queryDreams(
                DreamTable.Cols.UUID + " = ?",
                new String[]{ id.toString() });
        try {
            if (cursor.getCount() == 0) { return null; }
            cursor.moveToFirst();
            Dream dream = cursor.getDream();
            List<DreamEntry> entries = DreamEntryLab.getInstance(mContext).getDreamEntries(dream);
            dream.setDreamEntries(entries);
            return dream;
        } finally {
            cursor.close();
        }
    }

    public void addDream(Dream dream) {
        ContentValues values = getDreamValues(dream);
        mDatabase.insert(DreamTable.NAME, null, values);
        for (DreamEntry entry : dream.getDreamEntries()) {
            DreamEntryLab.getInstance(mContext).addDreamEntry(dream, entry);
        }
    }

    public void updateDream(Dream dream) {
        String uuidString = dream.getID().toString();
        ContentValues values = getDreamValues(dream);
        mDatabase.update(DreamTable.NAME, values,
                DreamTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private DreamCursorWrapper queryDreams(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DreamTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DreamCursorWrapper(cursor);
    }

    public List<Dream> getDreams() {
        List<Dream> dreams = new ArrayList<>();
        DreamCursorWrapper cursor = queryDreams(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dreams.add(cursor.getDream());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return dreams;
    }

    private ContentValues getDreamValues(Dream dream) {
        ContentValues values = new ContentValues();
        values.put(DreamTable.Cols.UUID, dream.getID().toString());
        values.put(DreamTable.Cols.TITLE, dream.getTitle());
        values.put(DreamTable.Cols.DATE, dream.getDateRevealed().getTime());
        values.put(DreamTable.Cols.DEFERRED, dream.isDeferred() ? 1 : 0);
        values.put(DreamTable.Cols.REALIZED, dream.isRealized() ? 1 : 0);
        return values;
    }
}

