package com.example.bootcampodev7.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bootcampodev7.data.entity.ToDos;

@Database(entities = {ToDos.class},version = 1)
public abstract class DB extends RoomDatabase {
    public abstract ToDosDao getToDosDao();
}
