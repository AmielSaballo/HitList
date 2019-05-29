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

public class ViewTaskActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_view_task);

        String milestone_id = getIntent().getStringExtra("milestone_id");
        _id = Long.parseLong(milestone_id);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchTasks(_id);

        listView = (ListView) findViewById(R.id.taskList);
        listView.setEmptyView(findViewById(R.id.lblEmpty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_listview_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
//                TextView idTextView = (TextView) view.findViewById(R.id.id);
//                TextView titleTextView = (TextView) view.findViewById(R.id.title);
//                TextView descTextView = (TextView) view.findViewById(R.id.desc);
//
//                String id = idTextView.getText().toString();
//                String title = titleTextView.getText().toString();
//                String desc = descTextView.getText().toString();
//
//                Intent modify_intent = new Intent(getApplicationContext(), ModifySchemeActivity.class);
//                modify_intent.putExtra("title", title);
//                modify_intent.putExtra("desc", desc);
//                modify_intent.putExtra("id", id);
//
//                Intent seeTasks = new Intent(getApplicationContext(), ViewTaskActivity.class);
//                if (id !=null) {
//                    seeTasks.putExtra("scheme_id", id);
//
//                    startActivity(seeTasks);
//                }
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
        if (id == R.id.menu_add) {

            Intent add_mem = new Intent(this, AddTaskActivity.class);
            add_mem.putExtra("milestone_id", String.valueOf(_id));
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}
