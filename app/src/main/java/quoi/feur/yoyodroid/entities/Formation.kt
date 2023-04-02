package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.LinkedList


class Formation (val id:Int, var name:String, var levelId: Int, var deleted:Boolean){

    companion object{

        var all : LinkedList<Formation> = LinkedList<Formation>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Formation>{
            val formList = LinkedList<Formation>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val levelId = json.getInt("levelId")
                val deleted = json.getBoolean("deleted")

                formList.add(Formation(id, name, levelId, deleted))
            }
            return formList
        }

        fun findById(id : Int) : Formation?{
            all.forEach { formation ->
                if(formation.id == id){
                    return formation
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return "Formation n° $id : $name"
    }


}