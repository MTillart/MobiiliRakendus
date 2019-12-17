package com.merliti.searchdbapp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "testtrash.db";
    private static final int DB_VER = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

//    // Fuction get all trash
//    public List<Trash> getTrash(){
//        SQLiteDatabase db = getReadableDatabase();
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//
//        String[] sqlSelect = {"_id", "Name", "Container", "Description"};
//        String tableName = "testtrash";
//
//        queryBuilder.setTables(tableName);
//
//        Cursor cursor = queryBuilder.query(db, sqlSelect, null, null, null, null, null);
//
//        List<Trash> result = new ArrayList<>();
//        Trash trash = new Trash();
//
//        if (cursor.moveToFirst()){
//            do {
//
//                trash.setId(cursor.getInt(cursor.getColumnIndex("_id")));
//                trash.setName(cursor.getString(cursor.getColumnIndex("Name")));
//                trash.setContainer(cursor.getString(cursor.getColumnIndex("Container")));
//                trash.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
//
//                result.add(trash);
//            } while (cursor.moveToNext());
//        }
//        return result;
//
//    }
//
//    // Function get all trash names
//    public List<String> getNames(){
//        SQLiteDatabase db = getReadableDatabase();
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//
//        String[] sqlSelect = {"Name"};
//        String tableName = "testtrash";
//
//        queryBuilder.setTables(tableName);
//
//        Cursor cursor = queryBuilder.query(db, sqlSelect, null, null, null, null, null);
//
//        List<String> result = new ArrayList<>();
//
//        if (cursor.moveToFirst()){
//            do {
//                result.add(cursor.getString(cursor.getColumnIndex("Name")));
//            } while (cursor.moveToNext());
//        }
//        return result;
//
//    }
//
//    // Function get Trash by name
//    public List<Trash> getTrashByName(String name){
//        SQLiteDatabase db = getReadableDatabase();
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//
//        String[] sqlSelect = {"_id", "Name", "Container", "Description"};
//        String tableName = "testtrash";
//
//        queryBuilder.setTables(tableName);
//
//        // Query for : Select * from Trash where Name like %pattern%
//        Cursor cursor = queryBuilder.query(db, sqlSelect, "Name LIKE ?", new String[]{"%"+name+"%"}, null, null, null);
//        // For EXACT NAME ONLY
//        //Cursor cursor = queryBuilder.query(db, sqlSelect, "Name = ?", new String[]{name}, null, null, null);
//
//        List<Trash> result = new ArrayList<>();
//        Trash trash = new Trash();
//
//        if (cursor.moveToFirst()){
//            do {
//
//                trash.setId(cursor.getInt(cursor.getColumnIndex("_id")));
//                trash.setName(cursor.getString(cursor.getColumnIndex("Name")));
//                trash.setContainer(cursor.getString(cursor.getColumnIndex("Container")));
//                trash.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
//
//                result.add(trash);
//            } while (cursor.moveToNext());
//        }
//        return result;
//
//    }
}
