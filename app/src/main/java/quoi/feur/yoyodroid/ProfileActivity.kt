package quoi.feur.yoyodroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import quoi.feur.yoyodroid.entities.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Checking request
        val initiatorId : Int = intent.getIntExtra("initiatorId", -1)

        if(initiatorId == -1){
            finish()
        }

        val initiator = Initiator.findById(initiatorId)!!

        findViewById<TextView>(R.id.subHeader7).text = "Welcome, ".plus(initiator.name).plus("!")
        val editName = findViewById<EditText>(R.id.nameEdit)
        val editMail = findViewById<EditText>(R.id.emailEdit)
        val editPass = findViewById<EditText>(R.id.passwordEdit)
        val submit = findViewById<Button>(R.id.profileSubmitButton)

        editName.setText(initiator.name)
        editMail.setText(initiator.email)
        editPass.setText(initiator.password)

        submit.setOnClickListener{

            val name = editName.text.toString()
            val mail = editMail.text.toString()
            val pass = editPass.text.toString()

            if(name != "" && mail != "" && pass != ""){
                initiator.name = name
                initiator.email = mail
                initiator.password = pass

                Initiator.saveAsJSON(applicationContext.getSharedPreferences("yoyodroid", Context.MODE_PRIVATE))
                Toast.makeText(applicationContext, "Changes have been saved!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "Please fill out every field!", Toast.LENGTH_SHORT).show()

            }

        }


    }
}