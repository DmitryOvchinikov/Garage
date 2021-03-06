package com.classy.common;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TLogDAO {

    @Insert
    void insertAll(TLog... tLogs);

    @Delete
    void delete(TLog tLog);

    @Update
    void updateUsers(TLog... tLogs);

    @Query("SELECT * FROM time_logs")
    List<TLog> getAll();

    @Query("SELECT * FROM time_logs where tag LIKE :tag")
    List<TLog> getAllByTag(String tag);

}
