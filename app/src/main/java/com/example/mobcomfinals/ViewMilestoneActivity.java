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
import android.widget.Toast;

public class ViewMilestoneActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_view);

        String scheme_id = getIntent().getStringExtra("scheme_id");
        long _id = 50;
//        Toast.makeText(this,
//                scheme_id,
//                Toast.LENGTH_LONG).show();
        if (scheme_id != null) {
            return;
        }else {
//            _id = Long.parseLong(scheme_id);
        }
//        String scheme_id = scheme.getStringExtra("scheme_id");
//        if (scheme_id != null) {
//            _id = Long.parseLong(scheme_id);
//        }

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchMilestone(_id);

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

//                Intent modify_intent = new Intent(getApplicationContext(), ModifySchemeActivity.class);
//                modify_intent.putExtra("title", title);
//                modify_intent.putExtra("desc", desc);
//                modify_intent.putExtra("id", id);

                Intent seeTask = new Intent(getApplicationContext(), ViewTaskActivity.class);
                seeTask.putExtra("Milestone_id", id);

                startActivity(seeTask);
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
            long _id = 50;
            Intent add_mem = new Intent(this, AddMilestoneActivity.class);
            add_mem.putExtra("scheme_id", _id);

            Toast.makeText(this,
                    String.valueOf(_id),
                    Toast.LENGTH_LONG).show();

            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}
