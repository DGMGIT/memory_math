package au.edu.jcu.my.memory_math;

import android.content.Context;
import android.database.Cursor;
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
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE1+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME TEXT, "
                + "PASSWORD TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE2+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "MODE TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE3+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME TEXT, "
                + "PASSWORD TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void adduser(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();

        String sqlCode = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ("
                + "\"" + username + "\"," + "\"" + password + "\");";

        System.out.println(sqlCode);
        database.execSQL(sqlCode);
        database.close();
    }

    public void deleteCard(int i) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("FLASHCARDS", "id =" + i, null);
    }

    public List<String> getAll(){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM USERS;", null);

        List<String> result = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            System.out.println("data record contains; " + id + username + password );

            result.add(username + ":" + password);
        }
        cursor.close();
        database.close();

        return result;
    }

    public List<String> getSelect(int i){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM USERS;", null);

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
