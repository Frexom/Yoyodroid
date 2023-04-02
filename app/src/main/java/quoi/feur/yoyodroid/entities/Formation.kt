package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.LinkedList


class Formation (val id:Int, var name:String, var levelId: Int, var deleted:Boolean){

    companion object{

        var all : List<Formation> = LinkedList<Formation>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Formation>{
            val formList = LinkedList<Formation>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var levelId = json.getInt("levelId")
                var deleted = json.getBoolean("deleted")

                formList.add(Formation(id, name, levelId, deleted))
            }
            return formList
        }
    }

    public override fun toString(): String {
        return "Formation n° $id : $name"
    }


}