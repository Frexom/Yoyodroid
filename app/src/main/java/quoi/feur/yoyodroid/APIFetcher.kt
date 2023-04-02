package quoi.feur.yoyodroid

import android.content.SharedPreferences
import android.provider.Telephony.Mms.Part
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import quoi.feur.yoyodroid.entities.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.reflect.KClass

class APIFetcher : ViewModel() {




    fun fillDatabase(pref: SharedPreferences, graphicalUpdate: MutableLiveData<String>) {
        val editor = pref.edit()
        editor.clear()

        val keys = arrayOf<String>("content", "formation", "initiator", "participation", "session", "student", "trainingManager", "level", "skill", "aptitude")
        keys.forEach { key ->
            var data: MutableLiveData<String> = this.fetchAPI(key)

            while (data.value == null) {
                Thread.sleep(100)
            }

            editor.putString(key, data.value)

            graphicalUpdate.postValue(key.plus(pref.getString(key, "")))
        }
        editor.apply()
        this.initializeAll(pref)
    }

    public fun initializeAll(pref: SharedPreferences){
        Content.all = Content.createListFromJSONArray(JSONArray(pref.getString("content", "")))
        Formation.all = Formation.createListFromJSONArray(JSONArray(pref.getString("formation", "")))
        Initiator.all = Initiator.createListFromJSONArray(JSONArray(pref.getString("initiator", "")))
        Participation.all = Participation.createListFromJSONArray(JSONArray(pref.getString("participation", "")))
        Session.all = Session.createListFromJSONArray(JSONArray(pref.getString("session", "")))
        Student.all = Student.createListFromJSONArray(JSONArray(pref.getString("student", "")))
        TrainingManager.all = TrainingManager.createListFromJSONArray(JSONArray(pref.getString("trainingManager", "")))
        Level.all = Level.createListFromJSONArray(JSONArray(pref.getString("level", "")))
        Skill.all = Skill.createListFromJSONArray(JSONArray(pref.getString("skill", "")))
        Aptitude.all = Aptitude.createListFromJSONArray(JSONArray(pref.getString("aptitude", "")))

    }

    private fun fetchAPI(api:String) : MutableLiveData<String> {
        val data = MutableLiveData<String>()
        var res = StringBuilder()
        thread {
            var url = URL("https://dev-restandroid.users.info.unicaen.fr/REST/$api/")
            var inet: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                inet.connect();

                if (inet.responseCode == HttpURLConnection.HTTP_OK) {
                    var reader: BufferedReader =
                        BufferedReader(InputStreamReader(inet.inputStream));
                    var line: String? = reader.readLine()
                    while (line != null) {
                        res.append(line);
                        line = reader.readLine()
                    }
                    reader.close();
                }
                data.postValue(res.toString())
                print(res.toString())
            }catch (e: java.net.ConnectException){
                data.postValue("Couldn't connect to the API.")
            }
        }
        return data
    }

}