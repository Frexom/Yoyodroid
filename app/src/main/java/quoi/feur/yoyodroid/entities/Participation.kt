package quoi.feur.yoyodroid.entities

import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*

class Participation ( val id : Int, var contentId : Int, var studentId : Int, var status: Status, var commentary : String){
    companion object{

        var all : List<Participation> = LinkedList<Participation>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Participation> {
            val participationList = LinkedList<Participation>()
            for (i in 0 until array.length()) {
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var contentId = json.getInt("contentId")
                var studentId = json.getInt("studentId")
                var status = Status.getStatus(json.getInt("statusId"))
                var commentary = "";
                try {
                    commentary = json.getString("commentary")
                } catch (_: Exception) {
                }
                participationList.add(Participation(id, contentId, studentId, status, commentary))
            }
            return participationList
        }
    }
}