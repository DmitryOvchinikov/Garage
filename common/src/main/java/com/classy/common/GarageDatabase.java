package com.classy.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities ={TLog.class})
abstract class GarageDatabase extends RoomDatabase {
    public abstract TLogDAO tLogDAO();
}
