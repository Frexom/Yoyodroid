package quoi.feur.yoyodroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.thread

class HomeActivity : AppCompatActivity() {

    private val fletcher = APIFetcher()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        //Checking request
        val initiatorId : Int = intent.getIntExtra("initiatorId", -1)

        if(initiatorId == -1){
            finish()
        }

        val pref = applicationContext.getSharedPreferences("yoyodroid", Context.MODE_PRIVATE)

        val subHeader = findViewById<TextView>(R.id.subHeader)
        val subHeaderContent : MutableLiveData<String> = MutableLiveData<String>()
        subHeaderContent.observe(this) {
            subHeader.text = it
        }

        subHeaderContent.postValue("Database is up to date!")


        val formationsButton = findViewById<Button>(R.id.formationsButton)
        formationsButton.setOnClickListener{
            if(pref.getString("aptitude", "") != "") {
                val intent = Intent(this, FormationsActivity::class.java)
                startActivity(intent)
            }
        }

        val fetchButton = findViewById<Button>(R.id.fetchButton)
        fetchButton.setOnClickListener{
            thread{
                subHeaderContent.postValue("Fetching API...")
                fletcher.fillDatabase(pref)
                subHeaderContent.postValue("Database is up to date!")
            }
        }

        val profileButton = findViewById<Button>(R.id.profileButton)
        profileButton.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("initiatorId", initiatorId)
            startActivity(intent)
        }
    }
}