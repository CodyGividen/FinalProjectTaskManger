package com.example.codygividen.finalprojecttaskmanger;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;


@Dao
public interface TaskDao {



    //Allow us to get all video games
    @Query("SELECT * FROM taskdefault")
    List<TaskDefault> getTaskDefault();

    //Allows us to add a single game to the list
    @Insert
    void addTaskDefault(TaskDefault taskDefault);

    //Allows us to update the values of an existing game
    @Update
    void updateTaskDefault(TaskDefault taskDefault);

    //Allows us to delete a game from the library
    @Delete
    void deleteTaskDefault(TaskDefault taskDefault);


}
