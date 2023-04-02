package quoi.feur.yoyodroid.entities

import android.database.sqlite.SQLiteDatabase
import org.json.JSONArray
import java.util.*

class Student (val id : Int, var name: String, var formationId : Int, var phone : String, var deleted : Boolean){
    companion object{

        var all : List<Student> = LinkedList<Student>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Student> {
            val studentList = LinkedList<Student>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var name = json.getString("name")
                var formationId = json.getInt("formationId")
                var phone = json.getString("phone")
                var deleted = json.getBoolean("deleted")

                studentList.add(Student(id, name, formationId, phone, deleted))
            }
            return studentList
        }
    }
}