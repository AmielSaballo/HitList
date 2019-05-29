package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class AddMilestoneActivity extends Activity implements OnClickListener {

    private Button addBtn;
    private EditText milestoneName;
    private EditText milestoneDesc;

    private DBManager dbManager;

    Intent scheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milestone);

        scheme = getIntent();

        milestoneName = (EditText) findViewById(R.id.milestoneName);
        milestoneDesc = (EditText) findViewById(R.id.milestoneDesc);

        addBtn = (Button) findViewById(R.id.addMilestone);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMilestone:

                final String name = milestoneName.getText().toString();
                final String desc = milestoneDesc.getText().toString();
                final String scheme_id = scheme.getStringExtra("scheme_id");

                dbManager.insertMilestone(name, desc);

                Intent main = new Intent(AddMilestoneActivity.this, ViewMilestoneActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                main.putExtra("scheme_id",scheme_id);

                startActivity(main);
                break;
        }
    }
}
