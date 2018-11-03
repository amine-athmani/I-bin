package dz.cdta.smartbin;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import dz.cdta.smartbin.data.AppDatabase;
import dz.cdta.smartbin.data.TaskDao;
import dz.cdta.smartbin.model.Task;

public class TaskViewModel extends AndroidViewModel {

    private TaskDao taskDao;
    private LiveData<List<Task>> tasksList;
    private int taskState;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.taskState = taskState;
        taskDao = AppDatabase.getAppDatabase(application.getApplicationContext()).taskDao();
        tasksList = taskState == 1 ? taskDao.getUpcomingTasks() : taskDao.getHistoryTasks();
    }

    public LiveData<List<Task>> getTasksList(int index) {

        if (tasksList==null) {
            tasksList = index == 1 ? taskDao.getUpcomingTasks() : taskDao.getHistoryTasks();
        }
        return tasksList;
    }

}
