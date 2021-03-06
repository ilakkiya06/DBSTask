package com.dbs.task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dbs.task.R;

public class TaskEditActivity extends AppCompatActivity {

    AppCompatEditText edTitle,edDesc;
    AppCompatImageView imageViewCancel;

    String editTitle = "",editDesc = "";
    AppCompatButton buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);
        edTitle = findViewById(R.id.tvTitle);
        edDesc = findViewById(R.id.tvDescription);
        imageViewCancel = findViewById(R.id.imageview_cancel);
        buttonSave = findViewById(R.id.button);


        Intent intent = getIntent();

        editTitle = intent.getStringExtra("title");
        edTitle.setText(editTitle);

        editDesc = intent.getStringExtra("description");
        edDesc.setText(editDesc);

        int id = intent.getIntExtra("id", 1);

        imageViewCancel.setOnClickListener(v -> onBackPressed());

    }

    public void saveTask(View view){

        onBackPressed();

    }
}
