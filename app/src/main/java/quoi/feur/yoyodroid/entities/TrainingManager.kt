package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class TrainingManager(val id : Int, var initiatorId: Int, var formationId : Int) {
    companion object{

        var all : LinkedList<TrainingManager> = LinkedList<TrainingManager>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<TrainingManager> {
            val managerList = LinkedList<TrainingManager>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val initiatorId = json.getInt("initiatorId")
                val formationId = json.getInt("formationId")

                managerList.add(TrainingManager(id, initiatorId, formationId))
            }
            return managerList
        }
    }
}