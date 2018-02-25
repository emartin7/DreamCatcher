package edu.vt.emartin7.dreamcatcher.model;

import android.graphics.PorterDuff;
import android.widget.Button;

import edu.vt.emartin7.dreamcatcher.R;

/**
 * Created by ericmartin on 2/18/18.
 */

public enum DreamEntryKind {

    REVEALED {
        @Override
        public int getBackgroundColor() {
            return R.color.revealedBackground;
        }

        @Override
        public int getTextColor() {
            return R.color.revealedText;
        }
    },
    DEFERRED {
        @Override
        public int getBackgroundColor() {
            return R.color.deferredBackground;
        }

        @Override
        public int getTextColor() {
            return R.color.deferredText;
        }
    },
    REALIZED {
        @Override
        public int getBackgroundColor() {
            return R.color.realizedBackground;
        }

        @Override
        public int getTextColor() {
            return R.color.realizedText;
        }
    },
    COMMENT {
        @Override
        public int getBackgroundColor() {
            return R.color.commentBackground;
        }

        @Override
        public int getTextColor() {
            return R.color.commentText;
        }

        @Override
        public String getTextFromEntry(DreamEntry dreamEntry) {
            return dreamEntry.getText() + "\n" + dreamEntry.getFormattedDate();
        }
    };

    // abstract method
    public abstract int getBackgroundColor();

    public abstract int getTextColor();

    public String getTextFromEntry(DreamEntry dreamEntry) {
        return dreamEntry.getText();
    };
}
