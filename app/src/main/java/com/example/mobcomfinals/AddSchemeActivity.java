package com.example.mobcomfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSchemeActivity extends Activity implements OnClickListener {

    private Button addBtn;
    private EditText schemeName;
    private EditText schemeDesc;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheme);


        schemeName = (EditText) findViewById(R.id.schemeName);
        schemeDesc = (EditText) findViewById(R.id.schemeDesc);

        addBtn = (Button) findViewById(R.id.addScheme);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addScheme:
                Scheme scheme = new Scheme(schemeName.getText().toString(),
                        schemeDesc.getText().toString());

                if (scheme.getDesc() == null) {
                    scheme.setDesc("");
                }

//                Toast.makeText(this,
//                        scheme.getTitle() + " " + scheme.getDesc(),
//                        Toast.LENGTH_LONG).show();

                long scheme_id = db.createScheme(scheme);

                Intent main = new Intent(this, ViewHitListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
