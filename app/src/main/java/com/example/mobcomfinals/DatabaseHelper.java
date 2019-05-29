package com.example.mobcomfinals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_SCHEME = "SCHEME";
    public static final String TABLE_MILESTONE = "MILESTONE";
    public static final String TABLE_TASK = "TASK";
    public static final String TABLE_SMT = "SCHEME_MILESTONE_TASK";

    // Table columns
    public static final String KEY_ID = "_id";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_DESC = "description";
    public static final String KEY_STATUS = "status";

    //Foreign keys
    public static final String FK_SCHEME_ID = "FK_Scheme_id";
    public static final String FK_MILESTONE_ID = "FK_Milestone_id";

    // Database Information
    static final String DB_NAME = "HitList.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE_SCHEME = "create table " + TABLE_SCHEME
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_SUBJECT + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT);";

    private static final String CREATE_TABLE_MILESTONE = "create table " + TABLE_MILESTONE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_SUBJECT + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT, "
            + KEY_STATUS + " TEXT NOT NULL DEFAULT 'Incomplete', "
            + "CONSTRAINT " + FK_SCHEME_ID + " FOREIGN KEY (_id) REFERENCES SCHEME(_id)"
            +");";

    private static final String CREATE_TABLE_TASK = "create table " + TABLE_TASK
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_SUBJECT + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT, "
            + KEY_STATUS + " TEXT NOT NULL DEFAULT 'Incomplete', "
            + "CONSTRAINT " + FK_MILESTONE_ID + " FOREIGN KEY (_id) REFERENCES MILESTONE(_id)"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SCHEME);
        db.execSQL(CREATE_TABLE_MILESTONE);
        db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MILESTONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);

        onCreate(db);
    }
}
