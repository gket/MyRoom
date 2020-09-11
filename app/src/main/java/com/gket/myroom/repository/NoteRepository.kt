package com.gket.myroom.repository

import com.gket.myroom.data.Note
import com.gket.myroom.database.dao.MyRoomDao

class NoteRepository(private val myRoomDao: MyRoomDao) {
    suspend fun getAllNotes() = myRoomDao.getAllNotes()
    suspend fun addNote(note : Note) = myRoomDao.addNote(note)
    suspend fun updateNote(note : Note) = myRoomDao.updateNote(note)
    suspend fun deleteNote(note : Note) = myRoomDao.deleteNote(note)
    suspend fun getSearchResult(searchQuery : String?) = myRoomDao.getSearchResults(searchQuery)
    suspend fun getNoteDetailById(id : Int) = myRoomDao.getNoteDetailById(id)
}