package com.example.gymplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseConnection extends SQLiteOpenHelper {
    public DatabaseConnection(Context context) {
        super(context, "Medicinedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE MDTable(MedicineName TEXT, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MDTable");
        onCreate(sqLiteDatabase);
    }

    public boolean insertvalues(String medname, String meddate, String medtime) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Check if the record already exists
        Cursor cursor = database.rawQuery("SELECT * FROM MDTable WHERE MedicineName = ? AND date = ? AND time = ?",
                new String[]{medname, meddate, medtime});

        if (cursor.getCount() > 0) {
            cursor.close();
            return false;  // Return false if a duplicate entry exists
        }

        // Insert new record if not a duplicate
        ContentValues contentValues = new ContentValues();
        contentValues.put("MedicineName", medname);
        contentValues.put("date", meddate);
        contentValues.put("time", medtime);

        long res = database.insert("MDTable", null, contentValues);
        cursor.close();

        return res != -1;  // Return true if insertion is successful
    }

    public Cursor FetchData(String date, String time) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM MDTable WHERE date = ? AND time = ?", new String[]{date, time});
        return c;
    }

    // Method to fetch all entries for a given date
    public Cursor fetchAllEntriesForDate(String date) {
        SQLiteDatabase database = this.getReadableDatabase();
        // Query to fetch all entries for the given date
        return database.rawQuery("SELECT * FROM MDTable WHERE date = ?", new String[]{date});
    }
}
