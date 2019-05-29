package com.example.mobcomfinals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertScheme(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        database.insert(DatabaseHelper.TABLE_SCHEME, null, contentValue);
    }

    public Cursor fetchSchemes() {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_SUBJECT, DatabaseHelper.KEY_DESC};
        Cursor cursor = database.query(DatabaseHelper.TABLE_SCHEME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateScheme(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_SCHEME, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_SCHEME, DatabaseHelper.KEY_ID + "=" + _id, null);
    }
    //----------------------------------------------------------------------------------------------
    public void insertMilestone(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        database.insert(DatabaseHelper.TABLE_MILESTONE, null, contentValue);
    }

    public Cursor fetchMilestone() {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_SUBJECT, DatabaseHelper.KEY_DESC};
        Cursor cursor = database.query(DatabaseHelper.TABLE_MILESTONE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateMilestone(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_MILESTONE, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }
    //----------------------------------------------------------------------------------------------
    public void insertTask(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        database.insert(DatabaseHelper.TABLE_TASK, null, contentValue);
    }

    public Cursor fetchTask() {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_SUBJECT, DatabaseHelper.KEY_DESC};
        Cursor cursor = database.query(DatabaseHelper.TABLE_TASK, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateTask(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_SUBJECT, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_TASK, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }
}

