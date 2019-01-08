package com.jshvarts.notesnavigation.presentation.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jshvarts.notesnavigation.domain.NotesManager
import java.lang.IllegalArgumentException

/**
 * Created by Oleg Sitnikov on 2019-01-08
 */
class AddNoteViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get()= status

    fun addNote(noteText: String) {
        status.value = try {
            NotesManager.addNote(noteText)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}