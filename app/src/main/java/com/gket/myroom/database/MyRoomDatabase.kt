package com.gket.myroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gket.myroom.data.Note
import com.gket.myroom.database.dao.MyRoomDao

@Database(entities = [Note::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun getNoteDao() : MyRoomDao
}