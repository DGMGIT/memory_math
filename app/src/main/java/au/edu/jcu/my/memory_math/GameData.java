package au.edu.jcu.my.memory_math;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GameData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game_database";
    private static final String TABLE1 = "USERS";
    private static final String TABLE2 = "MODE";

    private static final String TABLE4 = "EASY";
    private static final String TABLE5 = "MEDIUM";
    private static final String TABLE6 = "HARD";


    public GameData(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE1 + " ("
                + "USERNAME TEXT UNIQUE PRIMARY KEY, "
                + "PASSWORD TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE2 + " ("
                + " USERNAME TEXT, "
                + " MODE TEXT, "
                + " HIGHSCORE INTEGER, "
                + " FOREIGN KEY (" + "USERNAME" + ") REFERENCES  " + TABLE1 + "(" + "USERNAME" + "));");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE4 + " ("
                + " USERNAME TEXT, "
                + "SCORE INTEGER, "
                + "CTL TEXT, "
                + " FOREIGN KEY (" + "USERNAME" + ") REFERENCES  " + TABLE1 + "(" + "USERNAME" + "));");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE5 + " ("
                + " USERNAME TEXT, "
                + "SCORE INTEGER, "
                + "CTL TEXT, "
                + " FOREIGN KEY (" + "USERNAME" + ") REFERENCES  " + TABLE1 + "(" + "USERNAME" + "));");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE6 + " ("
                + " USERNAME TEXT, "
                + "SCORE INTEGER, "
                + "CTL TEXT, "
                + " FOREIGN KEY (" + "USERNAME" + ") REFERENCES  " + TABLE1 + "(" + "USERNAME" + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void adduser(String username, String password) {
        SQLiteDatabase database = this.getWritableDatabase();

        String sqlCode0 = "INSERT INTO " + TABLE1 + " (USERNAME, PASSWORD) VALUES ("
                + "\"" + username + "\"," + "\"" + password + "\");";

        String sqlCode1 = "INSERT INTO " + TABLE2 + " (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "EASY" + "\"," + 30 + ");";

        String sqlCode2 = "INSERT INTO " + TABLE2 + " (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "MEDIUM" + "\"," + 60 + ");";

        String sqlCode3 = "INSERT INTO " + TABLE2 + " (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "HARD" + "\"," + 90 + ");";

        String sqlCode4 = "INSERT INTO " + TABLE4 + " (USERNAME, SCORE, CTL) VALUES("
                + "\"" + username + "\"," + "" + 0 + "," + "\"" + "~" + "\");";

        String sqlCode5 = "INSERT INTO " + TABLE5 + " (USERNAME, SCORE, CTL) VALUES("
                + "\"" + username + "\"," + "" + 0 + "," + "\"" + "~" + "\");";

        String sqlCode6 = "INSERT INTO " + TABLE6 + " (USERNAME, SCORE, CTL) VALUES("
                + "\"" + username + "\"," + "" + 0 + "," + "\"" + "~" + "\");";


        System.out.println(sqlCode0);
        database.execSQL(sqlCode0);

        System.out.println(sqlCode1);
        database.execSQL(sqlCode1);

        System.out.println(sqlCode2);
        database.execSQL(sqlCode2);

        System.out.println(sqlCode3);
        database.execSQL(sqlCode3);

        database.execSQL(sqlCode4);
        database.execSQL(sqlCode5);
        database.execSQL(sqlCode6);

        database.close();
    }

    public void addScore(String username, String table, int score, String CTL) {
        SQLiteDatabase database = this.getWritableDatabase();

        String sqlCode0 = "INSERT INTO " + table + " (USERNAME, SCORE, CTL) VALUES("
                + "\"" + username + "\"," + "" + score + "," + "\"" + CTL + "\");";

        database.execSQL(sqlCode0);
    }

    public void updateHighscore(String username, String table, int score, String mode) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("HIGHSCORE", score);

        System.out.println(database.update(table, cv, "USERNAME = ? AND MODE = ?",
                new String[]{username, mode}));
    }

    public int numRows(String table) {
        SQLiteDatabase database = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(database, table);
        database.close();
        return count;
    }

    public boolean isEmpty(String table) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        boolean rowExists;
        rowExists = cursor.moveToFirst(); // if empty = false
        cursor.close();
        database.close();
        return rowExists;
    }


    public List<String> getAll(String table) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + ";", null);

        int numCol = cursor.getColumnCount();

        List<String> resultAll = new ArrayList<>();
        StringBuilder result;
        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
            int i;
            result = new StringBuilder();
            for (i = 0; i < numCol; i++) {
                if (result.toString().equals("")) {
                    String data = cursor.getString(i);
                    result.append(data);
                } else {
                    result.append(":");
                    String data = cursor.getString(i);
                    result.append(data);
                }
            }
            System.out.println("data record contains; " + result);
            resultAll.add("" + result);
        }
        cursor.close();
        database.close();

        return resultAll;
    }

    public List<String> getAllBased(String data, String table, String username) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE INSTR(" + data + ", " + "\"" + username + "\"" + ");", null);

        int numCol = cursor.getColumnCount();

        List<String> resultAll = new ArrayList<>();
        StringBuilder result;
        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
            int i;
            result = new StringBuilder();
            for (i = 1; i < numCol; i++) {
                if (result.toString().equals("")) {
                    String dataall = cursor.getString(i);
                    result.append(dataall);
                } else {
                    result.append(":");
                    String dataall = cursor.getString(i);
                    result.append(dataall);
                }
            }
            System.out.println("data record contains; " + result);
            resultAll.add("" + result);
        }
        cursor.close();
        database.close();

        return resultAll;
    }

    public List<String> getSelect(String table, int i) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " ; ", null);

        List<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            String select = cursor.getString(i);
            System.out.println("data record contains; " + select);

            result.add(select);
        }
        cursor.close();
        database.close();

        return result;
    }

    public List<Integer> getSelectBasedInt(String data, String table, int i, String username) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE INSTR(" + data + ", " + "\"" + username + "\"" + ");", null);

        List<Integer> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            int select = cursor.getInt(i);
            System.out.println("data record contains; " + select);

            result.add(select);
        }
        cursor.close();
        database.close();

        return result;
    }
}
