package com.dbs.task.repository;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dbs.task.response.TaskListResponse;
import com.dbs.task.retrofit.ApiRequest;
import com.dbs.task.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskRepository {

    private static final String TAG = TaskRepository.class.getSimpleName();

    private ApiRequest apiRequest;
    Application application;



    public TaskRepository(Application application) {

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        this.application = application;

    }


    public LiveData<List<TaskListResponse>> getTaskList() {

        final MutableLiveData<List<TaskListResponse>> data = new MutableLiveData<>();
        apiRequest.getTaskList().enqueue(new Callback<List<TaskListResponse>>() {
            @Override
            public void onResponse(Call<List<TaskListResponse>> call, Response<List<TaskListResponse>> response) {

                Log.d(TAG, "on Response:: " + response);

                if (response.body() != null) {

                    data.setValue(response.body());
                 //   TaskListModelRepository taskListModelRepository = new TaskListModelRepository(application);



                    // save the local db

                    for(int i=0;i<response.body().size();i++){
                      //  taskListModelRepository.insert(convertTaskList(response.body().get(i)));
                      //  taskListModelRepository.response.body().get(i);

                        data.setValue(response.body());

                    }


                }
                else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TaskListResponse>> call, Throwable t) {

                Log.e(TAG, "network_error ::" + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

  /*  private TaskListModel convertTaskList(TaskListResponse taskListResponse){

        TaskListModel taskListModel = new TaskListModel(taskListResponse.getId(),taskListResponse.getTitle(),
                taskListResponse.getLastUpdate(),taskListResponse.getShortDescription(),taskListResponse.getAvatar());
        return taskListModel;
    }
*/
}
