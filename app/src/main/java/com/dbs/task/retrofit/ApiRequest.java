package com.dbs.task.retrofit;


import com.dbs.task.response.TaskListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiRequest {

    @GET("/article")
    Call<List<TaskListResponse>> getTaskList(

    );



}
