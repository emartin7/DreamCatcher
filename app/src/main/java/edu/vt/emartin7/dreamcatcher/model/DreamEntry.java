package edu.vt.emartin7.dreamcatcher.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ericmartin on 2/18/18.
 */

public class DreamEntry {

    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

    private String mText;
    private Date mDate;
    private DreamEntryKind mDreamKind;

    public DreamEntry(String text, Date date, DreamEntryKind dreamKind) {
        mText = text;
        mDate = date;
        mDreamKind = dreamKind;
    }

    public String getText() {
        return mText;
    }

    public Date getDate() {
        return mDate;
    }

    public String getFormattedDate() {
        return df.format(mDate);
    }

    public DreamEntryKind getDreamKind() {
        return mDreamKind;
    }
}
