package com.gket.myroom.di

import com.gket.myroom.ui.home.HomeViewModel
import com.gket.myroom.ui.notedetail.NoteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { NoteDetailViewModel(get()) }
}