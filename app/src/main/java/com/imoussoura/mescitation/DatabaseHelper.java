package com.imoussoura.mescitation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="citation_database";
    private static int DATABASE_VERSION = 1;
    private static final String TABLE_CITATION = "citation";


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    private static final String CREATE_TABLE_CITATION = "CREATE TABLE "
            + TABLE_CITATION
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_CITATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_CITATION);
        onCreate(sqLiteDatabase);

    }

    public boolean addData(String item){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,item);
        long result=sqLiteDatabase.insert(TABLE_CITATION,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase database=this.getWritableDatabase();
        String query= "SELECT * FROM "+ TABLE_CITATION;
        Cursor data=database.rawQuery(query,null);
        return data;

    }


}

