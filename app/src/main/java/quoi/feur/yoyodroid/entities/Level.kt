package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Level (val id : Int, var name : String, var deleted : Boolean) {
    companion object{

        var all : LinkedList<Level> = LinkedList<Level>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Level> {
            val levelList = LinkedList<Level>()
            for (i in 0 until array.length()) {
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val deleted = json.getBoolean("deleted")

                levelList.add(Level(id, name, deleted))
            }
            return levelList
        }
    }

}