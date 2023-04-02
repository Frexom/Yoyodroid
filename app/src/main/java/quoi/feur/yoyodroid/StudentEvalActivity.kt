package quoi.feur.yoyodroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import quoi.feur.yoyodroid.entities.Aptitude
import quoi.feur.yoyodroid.entities.Participation
import quoi.feur.yoyodroid.entities.Student

class StudentEvalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_eval)

        //Checking request
        val studentId : Int = intent.getIntExtra("studentId", -1)

        if(studentId == -1){
            finish()
        }

        findViewById<TextView>(R.id.header4).text = Student.findbyId(studentId).toString()


        val aptitudes = Participation.findAptitudesByStudentId(studentId)

        if(aptitudes.size > 0) {

            val aptitudesSize = aptitudes.size.toString()
            findViewById<TextView>(R.id.subHeader4).text = "$aptitudesSize aptitudes(s) found!"

            val aptitudesList = findViewById<ListView>(R.id.aptitudesList)

            val adapter: ArrayAdapter<Aptitude> =
                ArrayAdapter<Aptitude>(this, android.R.layout.simple_list_item_1, aptitudes)
            aptitudesList.adapter = adapter

            aptitudesList.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this, EditParticipationActivity::class.java)
                intent.putExtra("aptitudeId", aptitudes[position].id)
                intent.putExtra("studentId", studentId)
                startActivity(intent)
            }
        }
        else{
            findViewById<TextView>(R.id.subHeader4).text = "No students found!"
        }
    }
}