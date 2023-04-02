package quoi.feur.yoyodroid.entities

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

class Participation ( val id : Int, var contentId : Int, var studentId : Int, var status: Status, var commentary : String){
    companion object{

        var all : LinkedList<Participation> = LinkedList<Participation>()

        fun createListFromJSONArray(array: JSONArray) : LinkedList<Participation> {
            val participationList = LinkedList<Participation>()
            for (i in 0 until array.length()) {
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val contentId = json.getInt("contentId")
                val studentId = json.getInt("studentId")
                val status = Status.getStatus(json.getInt("statusId"))
                var commentary = "";
                try {
                    commentary = json.getString("commentary")
                } catch (_: Exception) {
                }
                participationList.add(Participation(id, contentId, studentId, status, commentary))
            }
            return participationList
        }

        fun saveAsJSON(pref: SharedPreferences){
            var json = "["
            this.all.forEach{
                val id = it.id
                val contentId = it.contentId
                val studentId = it.studentId
                val statusId = it.status.getId()
                val commentary : String = if(it.commentary == "null"){"null"}else{"\"".plus(it.commentary).plus("\"")}

                json += "{\"id\": $id, \"contentId\": $contentId, \"studentId\": $studentId, \"statusId\": $statusId, \"commentary\": $commentary}, "
            }
            if(json.length > 1){
                json = json.substring(0, json.length-2)
            }
            json += "]"

            pref.edit().remove("participation").putString("participation", json).apply()
        }


        fun findByStudentId(id:Int): LinkedList<Participation>{
            val found = LinkedList<Participation>()
            all.forEach{participation ->
                if(participation.studentId == id){
                    found.add(participation)
                }
            }
            return found
        }

        fun findById(id:Int): Participation?{
            all.forEach { participation ->
                if(participation.id == id){
                    return participation
                }
            }
            return null
        }

        fun findAptitudesByStudentId(id : Int): LinkedList<Aptitude>{
            val found = LinkedList<Aptitude>()
            val noDupes = HashSet<Int>()
            all.forEach{participation ->

                val aptitudeId = Content.findById(participation.contentId)!!.aptitudeId
                if(participation.studentId == id && noDupes.add(aptitudeId)){
                    found.add(Aptitude.findById(aptitudeId)!!)
                }
            }
            return found
        }

        fun findByStudentIdAndAptitudeId(studentId : Int, aptitudeId: Int): LinkedList<Participation>{
            val found = LinkedList<Participation>()
            all.forEach{participation ->

                val content = Content.findById(participation.contentId)!!
                if(participation.studentId == studentId && content.aptitudeId == aptitudeId){
                    found.add(participation)
                }
            }
            return found
        }

        fun getUnusedId(): Int{
            var max = 0
            all.forEach{participation ->

                if(participation.id > max){
                    max = participation.id
                }
            }
            return max+1
        }
    }

    override fun toString(): String {
        val statusString = status.toString()
        val session = Session.findById(Content.findById(contentId)!!.sessionId)!!
        val commentary = if(commentary == "null"){"No comment"}else{this.commentary}

        return  session.toString().plus(" (").plus(statusString).plus(") : $commentary")

    }

    fun toStringWithStudentAndAptitude(): String{
        return Student.findbyId(studentId).toString().plus(" : ").plus(Aptitude.findById( Content.findById(contentId)!!.aptitudeId)!!.name)
    }

}