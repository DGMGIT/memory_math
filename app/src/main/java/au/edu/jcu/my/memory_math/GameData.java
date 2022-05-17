package au.edu.jcu.my.memory_math;

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
    private static final String TABLE3 = "SCORE";


    public GameData(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE1+" ("
                + "USERNAME TEXT UNIQUE PRIMARY KEY, "
                + "PASSWORD TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE2+" ("
                + " USERNAME TEXT, "
                + " MODE TEXT, "
                + " HIGHSCORE TEXT, "
                + " FOREIGN KEY ("+ "USERNAME" +") REFERENCES  "+TABLE1+"("+"USERNAME"+"));");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE3+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "MODE TEXT, "
                + "SCORE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void adduser(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();

        String sqlCode0 = "INSERT INTO "+TABLE1+" (USERNAME, PASSWORD) VALUES ("
                + "\"" + username + "\"," + "\"" + password + "\");";

        String sqlCode1 = "INSERT INTO "+TABLE2+" (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "EASY" + "\"," + "\"" + "HIGHSCORE: 0" + "\");";

        String sqlCode2 = "INSERT INTO "+TABLE2+" (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "MEDIUM" + "\"," + "\"" + "HIGHSCORE: 0" + "\");";

        String sqlCode3 = "INSERT INTO "+TABLE2+" (USERNAME, MODE, HIGHSCORE) VALUES("
                + "\"" + username + "\"," + "\"" + "HARD" + "\"," + "\"" + "HIGHSCORE: 0" + "\");";


        System.out.println(sqlCode0);
        database.execSQL(sqlCode0);

        System.out.println(sqlCode1);
        database.execSQL(sqlCode1);

        System.out.println(sqlCode2);
        database.execSQL(sqlCode2);

        System.out.println(sqlCode3);
        database.execSQL(sqlCode3);

        database.close();
    }

    public void deleteCard(int i) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("FLASHCARDS", "id =" + i, null);
    }

    public int numUsers (){
        SQLiteDatabase database = this.getReadableDatabase();

        int count = (int) DatabaseUtils.queryNumEntries(database, TABLE1);
        database.close();
        return count;
    }

    public boolean isEmpty (String table) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        boolean rowExists;
        rowExists = cursor.moveToFirst(); // if empty = false
        cursor.close();
        database.close();
        return rowExists;
    }


    public List<String> getAll(String table){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + ";", null);

        List<String> result = new ArrayList<>();
        while (cursor.moveToNext()){
//            int id = cursor.getInt(0);
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            System.out.println("data record contains; " + username + password );

            result.add(username + ":" + password);
        }
        cursor.close();
        database.close();

        return result;
    }

    public List<String> getSelect(String table, int i){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + table + ";", null);

        List<String> result = new ArrayList<>();
        while (cursor.moveToNext()){
            String select = cursor.getString(i);
            System.out.println("data record contains; " + select);

            result.add(select);
        }
        cursor.close();
        database.close();

        return result;
    }
}
