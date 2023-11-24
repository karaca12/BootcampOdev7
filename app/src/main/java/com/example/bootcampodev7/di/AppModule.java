package com.example.bootcampodev7.di;

import android.content.Context;

import androidx.room.Room;

import com.example.bootcampodev7.data.repo.ToDoDaoRepository;
import com.example.bootcampodev7.room.DB;
import com.example.bootcampodev7.room.ToDosDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ToDoDaoRepository provideToDoDaoRepository(ToDosDao toDosDao) {
        return new ToDoDaoRepository(toDosDao);
    }

    @Provides
    @Singleton
    public ToDosDao provideToDosDao(@ApplicationContext Context context) {
        DB db = Room.databaseBuilder(context, DB.class, "bootcamp.sqlite")
                .createFromAsset("bootcamp.sqlite").build();
        return db.getToDosDao();
    }

}
