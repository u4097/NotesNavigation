package com.jshvarts.notesnavigation.presentation.notelist

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.jshvarts.notesnavigation.R
import com.jshvarts.notesnavigation.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*

/**
 * Created by Oleg Sitnikov on 2019-01-08
 */

typealias ClickListener = (Note) -> Unit

class NoteAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList = emptyList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val itemContainer = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false) as ViewGroup
        val viewHolder = ViewHolder(itemContainer)
        itemContainer.setOnClickListener{
            clickListener(noteList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int =
            noteList.size



    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = noteList[position]
        holder.id.text = note.id.toString()
        holder.text.text = note.text
    }

    class ViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup) {
        val id: TextView = itemViewGroup.findViewById(R.id.noteId)
        val text: TextView = itemViewGroup.findViewById(R.id.noteText)
    }

    fun updateNotes(noteList: List<Note>) {
        val diffResult = calculateDiff(NoteDiffCallback(this.noteList,noteList))
        this.noteList = noteList
        diffResult.dispatchUpdatesTo(this)
    }
}