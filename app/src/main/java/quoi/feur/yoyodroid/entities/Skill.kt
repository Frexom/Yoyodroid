package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Skill (val id : Int, var name : String, var levelId : Int, var deleted : Boolean) {
    companion object{

        var all : LinkedList<Skill> = LinkedList<Skill>()


        fun createListFromJSONArray(array: JSONArray) : LinkedList<Skill> {
            val skillList = LinkedList<Skill>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val levelId = json.getInt("levelId")
                val deleted = json.getBoolean("deleted")

                skillList.add(Skill(id, name, levelId, deleted))
            }
            return skillList
        }
    }
}