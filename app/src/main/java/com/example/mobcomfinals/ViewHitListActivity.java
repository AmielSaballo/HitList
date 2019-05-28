package com.example.mobcomfinals;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private DatabaseHelper db;


    private ListView listView;

    private SimpleCursorAdapter adapter;

    private Scheme scheme;

    final String[] from = new String[] { DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

    TextView idTextView;
    TextView titleTextView;
    TextView descTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schemes);

        db = new DatabaseHelper(getApplicationContext());

        db.open();
        Cursor cursor = db.fetch();

        listView = (ListView) findViewById(R.id.schemeList);
        listView.setEmptyView(findViewById(R.id.lblEmpty));

        adapter = new SimpleCursorAdapter(this,
                R.layout.activity_listview_view,
                cursor,
                from,
                to,
                0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
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

                Intent viewMilestone = new Intent(getApplicationContext(), ViewMilestoneActivity.class);
                viewMilestone.putExtra("id", idNumber);
                startActivity(viewMilestone);
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
        int id = item.getItemId();
        switch (id) {
            case R.id.add_scheme:
                Intent add_scheme = new Intent(this, AddSchemeActivity.class);
                startActivity(add_scheme);
                break;
            case R.id.edit_scheme:
                Intent edit_scheme = new Intent(this, ModifySchemeActivity.class);
                scheme.setId(Integer.parseInt(idTextView.getText().toString()));
                scheme.setTitle(titleTextView.getText().toString());
                scheme.setDesc(descTextView.getText().toString());

                edit_scheme.putExtra("scheme", scheme);

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
