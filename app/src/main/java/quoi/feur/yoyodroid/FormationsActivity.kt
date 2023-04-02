package quoi.feur.yoyodroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import quoi.feur.yoyodroid.entities.Formation

class FormationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formations)

        val formationsList = findViewById<ListView>(R.id.formations)


        val adapter : ArrayAdapter<Formation> = ArrayAdapter<Formation>(this, android.R.layout.simple_list_item_1, Formation.all)
        formationsList.adapter = adapter

        formationsList.setOnItemClickListener{ adapterView, view, position, id ->
            val chosenView = view as TextView
            Toast.makeText(this, chosenView.text, Toast.LENGTH_SHORT).show()
        }
    }
}