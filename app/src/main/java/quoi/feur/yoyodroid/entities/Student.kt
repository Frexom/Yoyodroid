package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Student (val id : Int, var name: String, var formationId : Int, var phone : String, var deleted : Boolean){
    companion object{

        var all : LinkedList<Student> = LinkedList<Student>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Student> {
            val studentList = LinkedList<Student>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val name = json.getString("name")
                val formationId = json.getInt("formationId")
                val phone = json.getString("phone")
                val deleted = json.getBoolean("deleted")

                studentList.add(Student(id, name, formationId, phone, deleted))
            }
            return studentList
        }

        fun findByFormationId(id: Int) : LinkedList<Student>{

            val found = LinkedList<Student>()

            all.forEach{student ->
                if(student.formationId == id){
                    found.add(student)
                }
            }
            return found
        }

        fun findbyId(id : Int) : Student?{
            all.forEach { student ->
                if(student.id == id){
                    return student
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return name.lowercase().split(" ").joinToString(separator = " ") { word-> word.replaceFirstChar { it.uppercase()}}
    }
}