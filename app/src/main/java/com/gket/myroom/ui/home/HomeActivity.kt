package com.gket.myroom.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gket.myroom.R
import com.gket.myroom.adapter.NoteAdapter
import com.gket.myroom.ui.notedetail.NoteDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by inject()
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeProcess()
        searchProcess()
        homeViewModel.getAllNotes()
        noteAdapter = NoteAdapter()
        recyclerViewNotes.adapter = noteAdapter
        noteAdapterClicked()
        addNoteClicked()
    }

    private fun getSearchResultFromDb(query: String?) {
        var searchText = query
        // SQL De search sorgusunu tüm datanın içerisinde araması için %value% şeklinde veriyoruz
        searchText = "%$searchText%"
        homeViewModel.getSearchResult(searchText)
    }

    fun observeProcess() {
        homeViewModel.allNotes.observe(this, Observer {
            noteAdapter.setData(it)
        })

        homeViewModel.searchResult.observe(this, Observer {
            noteAdapter.setData(it)
        })

        homeViewModel.isNoteDeleted.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                homeViewModel.getAllNotes()
            }
        })
    }

    private fun searchProcess() {
        searchView.isFocusable = true;
        searchView.isFocusableInTouchMode = false
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryString: String?): Boolean {
                getSearchResultFromDb(queryString)
                return false
            }

            override fun onQueryTextChange(queryString: String?): Boolean {
                getSearchResultFromDb(queryString)
                return false
            }
        })
    }

    fun noteAdapterClicked() {
        noteAdapter.onNoteClicked = {
            startActivity(Intent(this, NoteDetailActivity::class.java).putExtra("ID", it))
            finish()
        }

        noteAdapter.onDeleteButtonClicked = {
            GlobalScope.launch {
                delay(1000)
                homeViewModel.deleteNote(it)
            }
        }
    }

    fun addNoteClicked() {
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, NoteDetailActivity::class.java))
            finish()
        }
    }

}