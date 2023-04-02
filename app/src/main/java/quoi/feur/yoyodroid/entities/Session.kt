package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*


class Session (val id : Int, var date:String, var formationId: Int, var deleted : Boolean) {
    companion object{

        var all: LinkedList<Session> = LinkedList<Session>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Session> {
            val sessionList = LinkedList<Session>()
            for (i in 0 until array.length()) {
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val date = json.getString("date")
                val formationId = json.getInt("formationId")
                val deleted = json.getBoolean("deleted")

                sessionList.add(Session(id, date, formationId, deleted))
            }
            return sessionList
        }

        fun findByFormationId(id: Int) : LinkedList<Session>{
            val found = LinkedList<Session>()
            all.forEach{ session ->
                if(session.formationId == id){
                    found.add(session)
                }
            }
            return found
        }

        fun findById(id : Int) : Session?{
            all.forEach { session ->
                if(session.id == id){
                    return session
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return date.substring(0,10).replace("-", "/")
    }
}