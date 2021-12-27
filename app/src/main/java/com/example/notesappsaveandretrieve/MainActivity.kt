package com.example.notesappsaveandretrieve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var addNoteEt: EditText
    lateinit var addBu : Button
    lateinit var recView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    var notes= ArrayList<Notes>()
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteEt = findViewById(R.id.addNoteEt)
        addBu = findViewById(R.id.addBu)
        recView = findViewById(R.id.recView)

        rvAdapter = RecyclerViewAdapter(notes)
        recView.adapter = rvAdapter
        recView.layoutManager = LinearLayoutManager(this)


        addBu.setOnClickListener{
            var note = addNoteEt.text.toString()
            databaseHelper.saveData(note)
            Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
            notes = databaseHelper.readData()
            rvAdapter.update(notes)

        }

    }
}