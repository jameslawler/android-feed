package com.jameslawler.library.storio.tables;

import android.support.annotation.NonNull;

public class ChannelTable {

    @NonNull
    public static final String TABLE = "channel";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_TITLE = "title";

    @NonNull
    public static final String COLUMN_DESCRIPTION = "description";

    private ChannelTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_DESCRIPTION + " TEXT NOT NULL"
                + ");";
    }
}
