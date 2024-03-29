package com.example.mobcomfinals;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewMilestoneActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    long _id;

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

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);

                String id = idTextView.getText().toString();

                Intent seeTasks = new Intent(getApplicationContext(), ViewTaskActivity.class);
                seeTasks.putExtra("milestone_id", id);

                startActivity(seeTasks);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_add:
                Intent add_mem = new Intent(this, AddMilestoneActivity.class);
                add_mem.putExtra("scheme_id", String.valueOf(_id));
                startActivity(add_mem);
                break;
            case R.id.Edit:
                Intent edit_mem = new Intent(this, ListModifyMilestone.class);
                edit_mem.putExtra("scheme_id", String.valueOf(_id));
                startActivity(edit_mem);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
