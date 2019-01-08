package com.jshvarts.notesnavigation.presentation.notelist

import androidx.recyclerview.widget.DiffUtil
import com.jshvarts.notesnavigation.domain.Note

class NoteDiffCallback(private val old: List<Note>,
                       private val new: List<Note>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

}
