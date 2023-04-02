package quoi.feur.yoyodroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import quoi.feur.yoyodroid.entities.Formation
import quoi.feur.yoyodroid.entities.Student

class FormationStudentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formation_students)

        //Checking request
        val formationId : Int = intent.getIntExtra("formationId", -1)

        if(formationId == -1){
            finish()
        }
        findViewById<TextView>(R.id.header3).text = Formation.findById(formationId).toString()

        val students = Student.findByFormationId(formationId)

        if(students.size > 0) {

            val studentSize = students.size.toString()

            findViewById<TextView>(R.id.subHeader3).text = "$studentSize student(s) found!"



            val studentList = findViewById<ListView>(R.id.studentList)

            val adapter: ArrayAdapter<Student> =
                ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students)
            studentList.adapter = adapter

            studentList.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this, StudentEvalActivity::class.java)
                intent.putExtra("studentId", students[position].id)
                startActivity(intent)
            }
        }
        else{
            findViewById<TextView>(R.id.subHeader3).text = "No students found!"
        }





    }
}