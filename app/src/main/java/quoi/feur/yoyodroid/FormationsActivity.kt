package quoi.feur.yoyodroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import quoi.feur.yoyodroid.entities.Formation

class FormationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formations)

        val formationsList = findViewById<ListView>(R.id.formations)

        val formationSize = Formation.all.size.toString()
        findViewById<TextView>(R.id.subHeader2).text = "$formationSize formation(s) found!"

        val adapter : ArrayAdapter<Formation> = ArrayAdapter<Formation>(this, android.R.layout.simple_list_item_1, Formation.all)
        formationsList.adapter = adapter

        formationsList.setOnItemClickListener{ _, _, position, _ ->
            val intent = Intent(this, FormationStudentsActivity::class.java)
            intent.putExtra("formationId", Formation.all[position].id)
            startActivity(intent)
        }
    }
}