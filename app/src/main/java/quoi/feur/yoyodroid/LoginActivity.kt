package quoi.feur.yoyodroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import quoi.feur.yoyodroid.entities.Formation
import quoi.feur.yoyodroid.entities.Initiator
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {

    val fletcher = APIFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pref = applicationContext.getSharedPreferences("yoyodroid", Context.MODE_PRIVATE)


        if(pref.getString("formation", "") == ""){
            thread{
                fletcher.fillDatabase(pref)
            }
        }
        else{
            try {
                fletcher.initializeAll(pref)
            }catch (e:java.lang.Exception){
                thread{
                    fletcher.fillDatabase(pref)
                }
            }
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener{
            val email = findViewById<EditText>(R.id.emailField).text.toString()
            val password = findViewById<EditText>(R.id.passwordField).text.toString()
            val initiator = Initiator.findByEmailAndPassword(email, password)
            if(initiator == null){
                Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("initiatorId", initiator.id)
                startActivity(intent)
            }
        }
    }
}