package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Level (val id : Int, var name : String, var deleted : Boolean) {
    companion object{

        var all : List<Level> = LinkedList<Level>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Level> {
            val levelList = LinkedList<Level>()
            for (i in 0 until array.length()) {
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var deleted = json.getBoolean("deleted")

                levelList.add(Level(id, name, deleted))
            }
            return levelList
        }
    }

}