package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Content (val id : Int, var sessionId : Int, var aptitudeId : Int) {
    companion object{

        var all: List<Content> = LinkedList<Content>()


        fun createListFromJSONArray(array: JSONArray) : LinkedList<Content> {
            val contentList = LinkedList<Content>()
            for(i in 0  until array.length()){
                val json = array.getJSONObject(i)
                val id = json.getInt("id")
                val sessionId = json.getInt("sessionId")
                val skillId = json.getInt("aptitudeId")

                contentList.add(Content(id, sessionId, skillId))
            }
            return contentList
        }

        fun findById(id : Int) : Content?{
            all.forEach { content ->
                if(content.id == id){
                    return content
                }
            }
            return null
        }

        fun findBySessionAndAptitudeId(sessionId : Int, aptitudeId : Int) : Content?{
            all.forEach { content ->
                if(content.sessionId == sessionId && content.aptitudeId == aptitudeId){
                    return content
                }
            }
            return null
        }


        fun getUnusedId(): Int{
            var max = 0
            all.forEach{ content ->

                if(content.id > max){
                    max = content.id
                }
            }
            return max+1
        }
    }
}