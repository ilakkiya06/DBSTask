package com.dbs.task.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dbs.task.repository.TaskDetailsRepository;
import com.dbs.task.response.TaskDetailsResponse;


public class TaskDetailsViewModel extends AndroidViewModel {

    private TaskDetailsRepository taskDetailsRepository;


    private LiveData<TaskDetailsResponse> taskDetailsResponseLiveData;



    public TaskDetailsViewModel(@NonNull Application application, String param) {
        super(application);

        taskDetailsRepository = new TaskDetailsRepository(application);
        this.taskDetailsResponseLiveData = taskDetailsRepository.getTaskDetails(Integer.parseInt(param));
    }


    public LiveData<TaskDetailsResponse> getTaskDetails() {

        return taskDetailsResponseLiveData;
    }


}
