package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Aptitude(val id : Int, var name : String, var skillId : Int, var deleted : Boolean) {

    companion object{

        var all : List<Aptitude> = LinkedList<Aptitude>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Aptitude>{
            val aptitudeList = LinkedList<Aptitude>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var skillId = json.getInt("skillId")
                var deleted = json.getBoolean("deleted")

                aptitudeList.add(Aptitude(id, name, skillId, deleted))
            }
            return aptitudeList
        }
    }
}