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

public class ViewHitListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schemes);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchSchemes();

        listView = (ListView) findViewById(R.id.schemeList);
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

                Intent seeMilestones = new Intent(getApplicationContext(), ViewMilestoneActivity.class);
                if (id !=null) {
                    seeMilestones.putExtra("scheme_id", id);

                    startActivity(seeMilestones);
                }
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

        View view = findViewById(R.id.content);

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_add:
                Intent add_mem = new Intent(this, AddSchemeActivity.class);
                startActivity(add_mem);
                break;

            case R.id.Edit:
                Intent edit_mem = new Intent(this, ListModifyScheme.class);
                startActivity(edit_mem);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
