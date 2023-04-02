package quoi.feur.yoyodroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import quoi.feur.yoyodroid.entities.Formation
import quoi.feur.yoyodroid.entities.Participation
import quoi.feur.yoyodroid.entities.Student

class StudentEvalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_eval)

        //Checking request
        val intent : Intent = getIntent()
        val studentId : Int = intent.getIntExtra("studentId", -1)

        if(studentId == -1){
            finish()
        }

        findViewById<TextView>(R.id.header4).text = Student.findbyId(studentId).toString()


        val participations = Participation.findByStudentId(studentId)

        if(participations.size > 0) {

            val participationSize = participations.size.toString()
            findViewById<TextView>(R.id.subHeader4).text = "$participationSize aptitudes(s) found!"

            val aptitudesList = findViewById<ListView>(R.id.aptitudesList)

            val adapter: ArrayAdapter<Participation> =
                ArrayAdapter<Participation>(this, android.R.layout.simple_list_item_1, participations)
            aptitudesList.adapter = adapter

            aptitudesList.setOnItemClickListener { adapterView, view, position, id ->
                val chosenView = view as TextView
                Toast.makeText(this, chosenView.text, Toast.LENGTH_SHORT).show()
            }
        }
        else{
            findViewById<TextView>(R.id.subHeader4).text = "No students found!"
        }
    }
}