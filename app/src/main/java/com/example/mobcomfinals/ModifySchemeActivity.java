package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifySchemeActivity extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;

    private long _id;
    Scheme scheme;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Modify Record");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_scheme);

        titleText = (EditText) findViewById(R.id.txtSchemeName);
        descText = (EditText) findViewById(R.id.txtSchemeDesc);

        updateBtn = (Button) findViewById(R.id.btnUpdate);
        deleteBtn = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        scheme = (Scheme) intent.getSerializableExtra("scheme");

        scheme = new Scheme();

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                scheme.setTitle(titleText.getText().toString());
                scheme.setDesc(descText.getText().toString());

                db.updateScheme(scheme);

                this.returnHome();
                break;

            case R.id.btnDelete:
                _id = Long.parseLong(String.valueOf(scheme.getId()));
                db.deleteScheme(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ViewHitListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
