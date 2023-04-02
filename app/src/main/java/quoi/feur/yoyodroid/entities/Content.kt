package quoi.feur.yoyodroid.entities

import org.json.JSONArray
import java.util.*

class Content (val id : Int, var sessionId : Int, var aptitudeId : Int) {
    companion object{

        var all: List<Content> = LinkedList<Content>()


        fun createListFromJSONArray(array: JSONArray) : LinkedList<Content> {
            val contentList = LinkedList<Content>()
            for(i in 0  until array.length()){
                var json = array.getJSONObject(i)
                var id = json.getInt("id")
                var sessionId = json.getInt("sessionId")
                var skillId = json.getInt("aptitudeId")

                contentList.add(Content(id, sessionId, skillId))
            }
            return contentList
        }
    }
}