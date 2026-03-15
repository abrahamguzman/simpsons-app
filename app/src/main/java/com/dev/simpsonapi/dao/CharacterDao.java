package com.dev.simpsonapi.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.dev.simpsonapi.db.Character;
import com.dev.simpsonapi.db.DbHelper;

import java.util.ArrayList;

public class CharacterDao {
    private DbHelper dbHelper;

    public CharacterDao(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void insert(Character character) {
        ContentValues values = new ContentValues();
        // values.put("id", character.getId());
        values.put("name", character.getName());
        values.put("age", character.getAge());
        values.put("birthdate", character.getBirtdate());
        values.put("image", character.getImage());
        values.put("occupation", character.getOccupation());
        values.put("status", character.getStatus());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert("characters", null, values);
    }

    public ArrayList<Character> getAll() {
        ArrayList<Character> characters = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        var cursor = db.query("characters", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String birthdate = cursor.getString(cursor.getColumnIndexOrThrow("birthdate"));
            String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
            String occupation = cursor.getString(cursor.getColumnIndexOrThrow("occupation"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

            Character character = new Character(id, name, age, birthdate, image, occupation, status);
            characters.add(character);
        }
        cursor.close();

        return characters;
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("characters", null, null);
    }
}
