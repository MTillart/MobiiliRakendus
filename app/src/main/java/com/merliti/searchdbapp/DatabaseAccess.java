package com.merliti.searchdbapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private static final String TAG = "DatabaseAccess";

    String tableName = "Trash";

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new Database(context);
    }
    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DatabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }
    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    /**
     * Read all Trash from the database.
     *
     * @return a List of quotes
     */
// Fuction get all trash
    public ArrayList<Trash> getTrash(){

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"_id", "Name", "Container", "Description"};

        queryBuilder.setTables(tableName);

        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);

        ArrayList<Trash> result = new ArrayList<>();
        Trash trash = new Trash();

        if (cursor.moveToFirst()){
            do {

                trash.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                trash.setName(cursor.getString(cursor.getColumnIndex("Name")));
                trash.setContainer(cursor.getString(cursor.getColumnIndex("Container")));
                trash.setDescription(cursor.getString(cursor.getColumnIndex("Description")));

                result.add(trash);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;

    }

    // Function get all trash names
    public List<String> getNames(){
        //SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name"};

        queryBuilder.setTables(tableName);

        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Name")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;

    }

    // Function get Trash by name
    public Trash getTrashByName(String name){
        //SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"_id", "Name", "Container", "Description"};

        queryBuilder.setTables(tableName);

        // Query for : Select * from Trash where Name like %pattern%
        Cursor cursor = queryBuilder.query(database, sqlSelect, "Name LIKE ?", new String[]{"%"+name+"%"}, null, null, null);
        // For EXACT NAME ONLY
        //Cursor cursor = queryBuilder.query(db, sqlSelect, "Name = ?", new String[]{name}, null, null, null);

        ArrayList<Trash> result = new ArrayList<>();
        Trash trash = new Trash();

        if (cursor.moveToFirst()){
            do {

                trash.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                trash.setName(cursor.getString(cursor.getColumnIndex("Name")));
                trash.setContainer(cursor.getString(cursor.getColumnIndex("Container")));
                trash.setDescription(cursor.getString(cursor.getColumnIndex("Description")));

                result.add(trash);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trash;

    }
    public Cursor searchTrash(String searchTxt) {
        Log.d(TAG, "searchTrash: Searching..."+ searchTxt);
        Cursor cursor;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tableName);


        String[] sqlSelect = {"_id", "Name", "Container", "Description"};

        cursor = queryBuilder.query(database, sqlSelect, "Name LIKE ?", new String[]{"%"+searchTxt+"%"}, null, null, null);

        return cursor;

    }





}
