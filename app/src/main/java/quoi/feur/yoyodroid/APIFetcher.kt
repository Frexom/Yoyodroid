package quoi.feur.yoyodroid

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class APIFetcher : ViewModel() {

    private var lastData: MutableLiveData<String> = MutableLiveData<String>();


    fun fillDatabase(): Boolean{
        val levelData :LiveData<String> = this.fetchAPI("level")
        val skillData :LiveData<String> = this.fetchAPI("skills")
        val aptitudeData :LiveData<String> = this.fetchAPI("aptitude")

        while(levelData.value == null || skillData.value == null || aptitudeData.value == null){
            Thread.sleep(500)
        }

        val levels = JSONObject(levelData.value!!)
        val skills = JSONObject(skillData.value!!)
        val aptitudes = JSONObject(aptitudeData.value!!)






        return true;
    }

    fun fetchAPI(api:String) : LiveData<String> {
        var res = StringBuilder()
        thread {
            var url = URL("https://dev-restandroid.users.info.unicaen.fr/REST/$api/")
            var inet: HttpURLConnection = url.openConnection() as HttpURLConnection
            inet.connect();

            if (inet.responseCode == HttpURLConnection.HTTP_OK) {
                var reader: BufferedReader = BufferedReader(InputStreamReader(inet.inputStream));
                var line: String? = reader.readLine()
                while (line != null) {
                    res.append(line);
                    line = reader.readLine()
                }
                reader.close();
            }
            lastData.postValue(res.toString())
            print(res.toString())
        }
        return lastData
    }

}