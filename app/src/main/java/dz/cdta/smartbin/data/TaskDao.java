package dz.cdta.smartbin.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dz.cdta.smartbin.model.Task;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getUpcomingTasks();

    @Query("SELECT * FROM tasks WHERE etat_tache = 2")
    LiveData<List<Task>> getHistoryTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Task> tasksList);

    @Delete
    void delete(Task task);
}
