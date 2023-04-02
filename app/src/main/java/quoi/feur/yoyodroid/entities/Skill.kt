package quoi.feur.yoyodroid.entities

import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*

class Skill (val id : Int, var name : String, var levelId : Int, var deleted : Boolean) {
    companion object{

        var all : List<Skill> = LinkedList<Skill>()


        fun createListFromJSONArray(array: JSONArray) : LinkedList<Skill> {
            val skillList = LinkedList<Skill>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var levelId = json.getInt("levelId")
                var deleted = json.getBoolean("deleted")

                skillList.add(Skill(id, name, levelId, deleted))
            }
            return skillList
        }
    }
}