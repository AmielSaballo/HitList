package com.example.mobcomfinals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_SCHEME = "SCHEME";
    public static final String TABLE_MILESTONE = "MILESTONE";
    public static final String TABLE_TASK = "TASK";

    // Table columns
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESC = "description";
    public static final String KEY_COLOR = "color";

    //Foreign keys
    public static final String FK_SCHEME_ID = "scheme_id";
    public static final String FK_MILESTONE_ID = "milestone_id";

    // Database Information
    static final String DB_NAME = "HitList.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE_SCHEME = "create table " + TABLE_SCHEME
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT);";

    private static final String CREATE_TABLE_MILESTONE = "create table " + TABLE_MILESTONE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT, "
            + FK_SCHEME_ID + " INTEGER DEFAULT '0'"
            +");";

    private static final String CREATE_TABLE_TASK = "create table " + TABLE_TASK
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT NOT NULL, "
            + KEY_DESC + " TEXT, "
            + KEY_COLOR + " INTEGER DEFAULT '-3050637', "
            + FK_MILESTONE_ID + " INTEGER DEFAULT '0'"
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
