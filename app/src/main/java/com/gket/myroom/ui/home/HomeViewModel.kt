package com.gket.myroom.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gket.myroom.data.Note
import com.gket.myroom.repository.NoteRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {

    var allNotes = MutableLiveData<List<Note>>()
    var searchResult = MutableLiveData<List<Note>>()
    var isNoteDeleted = MutableLiveData<Boolean>()

    fun getAllNotes() = viewModelScope.launch {
        allNotes.value = repository.getAllNotes()
    }

    fun getSearchResult(searchQuery : String?) = viewModelScope.launch {
        searchResult.value = repository.getSearchResult(searchQuery)
    }

    fun deleteNote(note : Note) = viewModelScope.launch {
        repository.deleteNote(note)
        isNoteDeleted.value = true
    }

}