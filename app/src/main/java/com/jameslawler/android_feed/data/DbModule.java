package com.jameslawler.android_feed.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.jameslawler.library.storio.entities.Channel;
import com.jameslawler.library.storio.tables.ChannelTable;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;

public class DbModule {

    @NonNull
    public static StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Channel.class, SQLiteTypeMapping.<Channel>builder()
                        .putResolver(new DefaultPutResolver<Channel>() {
                            @NonNull
                            @Override
                            protected InsertQuery mapToInsertQuery(@NonNull Channel object) {
                                return InsertQuery.builder()
                                    .table(ChannelTable.TABLE)
                                    .build();
                            }

                            @NonNull
                            @Override
                            protected UpdateQuery mapToUpdateQuery(@NonNull Channel object) {
                                return UpdateQuery.builder()
                                        .table(ChannelTable.TABLE)
                                        .where(ChannelTable.COLUMN_ID + " = ?")
                                        .whereArgs(ChannelTable.COLUMN_ID)
                                        .build();
                            }

                            @NonNull
                            @Override
                            protected ContentValues mapToContentValues(@NonNull Channel object) {
                                return null;
                            }
                        })
                        .getResolver(new DefaultGetResolver<Channel>() {
                            @NonNull
                            @Override
                            public Channel mapFromCursor(@NonNull Cursor cursor) {
                                return new Channel(
                                        cursor.getLong(cursor.getColumnIndex(ChannelTable.COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(ChannelTable.COLUMN_TITLE)),
                                        cursor.getString(cursor.getColumnIndex(ChannelTable.COLUMN_DESCRIPTION))
                                );
                            }
                        })
                        .deleteResolver(new DeleteResolver<Channel>() {
                            @NonNull
                            @Override
                            public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull Channel object) {
                                return null;
                            }
                        })
                        .build())
                .build();
    }

    @NonNull
    public static SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
