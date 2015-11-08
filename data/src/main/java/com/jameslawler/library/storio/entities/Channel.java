package com.jameslawler.library.storio.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jameslawler.library.storio.tables.ChannelTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by james on 07/11/2015.
 */
@StorIOSQLiteType(table = ChannelTable.TABLE)
public class Channel {
    @Nullable
    @StorIOSQLiteColumn(name = ChannelTable.COLUMN_ID, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = ChannelTable.COLUMN_TITLE)
    String title;

    @NonNull
    @StorIOSQLiteColumn(name = ChannelTable.COLUMN_DESCRIPTION)
    String description;

    Channel() {
    }

    public Channel(@Nullable Long id, @NonNull String title, @NonNull String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public String title() {
        return title;
    }

    @NonNull
    public String description() {
        return description;
    }

    // BTW, you can use AutoValue/AutoParcel to get immutability and code generation for free
    // Check our tests, we have examples!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (id != null ? !id.equals(channel.id) : channel.id != null) return false;
        if (!title.equals(channel.title)) return false;
        return description.equals(channel.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
