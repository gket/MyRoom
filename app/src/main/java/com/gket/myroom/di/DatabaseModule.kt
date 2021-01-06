package com.gket.myroom.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.gket.myroom.R
import com.gket.myroom.database.MyRoomDatabase
import com.gket.myroom.database.dao.MyRoomDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            MyRoomDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<MyRoomDatabase>().getNoteDao()
    }
}