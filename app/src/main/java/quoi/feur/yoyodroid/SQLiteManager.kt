package quoi.feur.yoyodroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteManager(context: Context, factory : SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "yoyodroid.db", factory, 3){

    override fun onCreate(db: SQLiteDatabase) {
        val createLevel = ("CREATE TABLE \"yoyo_level\" (\n" +
                "\t\"lev_id\"\tINTEGER NOT NULL,\n" +
                "\t\"lev_name\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"lev_id\")\n" +
                ")")
        db.execSQL(createLevel)

        val createSkill = ("CREATE TABLE \"yoyo_skill\" (\n" +
                "\t\"ski_id\"\tINTEGER NOT NULL,\n" +
                "\t\"lev_id\"\tINTEGER NOT NULL,\n" +
                "\t\"ski_name\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ski_id\")\n" +
                ")")
        db.execSQL(createSkill)

        val createAptitude = ("CREATE TABLE \"yoyo_aptitude\" (" +
                "\t\"apt_id\"\tINTEGER NOT NULL," +
                "\t\"ski_id\"\tINTEGER NOT NULL," +
                "\t\"apt_name\"\tTEXT NOT NULL," +
                "\tPRIMARY KEY(\"apt_id\")" +
                ")")
        db.execSQL(createAptitude)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}