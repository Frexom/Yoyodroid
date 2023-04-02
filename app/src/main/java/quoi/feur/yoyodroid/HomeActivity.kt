package quoi.feur.yoyodroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlin.concurrent.thread

class HomeActivity : AppCompatActivity() {

    private val fletcher = APIFetcher()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val pref = applicationContext.getSharedPreferences("yoyodroid", Context.MODE_PRIVATE)

        val subHeader = findViewById<TextView>(R.id.subHeader)
        val subHeaderContent : MutableLiveData<String> = MutableLiveData<String>()
        subHeaderContent.observe(this, Observer {
            subHeader.text = it
        })

        if(pref.getString("formation", "") == ""){
            val thread = thread{
                subHeaderContent.postValue("Fetching API...")
                fletcher.fillDatabase(pref, subHeaderContent)
                subHeaderContent.postValue("Database is up to date!")
            }
        }
        else{
            fletcher.initializeAll(pref)
            subHeaderContent.postValue("Database is up to date!")
        }


        val formationsButton = findViewById<Button>(R.id.formationsButton)
        val intent : Intent = Intent(this, FormationsActivity::class.java)
        formationsButton.setOnClickListener{
            startActivity(intent)
        }
    }
}