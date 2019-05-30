package com.example.mobcomfinals;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListModifyMilestone extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    long _id;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_view);

        String scheme_id = getIntent().getStringExtra("scheme_id");
        _id = Long.parseLong(scheme_id);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchMilestones(_id);

        listView = (ListView) findViewById(R.id.milestoneList);
        listView.setEmptyView(findViewById(R.id.lblEmpty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_listview_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyMilestoneActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("scheme_id", String.valueOf(_id));

                startActivity(modify_intent);
            }
        });
    }
}
