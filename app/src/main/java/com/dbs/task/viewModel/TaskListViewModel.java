package com.dbs.task.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dbs.task.repository.TaskRepository;
import com.dbs.task.response.TaskListResponse;


import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;


    private LiveData<List<TaskListResponse>> taskListLiveData;

    public TaskListViewModel(@NonNull Application application) {
        super(application);

        taskRepository = new TaskRepository(application);
        this.taskListLiveData = taskRepository.getTaskList();
    }


    public LiveData<List<TaskListResponse>> getTaskList() {

        return taskListLiveData;
    }




}
