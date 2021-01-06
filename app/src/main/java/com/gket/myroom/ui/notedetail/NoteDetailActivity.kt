package com.gket.myroom.ui.notedetail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gket.myroom.R
import com.gket.myroom.data.Note
import com.gket.myroom.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_add_note.*
import org.koin.android.ext.android.inject

class NoteDetailActivity : AppCompatActivity() {

    private val noteDetailViewModel: NoteDetailViewModel by inject()
    private var noteId: Int = -1
    private var isInDb: Boolean = false
    private var noteDetail = Note()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        observeProcess()
        noteId = intent.getIntExtra("ID", -1)
        if (noteId != -1) {
            isInDb = true
            noteDetailViewModel.getNoteDetailById(noteId)
        }
        button.setOnClickListener {
            addNoteProcess(isInDb)
        }
    }

    fun observeProcess() {
        noteDetailViewModel.detailedNote.observe(this, Observer {
            noteDetail = it
            editTextDescription.setText(it.noteDescription)
            editTextTitle.setText(it.noteTitle)
            editTextPriority.setText(it.notePoint.toString())
        })

        noteDetailViewModel.isNoteAdded.observe(this, Observer {
            if (it == true) {
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
                goBack()
            }
        })

        noteDetailViewModel.isNoteUpdated.observe(this, Observer {
            if (it == true) {
                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                goBack()
            }
        })
    }

    fun addNoteProcess(isUpdateProcess: Boolean) {
        if(checkTextFields()){
            val note = noteDetail
            note.noteTitle = editTextTitle.text.toString()
            note.noteDescription = editTextDescription.text.toString()
            note.notePoint = editTextPriority.text.toString().toInt()
            if (isUpdateProcess)
                noteDetailViewModel.updateNote(noteDetail)
            else
                noteDetailViewModel.addNote(note)
        }
        else{
            Toast.makeText(this, "Please fill to empty fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkTextFields(): Boolean {
        return editTextTitle.text.toString().isNotEmpty() && editTextDescription.text.toString()
            .isNotEmpty() && editTextPriority.text.toString().isNotEmpty()
    }

    private fun goBack() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        goBack()
    }

}