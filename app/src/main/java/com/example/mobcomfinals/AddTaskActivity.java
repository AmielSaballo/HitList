package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends Activity implements View.OnClickListener {

    private Button addBtn;
    private EditText taskName;
    private EditText taskDesc;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = (EditText) findViewById(R.id.taskName);
        taskDesc = (EditText) findViewById(R.id.taskDesc);

        addBtn = (Button) findViewById(R.id.addTask);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTask:

                final String name = taskName.getText().toString();
                final String desc = taskDesc.getText().toString();
                final String milestone_id = getIntent().getStringExtra("milestone_id");

                dbManager.insertTask(name, desc, milestone_id);

                Intent main = new Intent(AddTaskActivity.this, ViewTaskActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                main.putExtra("milestone_id",milestone_id);

                startActivity(main);
                break;
        }
    }
}
