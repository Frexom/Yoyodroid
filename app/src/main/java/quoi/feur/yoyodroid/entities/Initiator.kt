package quoi.feur.yoyodroid.entities

import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*

class Initiator(val id : Int, var name : String, var email : String, var password : String, var director : Boolean, var levelId: Int, var deleted : Boolean) {
    companion object{

        var all : List<Initiator> = LinkedList<Initiator>()
        fun createListFromJSONArray(array: JSONArray) : LinkedList<Initiator> {
            val initiatorList = LinkedList<Initiator>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var email = json.getString("email")
                var password = json.getString("password")
                var director = json.getBoolean("director")
                var levelId = json.getInt("levelId")
                var deleted = json.getBoolean("deleted")

                initiatorList.add(Initiator(id, name, email, password, director, levelId, deleted))
            }
            return initiatorList
        }
    }
}