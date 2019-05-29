package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyMilestoneActivity extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;

    private long _id;

    private long scheme_id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Modify Record");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_milestone);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.milestoneName);
        descText = (EditText) findViewById(R.id.milestoneDesc);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        scheme_id = Long.parseLong(intent.getStringExtra("scheme_id"));
        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                dbManager.updateMilestone(_id, title, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.deleteMilestone(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ViewMilestoneActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("scheme_id", String.valueOf(scheme_id));
        startActivity(home_intent);
    }
}
