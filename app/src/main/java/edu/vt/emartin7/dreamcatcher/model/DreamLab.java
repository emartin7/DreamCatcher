package edu.vt.emartin7.dreamcatcher.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ericmartin on 2/11/18.
 */

public class DreamLab {

    private static DreamLab sDreamLab;

    private List<Dream> mDreams;

    private DreamLab(Context context) {
        mDreams = new ArrayList<>();

        Dream dream1 = new Dream();
        dream1.setTitle("Travel to Thailand");
        dream1.addComment("Read travel magazines glorifying it");
        dream1.addComment("Booked Travel");
        dream1.addComment("Had a blast");
        dream1.setRealized(true);
        mDreams.add(dream1);

        Dream dream2 = new Dream();
        dream2.setTitle("Eat an extra large pizza");
        dream2.addComment("Odd goal, but one that I held");
        dream2.addComment("Ordered extra large from Mellow Mushroom");
        dream2.setDeferred(true);
        dream2.addComment("Looked at the pizza and realized it was a terrible idea");
        mDreams.add(dream2);

        Dream dream3 = new Dream();
        dream3.setTitle("Break 80 in golf");
        dream3.addComment("Set practice plan to get better");
        dream3.addComment("Golfing better and better");
        dream3.setRealized(true);
        mDreams.add(dream3);


        for (int i = 0; i < 20; i++) {
            Dream dream = new Dream();
            dream.setTitle("Dream #" + i);


            dream.setRealized(i % 3 == 0);
            dream.setDeferred(i % 2 == 0);
            mDreams.add(dream);
        }
    }

    public static DreamLab get(Context context) {
        if (sDreamLab == null) {
            sDreamLab = new DreamLab(context);
        }
        return sDreamLab;
    }

    public List<Dream> getDreams() {
        return mDreams;
    }

    public Dream getDream(UUID id) {
        for (Dream dream : mDreams) {
            if (dream.getID().equals(id)) {
                return dream;
            }
        }
        return null;
    }
}

