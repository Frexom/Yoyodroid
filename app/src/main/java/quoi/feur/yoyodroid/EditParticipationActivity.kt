package quoi.feur.yoyodroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import quoi.feur.yoyodroid.entities.*

class EditParticipationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_participation)
        //Checking request
        val aptitudeId: Int = intent.getIntExtra("aptitudeId", -1)
        val studentId: Int = intent.getIntExtra("studentId", -1)


        if (aptitudeId == -1 || studentId == -1) {
            finish()
        }

        val student = Student.findbyId(studentId)!!

        val pref = applicationContext.getSharedPreferences("yoyodroid", Context.MODE_PRIVATE)
        findViewById<TextView>(R.id.header5).text = student.toString()
        findViewById<TextView>(R.id.subHeader5).text = Aptitude.findById(aptitudeId).toString()



        val button = findViewById<Button>(R.id.aptitudeEditSubmit)
        val statusRoller = findViewById<Spinner>(R.id.statusSpinner)
        val sessionRoller = findViewById<Spinner>(R.id.sessionSpinner)
        val commentaryText = findViewById<EditText>(R.id.commentaryText)
        val history = findViewById<ListView>(R.id.history)

        val tmp = Participation.findByStudentIdAndAptitudeId(studentId, aptitudeId)
        tmp


        var participations = Participation.findByStudentIdAndAptitudeId(studentId, aptitudeId)
        val historyAdapter : ArrayAdapter<Participation> = ArrayAdapter<Participation>(this, android.R.layout.simple_list_item_1, participations)
        history.adapter = historyAdapter

        val sessionAdapter: ArrayAdapter<Session> =
            ArrayAdapter<Session>(this, android.R.layout.simple_list_item_1, Session.findByFormationId(student.formationId))
        sessionRoller.adapter = sessionAdapter

        val statusAdapter: ArrayAdapter<Status> =
            ArrayAdapter<Status>(this, android.R.layout.simple_list_item_1, Status.all)
        statusRoller.adapter = statusAdapter

        button.setOnClickListener{

            val participationId = Participation.getUnusedId()

            val session = sessionRoller.selectedItem as Session
            var content = Content.findBySessionAndAptitudeId(session.id, aptitudeId)
            if(content == null){
                content = Content(Content.getUnusedId(), session.id, aptitudeId)
            }
            val status = statusRoller.selectedItem as Status
            val commentary = if(commentaryText.text.toString() == "") {"null"} else {commentaryText.text.toString()}

            val newParticipation = Participation(participationId, content.id, studentId, status, commentary)

            Participation.all.add(newParticipation)
            Participation.saveAsJSON(pref)

            participations.add(newParticipation)
            historyAdapter.notifyDataSetChanged()
        }
    }
}