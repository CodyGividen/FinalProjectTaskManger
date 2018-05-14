package com.example.codygividen.finalprojecttaskmanger;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(version = 1, entities = TaskDefault.class)
@TypeConverters(DataConverter.class)
public abstract class TaskDatabase extends RoomDatabase{


    public abstract TaskDao taskDao();
}
