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

    private DBManager milestoneManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.DESC };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    TextView idTextView;
    TextView titleTextView;
    TextView descTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schemes);

        milestoneManager = new DBManager(this);
        milestoneManager.open();
        Cursor cursor = milestoneManager.fetch();

        listView = (ListView) findViewById(R.id.schemeList);
        listView.setEmptyView(findViewById(R.id.lblEmpty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_listview_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                idTextView = (TextView) view.findViewById(R.id.id);
//                titleTextView = (TextView) view.findViewById(R.id.title);
//                descTextView = (TextView) view.findViewById(R.id.desc);
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
//                startActivity(modify_intent);

                String idNumber = idTextView.getText().toString();

                Intent viewTask = new Intent(getApplicationContext(), ViewTaskActivity.class);
                viewTask.putExtra("id", idNumber);
                startActivity(viewTask);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

//        if (adapter == null) {
//            menu.findItem(R.id.edit_scheme).setEnabled(false);
//            menu.findItem(R.id.edit_scheme).setVisible(false);
//        } else {
//            menu.findItem(R.id.edit_scheme).setEnabled(true);
//            menu.findItem(R.id.edit_scheme).setVisible(true);
//        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        View view = this.findViewById(R.id.content);

        int id = item.getItemId();
        switch (id) {
            case R.id.add_scheme:
                Intent add_milestone = new Intent(this, AddSchemeActivity.class);
                startActivity(add_milestone);
                break;
            case R.id.edit_scheme:
                Intent edit_scheme = new Intent(this, ModifySchemeActivity.class);
                edit_scheme.putExtra("title", titleTextView.getText().toString());
                edit_scheme.putExtra("desc", descTextView.getText().toString());
                edit_scheme.putExtra("id", idTextView.getText().toString());

                startActivity(edit_scheme);
        }
//        if (id == R.id.add_scheme) {
//
//            Intent add_mem = new Intent(this, AddSchemeActivity.class);
//            startActivity(add_mem);
//
//        }
        return super.onOptionsItemSelected(item);
    }
}
