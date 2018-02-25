package edu.vt.emartin7.dreamcatcher.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by ericmartin on 2/10/18.
 */

public class Dream {

    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

    private UUID mID;
    private String mTitle;
    private Date mDateRevealed;
    private boolean mIsDeferred;
    private boolean mIsRealized;
    private List<DreamEntry> mDreamEntries;

    public Dream() {
        this(UUID.randomUUID());
    }

    public Dream(UUID id) {
        mDreamEntries = new ArrayList<>();
        mID = id;
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFormattedDate() {
        return df.format(mDateRevealed);
    }

    public Date getDateRevealed() {
        return mDateRevealed;
    }

    public boolean isDeferred() {
        return mIsDeferred;
    }

    public boolean isRealized() {
        return mIsRealized;
    }

    public List<DreamEntry> getDreamEntries() {
        return mDreamEntries;
    }

    public void setDreamEntries(List<DreamEntry> dreamEntries) {
        mDreamEntries = dreamEntries;
    }

    public void setDateRevealed(Date dateRevealed) {
        mDateRevealed = dateRevealed;
        addDreamRevealed();
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public void setDeferred(boolean deferred) {
        if (deferred) {
            if (!mIsDeferred && !mIsRealized) {
                addDreamDeferred();
                mIsDeferred = true;
            }
        } else {
            removeDreamDeferred();
            mIsDeferred = false;
        }
    }

    public void setRealized(boolean realized) {
        if (realized) {
            if (!mIsRealized && !mIsDeferred) {
                addDreamRealized();
                mIsRealized = true;
            }
        } else {
            removeDreamRealized();
            mIsRealized = false;
        }
    }


    // helper methods

    public void addComment(String text) {
        DreamEntry dreamEntry = new DreamEntry(text, new Date(), DreamEntryKind.COMMENT);
        mDreamEntries.add(dreamEntry);
    }

    private void addDreamRealized() {
        DreamEntry dreamEntry = new DreamEntry("Dream Realized", new Date(), DreamEntryKind.REALIZED);
        mDreamEntries.add(dreamEntry);
    }

    private void addDreamDeferred() {
        DreamEntry dreamEntry = new DreamEntry("Dream Deferred", new Date(), DreamEntryKind.DEFERRED);
        mDreamEntries.add(dreamEntry);
    }

    private void addDreamRevealed() {
        DreamEntry dreamEntry = new DreamEntry("Dream Revealed", new Date(), DreamEntryKind.REVEALED);
        mDreamEntries.add(dreamEntry);
    }

    private void removeDreamDeferred() {
        for (Iterator<DreamEntry> iterator = this.getDreamEntries().iterator(); iterator.hasNext(); ) {
            DreamEntry dreamEntry = iterator.next();
            if (dreamEntry.getDreamKind().equals(DreamEntryKind.DEFERRED)) {
                iterator.remove();
            }
        }
    }

    private void removeDreamRealized() {
        for (Iterator<DreamEntry> iterator = this.getDreamEntries().iterator(); iterator.hasNext(); ) {
            DreamEntry dreamEntry = iterator.next();
            if (dreamEntry.getDreamKind().equals(DreamEntryKind.REALIZED)) {
                iterator.remove();
            }
        }
    }
}
