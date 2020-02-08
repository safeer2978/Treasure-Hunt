package com.treasurehunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;



import java.util.ArrayList;
import java.util.List;

public class Table extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TreasureDatabase";
    Table(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql1 = "CREATE TABLE IF NOT EXISTS QUESTIONS_TABLE(\n" +
                "   question STRING NOT NULL,\n" +
                "   barcode STRING NOT NULL,\n" +
                "   hint STRING NOT NULL\n);";

        String sql2 = "CREATE TABLE IF NOT EXISTS LOGIN_DATA(\n" +
                "   teamname STRING NOT NULL,\n" +
                "   membername STRING NOT NULL,\n" +
                "   uid STRING NOT NULL\n);";

        String sql3 = "CREATE TABLE IF NOT EXISTS PROGRESS_TABLE(\n" +
                "   id STRING NOT NULL,\n" +
                "   timestamp STRING NOT NULL,\n" +
                "   roundnumber STRING NOT NULL,\n" +
                "   problemnumber STRING NOT NULL,\n" +
                "   score STRING NOT NULL\n);";

        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean addQuestions(Question question) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question.getQuestion());
        contentValues.put("barcode", question.getBarcode());
        contentValues.put("hint",  question.getHint()) ;

        SQLiteDatabase db = getWritableDatabase();
        return db.insert("QUESTIONS_TABLE", null, contentValues) != -1;
    }


    boolean updateQuestions(Question question)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question.getQuestion());
        contentValues.put("barcode", question.getBarcode());
        contentValues.put("hint",  question.getHint()) ;

        SQLiteDatabase db = getWritableDatabase();
        return db.update("QUESTIONS_TABLE" , contentValues, "barcode" + "=?", new String[] {question.getBarcode()}) ==1;
    }


    boolean removeQuestions(Question question)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("QUESTIONS_TABLE", "barcode" + "=?",  new String[] {question.getBarcode()}) ==1 ;
    }



    boolean addTeam (Team team) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("teamname", team.getTeamname());
        contentValues.put("membername", team.getMembername());
        contentValues.put("uid",  team.getUid()) ;

        SQLiteDatabase db = getWritableDatabase();
        return db.insert("LOGIN_DATA", null, contentValues) != -1;
    }


    boolean updateTeam(Team team)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("teamname", team.getTeamname());
        contentValues.put("membername", team.getMembername());
        contentValues.put("uid",  team.getUid()) ;

        SQLiteDatabase db = getWritableDatabase();
        return db.update("LOGIN_DATA" , contentValues, "teamname" + "=?", new String[] {team.getTeamname()}) ==1;
    }


    boolean removeTeam(Team team)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("LOGIN_DATA", "teamname" + "=?", new String[] {team.getTeamname()}) ==1;
    }


    boolean addProgress (Progress progress)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", progress.getTimestamp());
        contentValues.put("roundnumber", progress.getRoundnumber());
        contentValues.put("problemnumber", progress.getProblemnumber());
        contentValues.put("score", progress.getScore());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert("PROGRESS_TABLE", null, contentValues) != -1;
    }


    boolean updateProgress (Progress progress)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", progress.getTimestamp());
        contentValues.put("roundnumber", progress.getRoundnumber());
        contentValues.put("problemnumber", progress.getProblemnumber());
        contentValues.put("score", progress.getScore());

        SQLiteDatabase db = getWritableDatabase();
        return db.update("PROGRESS_TABLE" , contentValues, "id" + "=?",new String[] {progress.getId()}) ==1;
    }


    boolean removeProgress (Progress progress)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("PROGRESS_DATA", "id" + "=?", new String[] {progress.getId()}) ==1;
    }

    Question getQuestion(int id){
        SQLiteDatabase db = getReadableDatabase();

        return null;
    }
public     List<Question> getQuestions(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Question> questions = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM "+"question", null);
        if (c.moveToFirst()){
            do {
                Question temp = new Question();
                temp.setQuestion(c.getString(0));
                temp.setBarcode(c.getString(1));
                temp.setHint(c.getString(2));
                questions.add(temp);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return questions;
    }


    public void setTeam(Team team){

    }


}



