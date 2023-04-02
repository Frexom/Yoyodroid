package quoi.feur.yoyodroid.entities

import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*

class Initiator(val id : Int, var name : String, var email : String, var password : String, var director : Boolean, var levelId: Int, var deleted : Boolean) {
    companion object{

        var all : LinkedList<Initiator> = LinkedList<Initiator>()
        fun createListFromJSONArray(array: JSONArray) : LinkedList<Initiator> {
            val initiatorList = LinkedList<Initiator>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val email = json.getString("email")
                val password = json.getString("password")
                val director = json.getBoolean("director")
                val levelId = json.getInt("levelId")
                val deleted = json.getBoolean("deleted")

                initiatorList.add(Initiator(id, name, email, password, director, levelId, deleted))
            }
            return initiatorList
        }

        fun findById(id : Int) : Initiator?{
            all.forEach { initiator ->
                if(initiator.id == id){
                    return initiator
                }
            }
            return null
        }

        fun findByEmailAndPassword(email: String, password : String) : Initiator?{
            all.forEach { initiator ->
                if(initiator.email == email && initiator.password == password){
                    return initiator
                }
            }
            return null
        }

        fun saveAsJSON(pref: SharedPreferences){
            var json = "["
            this.all.forEach{
                val id = it.id
                val name = it.name
                val email = it.email
                val password = it.password
                val director = if(it.director){"true"}else{"false"}
                val levelId = it.levelId
                val deleted = if(it.deleted){"true"}else{"false"}

                json += "{\"id\": $id, \"name\": \"$name\",\"email\": \"$email\",  \"password\": \"$password\", \"director\":$director, \"levelId\": $levelId, \"deleted\":$deleted}, "
            }
            if(json.length > 1){
                json = json.substring(0, json.length-2)
            }
            json += "]"

            println(json)
            pref.edit().remove("initiator").putString("initiator", json).apply()
        }
    }
}