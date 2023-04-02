package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class TrainingManager(val id : Int, var initiatorId: Int, var formationId : Int) {
    companion object{

        var all : List<TrainingManager> = LinkedList<TrainingManager>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<TrainingManager> {
            val managerList = LinkedList<TrainingManager>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var initiatorId = json.getInt("initiatorId")
                var formationId = json.getInt("formationId")

                managerList.add(TrainingManager(id, initiatorId, formationId))
            }
            return managerList
        }
    }
}