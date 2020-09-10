package com.gket.myroom.database.dao

import android.graphics.Bitmap
import androidx.room.*
import com.gket.myroom.data.Note

@Dao
interface MyRoomDao {

    @Query("SELECT * FROM note")
    suspend fun getAllNotes() : List<Note>

    @Insert
    suspend fun addNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM note where noteTitle like :searchQuery OR noteDescription like :searchQuery")
    suspend fun getSearchResults(searchQuery : String?) : List<Note>
}