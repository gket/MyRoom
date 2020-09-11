package com.gket.myroom.ui.notedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gket.myroom.data.Note
import com.gket.myroom.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: NoteRepository) : ViewModel() {

    var isNoteAdded = MutableLiveData<Boolean>()
    var isNoteUpdated = MutableLiveData<Boolean>()
    var detailedNote = MutableLiveData<Note>()

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
        isNoteAdded.value = true
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
        isNoteUpdated.value = true
    }

    fun getNoteDetailById(id: Int) = viewModelScope.launch {
        detailedNote.value = repository.getNoteDetailById(id)
    }

}