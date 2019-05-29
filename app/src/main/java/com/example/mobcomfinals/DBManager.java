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
//--------------------------------------------------------------------------------------------------
    public void insertScheme(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_TITLE, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        database.insert(DatabaseHelper.TABLE_SCHEME, null, contentValue);
    }

    public Cursor fetchSchemes() {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};
        Cursor cursor = database.query(DatabaseHelper.TABLE_SCHEME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateScheme(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_SCHEME, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }

    public void deleteScheme(long _id) {
        database.delete(DatabaseHelper.TABLE_SCHEME, DatabaseHelper.KEY_ID + "=" + _id, null);
    }
    //----------------------------------------------------------------------------------------------
    public void insertMilestone(String name, String desc, String scheme_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_TITLE, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        contentValue.put(DatabaseHelper.FK_SCHEME_ID, scheme_id);

        database.insert(DatabaseHelper.TABLE_MILESTONE, null, contentValue);
    }

    public Cursor fetchMilestones(long _id) {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};
        String whereClause = DatabaseHelper.FK_SCHEME_ID + " = ?";
        String[] whereArg = {String.valueOf(_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_MILESTONE, columns, whereClause, whereArg, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateMilestone(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_MILESTONE, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }
    public void deleteMilestone(long _id) {
        database.delete(DatabaseHelper.TABLE_MILESTONE, DatabaseHelper.KEY_ID + "=" + _id, null);
    }
    //----------------------------------------------------------------------------------------------
    public void insertTask(String name, String desc, String milestone_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.KEY_TITLE, name);
        contentValue.put(DatabaseHelper.KEY_DESC, desc);
        contentValue.put(DatabaseHelper.FK_MILESTONE_ID, milestone_id);

        database.insert(DatabaseHelper.TABLE_TASK, null, contentValue);
    }

    public Cursor fetchTasks(long _id) {
        String[] columns = new String[] { DatabaseHelper.KEY_ID, DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_DESC};
        String whereClause = DatabaseHelper.FK_MILESTONE_ID + " = ?";
        String[] whereArg = {String.valueOf(_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_TASK, columns, whereClause, whereArg, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateTask(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, name);
        contentValues.put(DatabaseHelper.KEY_DESC, desc);

        int i = database.update(DatabaseHelper.TABLE_TASK, contentValues, DatabaseHelper.KEY_ID + " = " + _id, null);
        return i;
    }
    public void deleteTask(long _id) {
        database.delete(DatabaseHelper.TABLE_TASK, DatabaseHelper.KEY_ID + "=" + _id, null);
    }

    public int taskDone (long _id, int color) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_COLOR, color);

        String whereClause = DatabaseHelper.KEY_ID + " = " + _id;
        //String[] whereArgs = new String[] {String.valueOf(_id)};

        int i = database.update(DatabaseHelper.TABLE_TASK, contentValues, whereClause, null);
        return i;
    }

    public int getColor (long _id) {
        String[] columns = new String[] {DatabaseHelper.KEY_COLOR};
        String whereClause = DatabaseHelper.KEY_ID + " = " + _id;
        //String[] whereArg = {String.valueOf(_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_TASK, columns, whereClause, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        int i = cursor.getInt(0);
        return i;
    }
}

