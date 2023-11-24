package com.example.bootcampodev7.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bootcampodev7.data.entity.ToDos;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ToDosDao {
    @Query("SELECT * FROM toDos")
    Single<List<ToDos>> loadToDos();

    @Insert
    Completable saveToDos(ToDos toDos);

    @Update
    Completable updateToDos(ToDos toDos);

    @Delete
    Completable deleteToDos(ToDos toDos);

    @Query("SELECT * FROM toDos WHERE name like '%' || :searchInput || '%'")
    Single<List<ToDos>> searchToDos(String searchInput);
}
