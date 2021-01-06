package com.gket.myroom.di

import com.gket.myroom.repository.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        NoteRepository(get())
    }
}