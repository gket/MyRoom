package com.gket.myroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gket.myroom.R
import com.gket.myroom.data.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var items = listOf<Note>()

    fun setData(noteItems: List<Note>) {
        items = noteItems
        notifyDataSetChanged()
    }

    lateinit var onNoteClicked: ((Int) -> Unit)
    lateinit var onDeleteButtonClicked: ((Note) -> Unit)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNoteTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewNoteDescription: TextView = itemView.findViewById(R.id.textViewDetail)
        val textViewPriority: TextView = itemView.findViewById(R.id.textViewPriority)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewNoteTitle.text = items[position].noteTitle
        holder.textViewNoteDescription.text = items[position].noteDescription
        holder.textViewPriority.text = items[position].notePoint.toString()
        holder.itemView.setOnClickListener {
            onNoteClicked.invoke(items[position].noteId!!)
        }
        holder.buttonDelete.setOnClickListener {
            onDeleteButtonClicked.invoke(items[position])
        }
    }
}