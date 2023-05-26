package com.example.fetchssid.DataStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fetchssid.Model.SsidData;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SSIDdb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "AccessCodeTables";

    private static final String ID_COL = "id";
    private static final String ACCESS_CODE_COL = "AccessCode";
    private static final String SSID_COL = "SSID";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME
                + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ACCESS_CODE_COL + " TEXT,"
                + SSID_COL + " TEXT" + ")";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void AddData(SsidData data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACCESS_CODE_COL,data.getAccessCode());
        values.put(SSID_COL,data.getSSID());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<SsidData> getData(){
        List<SsidData> dataList = new ArrayList<SsidData>();
//
//        String fetchQuery = "SELECT "+ ACCESS_CODE_COL +" FROM " + TABLE_NAME + " WHERE " + SSID_COL + " = ?";
        String fetchQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(fetchQuery,null);
        if (cursor.moveToFirst()){
            do{
                SsidData data = new SsidData();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setAccessCode(cursor.getString(1));
                data.setSSID(cursor.getString(2));

                dataList.add(data);
            }while (cursor.moveToNext());
        }
        return dataList;
    }

    public List<SsidData> fetchAccessCode(String SSID){
        SQLiteDatabase db = this.getReadableDatabase();
        List<SsidData> dataList = new ArrayList<SsidData>();
//        String fetchQuery = "SELECT * FROM " + TABLE_NAME;
//                + " WHERE " + SSID_COL + " = " + ACCESS_CODE_COL;
//        String fetchQuery = "SELECT " + ACCESS_CODE_COL
//                + " FROM " + TABLE_NAME + " WHERE "
//                + SSID_COL + " = '"+ SSID +"'";

        String fetchQuery = "SELECT "+ACCESS_CODE_COL+" FROM " + TABLE_NAME + " WHERE "+SSID_COL+" ='" + SSID +"'";

        Cursor cursor = db.rawQuery(fetchQuery,null);

        if (cursor.moveToFirst()){
            do {
                SsidData data = new SsidData();
//                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setAccessCode(cursor.getString(0));
                data.setSSID(cursor.getString(0));

                dataList.add(data);
            }while (cursor.moveToNext());
        }
        return dataList;

    }
}
