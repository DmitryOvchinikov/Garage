package com.classy.common;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_logs")
public class TLog {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "tag")
    public String tag = "";

    @ColumnInfo(name = "sessionStart")
    public long sessionStart = 0;

    @ColumnInfo(name = "sessionEnd")
    public long sessionEnd = 0;

    @ColumnInfo(name = "duration")
    public long duration = 0;

    public TLog() {
    }

    public TLog(String tag, long sessionStart, long sessionEnd, long duration) {
        this.tag = tag;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.duration = duration;
    }
}
