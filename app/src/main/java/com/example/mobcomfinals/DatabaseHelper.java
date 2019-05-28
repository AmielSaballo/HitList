package com.example.mobcomfinals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    //Logcat Tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    static final int DB_VERSION = 1;

    //Database Name
    private static final String DB_NAME = "HitList";

    // Table Name
    public static final String TABLE_SCHEME = "Schemes";
    public static final String TABLE_MILESTONE = "Milestones";
    public static final String TABLE_TASK = "Tasks";
    public static final String TABLE_SCHEME_MILESTONE_TASK = "S_M_T";

    // Table columns
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESC = "description";
    private static final String KEY_STATUS = "status";

    //SMT
    private static final String KEY_SCHEME_ID = "scheme_id";
    private static final String KEY_MILESTONE_ID = "milestone_id";
    private static final String KEY_TASK_ID = "task_id";


    // Creating table query
    private static final String CREATE_TABLE_SCHEME = "CREATE TABLE " + TABLE_SCHEME
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT,"
            + KEY_STATUS + "TEXT NOT NULL DEFAULT 'INCOMPLETE'"
            + ");";
    private static final String CREATE_TABLE_MILESTONE = "CREATE TABLE " + TABLE_MILESTONE
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT"
            + KEY_STATUS + "TEXT NOT NULL DEFAULT 'INCOMPLETE'"
            +");";
    private static final String CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_TASK
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT"
            + KEY_STATUS + "TEXT NOT NULL DEFAULT 'INCOMPLETE'"
            +");";
    private static final String CREATE_TABLE_SMT = "CREATE TABLE " + TABLE_SCHEME_MILESTONE_TASK
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_SCHEME_ID + " INTEGER, "
            + KEY_MILESTONE_ID + " INTEGER, "
            + KEY_TASK_ID + " INTEGER"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SCHEME);
        db.execSQL(CREATE_TABLE_MILESTONE);
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_SMT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SCHEME);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MILESTONE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SMT);

        onCreate(db);
    }

    public DatabaseHelper open() throws SQLException {
        database = this.getWritableDatabase();
        return this;
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.KEY_ID,
                DatabaseHelper.KEY_TITLE,
                DatabaseHelper.KEY_DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_SCHEME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void createScheme(Scheme scheme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,scheme.getTitle());
        values.put(KEY_DESC, scheme.getDesc());
        values.put(KEY_STATUS, scheme.getStatus());

        db.insert(TABLE_SCHEME, null, values);
    }

    public Scheme getScheme(long scheme_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SCHEME + "WHERE " +
                KEY_ID + " = " + scheme_id;

        Log.e(LOG,query);

        Cursor c = db.rawQuery(query,null);
        if (c == null) {
            c.moveToFirst();
        }

        Scheme scheme = new Scheme();
        scheme.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        scheme.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        scheme.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
        scheme.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

        return scheme;
    }

    public List<Scheme> getAllSchemes() {
        List<Scheme> schemes = new ArrayList<Scheme>();
        String query = "SELECT * FROM " + TABLE_SCHEME;

        Log.e(LOG,query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Scheme scheme = new Scheme();
                scheme.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                scheme.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                scheme.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                scheme.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

                schemes.add(scheme);
            }while (c.moveToNext());
        }
        return schemes;
    }

    public int updateScheme(Scheme scheme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,scheme.getTitle());
        values.put(KEY_DESC, scheme.getDesc());
        values.put(KEY_STATUS, scheme.getStatus());

        return db.update(TABLE_SCHEME, values, KEY_ID + " = ?",
                new String[] {String.valueOf(scheme.getId())});
    }

    public void deleteScheme (long scheme_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEME,KEY_ID + " = ?",
                new String[]{String.valueOf(scheme_id)});
    }

    //----------------------------------------------------------------------------------------------
    public long createMilestone(Milestone milestone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,milestone.getTitle());
        values.put(KEY_DESC, milestone.getDesc());
        values.put(KEY_STATUS, milestone.getStatus());

        long milestone_id = db.insert(TABLE_MILESTONE, null, values);
        return milestone_id;
    }

    public Milestone getMilestone(long milestone_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SCHEME + "WHERE " +
                KEY_ID + " = " + milestone_id;

        Log.e(LOG,query);

        Cursor c = db.rawQuery(query,null);
        if (c == null) {
            c.moveToFirst();
        }

        Milestone milestone = new Milestone();
        milestone.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        milestone.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        milestone.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
        milestone.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

        return milestone;
    }

    public List<Milestone> getAllMilestones() {
        List<Milestone> milestones = new ArrayList<Milestone>();
        String query = "SELECT * FROM " + TABLE_MILESTONE;

        Log.e(LOG,query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Milestone milestone = new Milestone();
                milestone.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                milestone.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                milestone.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                milestone.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

                milestones.add(milestone);
            }while (c.moveToNext());
        }
        return milestones;
    }

    public int updateMilestone(Milestone milestone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,milestone.getTitle());
        values.put(KEY_DESC, milestone.getDesc());
        values.put(KEY_STATUS, milestone.getStatus());

        return db.update(TABLE_MILESTONE, values, KEY_ID + " = ?",
                new String[] {String.valueOf(milestone.getId())});
    }

    public void deleteMilestone(long milestone_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MILESTONE,KEY_ID + " = ?",
                new String[]{String.valueOf(milestone_id)});
    }
    //----------------------------------------------------------------------------------------------
    public long createMilestone(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,task.getTitle());
        values.put(KEY_DESC, task.getDesc());
        values.put(KEY_STATUS, task.getStatus());

        long task_id = db.insert(TABLE_TASK, null, values);
        return task_id;
    }

    public Task getTask(long task_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SCHEME + "WHERE " +
                KEY_ID + " = " + task_id;

        Log.e(LOG,query);

        Cursor c = db.rawQuery(query,null);
        if (c == null) {
            c.moveToFirst();
        }

        Task task = new Task();
        task.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        task.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        task.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
        task.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

        return task;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String query = "SELECT * FROM " + TABLE_MILESTONE;

        Log.e(LOG,query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                task.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                task.setDesc(c.getString(c.getColumnIndex(KEY_DESC)));
                task.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));

                tasks.add(task);
            }while (c.moveToNext());
        }
        return tasks;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,task.getTitle());
        values.put(KEY_DESC, task.getDesc());
        values.put(KEY_STATUS, task.getStatus());

        return db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
    }

    public void deleteTask(long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK,KEY_ID + " = ?",
                new String[]{String.valueOf(task_id)});
    }

    /*
    * Assign Milestone to Scheme
     */
    public long SMT(long scheme_id, long milestone_id, long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCHEME_ID, scheme_id);
        values.put(KEY_MILESTONE_ID, milestone_id);
        values.put(KEY_TASK_ID, task_id);

        long id = db.insert(TABLE_SCHEME_MILESTONE_TASK, null, values);

        return id;
    }

    public int updateSM(long scheme_id, long milestone_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MILESTONE_ID, milestone_id);

        return db.update(TABLE_SCHEME_MILESTONE_TASK, values, KEY_ID + " = ?",
                new String[] {String.valueOf(scheme_id)});
    }

    public void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
