package com.dbs.task.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dbs.task.R;
import com.dbs.task.adapter.TaskItemClickListener;
import com.dbs.task.adapter.TaskListAdapter;
import com.dbs.task.response.TaskListResponse;
import com.dbs.task.viewModel.TaskListViewModel;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity implements TaskItemClickListener {

    private ArrayList<TaskListResponse> taskResponses = new ArrayList<>();
    TaskListViewModel taskListViewModel;
    RecyclerView recyclerView;

    private ProgressBar progressCircular;
    private LinearLayoutManager layoutManager;
    private TaskListAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        init();
        getTaskList();
    }

    public void onListItemClick(int position) {

        Toast.makeText(getApplicationContext(), taskResponses.get(position).getTitle(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(TaskListActivity.this, TaskDetailsActivity.class);
        i.putExtra("id", taskResponses.get(position).getId());
        i.putExtra("title_name", taskResponses.get(position).getTitle());
        i.putExtra("avatar_img", taskResponses.get(position).getAvatar());

        startActivity(i);


    }

    private void init() {
        progressCircular = findViewById(R.id.progress_circular);
        recyclerView = findViewById(R.id.rvTaskList);

        layoutManager = new LinearLayoutManager(TaskListActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        // adapter
        adapter = new TaskListAdapter(TaskListActivity.this, taskResponses,  this);
        recyclerView.setAdapter(adapter);


        taskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
    }



    private void getTaskList() {
        taskListViewModel.getTaskList().observe(this, taskList -> {

                    if(taskList != null){
                        taskResponses.addAll(taskList);
                        progressCircular.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(TaskListActivity.this);
                        alert.setTitle("TaskList");
                        alert.setMessage("Unable to fetch the task list at the moments");
                        alert.setPositiveButton("OK",null);
                        alert.show();
                        progressCircular.setVisibility(View.GONE);

                    }

                }
        );
    }
}
