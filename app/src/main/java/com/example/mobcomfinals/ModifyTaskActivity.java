package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyTaskActivity extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn, markAsDone;
    private EditText descText;

    private long _id;

    private long milestone_id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Modify Record");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_task);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.taskName);
        descText = (EditText) findViewById(R.id.taskDesc);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        markAsDone = (Button) findViewById(R.id.done_btn);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        milestone_id = Long.parseLong(intent.getStringExtra("milestone_id"));
        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        markAsDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                dbManager.updateTask(_id, title, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.deleteTask(_id);
                this.returnHome();
                break;
            case R.id.done_btn:

                dbManager.taskDone(_id ,Color.rgb( 	117, 209, 140));
//                Toast.makeText(this, String.valueOf(Color.rgb(192,57,43)), Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ViewTaskActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("milestone_id", String.valueOf(milestone_id));
        startActivity(home_intent);
    }
}
