package quoi.feur.yoyodroid.entities

import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*


class Session (val id : Int, var date:String, var formationId: Int, var deleted : Boolean) {
    companion object{

        var all: List<Session> = LinkedList<Session>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Session> {
            val sessionList = LinkedList<Session>()
            for (i in 0 until array.length()) {
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var date = json.getString("date")
                var formationId = json.getInt("formationId")
                var deleted = json.getBoolean("deleted")

                sessionList.add(Session(id, date, formationId, deleted))
            }
            return sessionList
        }
    }
}