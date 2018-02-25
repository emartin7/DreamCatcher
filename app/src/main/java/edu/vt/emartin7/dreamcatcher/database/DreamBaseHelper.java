package edu.vt.emartin7.dreamcatcher.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.vt.emartin7.dreamcatcher.database.DreamDbSchema.*;

/**
 * Created by ericmartin on 2/25/18.
 */

public class DreamBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "dreamBase.db";
    public DreamBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DreamTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                DreamTable.Cols.UUID + " text not null unique, " +
                DreamTable.Cols.TITLE + " text, " +
                DreamTable.Cols.DATE + " integer, " +
                DreamTable.Cols.DEFERRED + " integer, " +
                DreamTable.Cols.REALIZED + " integer)"
        );
        db.execSQL("create table " + DreamEntryTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                DreamEntryTable.Cols.TEXT + " text, " +
                DreamEntryTable.Cols.DATE + " integer, " +
                DreamEntryTable.Cols.KIND + " text, " +
                DreamEntryTable.Cols.UUID + " text not null, " +
                "foreign key (" + DreamEntryTable.Cols.UUID + ") references " +
                DreamTable.NAME + "(" + DreamTable.Cols.UUID + "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // leave blank for now
    }


}
