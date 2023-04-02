package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Aptitude(val id : Int, var name : String, var skillId : Int, var deleted : Boolean) {

    companion object{

        var all : LinkedList<Aptitude> = LinkedList<Aptitude>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Aptitude>{
            val aptitudeList = LinkedList<Aptitude>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val skillId = json.getInt("skillId")
                val deleted = json.getBoolean("deleted")

                aptitudeList.add(Aptitude(id, name, skillId, deleted))
            }
            return aptitudeList
        }

        fun findById(id : Int) : Aptitude?{
            all.forEach {aptitude ->
                if(aptitude.id == id){
                    return aptitude
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return name
    }
}