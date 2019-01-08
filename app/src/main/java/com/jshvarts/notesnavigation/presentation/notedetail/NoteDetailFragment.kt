package com.jshvarts.notesnavigation.presentation.notedetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jshvarts.notesnavigation.R
import com.jshvarts.notesnavigation.domain.Note
import com.jshvarts.notesnavigation.presentation.notedetail.NoteDetailFragmentArgs.fromBundle
import com.jshvarts.notesnavigation.presentation.notedetail.NoteDetailFragmentDirections.actionNoteDetailToDeleteNote
import kotlinx.android.synthetic.main.note_detail_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NoteDetailFragment : Fragment() {

    private lateinit var viewModel: NoteDetailViewModel

    private val noteId by lazy {
        arguments?.let { fromBundle(it).noteId }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[NoteDetailViewModel::class.java]
        viewModel.observableNote.observe(this, Observer { note ->
            note?.let { render(note) } ?: renderNotNotFound()
        })

        editNoteButton.setOnClickListener {
            val navDirections = noteId?.let { noteId -> actionNoteDetailToDeleteNote(noteId) }
            navDirections?.let { navDirection -> findNavController().navigate(navDirection) }
        }

        deleteNoteButton.setOnClickListener {
            val navDirections = noteId?.let { noteId -> actionNoteDetailToDeleteNote(noteId) }
            navDirections?.let { nav -> findNavController().navigate(nav) }
        }
    }

    override fun onResume() {
        super.onResume()
        noteId?.let { viewModel.getNote(it) }
    }

    private fun render(note: Note) {
        noteIdView.text = String.format(getString(R.string.note_detail_id), note.id)
        noteText.text = String.format(getString(R.string.note_detail_text),note.text)
    }

    private fun renderNotNotFound() {
        noteText.visibility = View.GONE
        noteIdView.visibility = View.GONE
        view?.let {
            Snackbar.make(it, R.string.error_loading_note, Snackbar.LENGTH_LONG).show()
        }

    }


}
